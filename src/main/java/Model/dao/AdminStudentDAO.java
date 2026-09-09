package Model.dao;

import Model.entity.StudentEntity;
import DateandTimeConnection.DateandTimeConnection;
import DBConnection.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminStudentDAO {

    // 1. Auto Generate Next Student ID
    public String generateNextStudentId() {
        String nextId = "CCAS001";
        String query = "SELECT student_id FROM student ORDER BY student_id DESC LIMIT 1";

        try {
            Connection con = DBConnection.getInstance().getConnection();
            
            try (Statement stm = con.createStatement();
                 ResultSet rst = stm.executeQuery(query)) {

                if (rst.next()) {
                    String lastId = rst.getString("student_id");
                    int numericPart = Integer.parseInt(lastId.replace("CCAS", ""));
                    numericPart++;
                    nextId = String.format("CCAS%03d", numericPart);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nextId;
    }

    // 2. Add Student (class_id ඇතුළුව Table දෙකටම ඩේටා දැමීම)
    public boolean addStudent(StudentEntity student) {
        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false); 

            // users table එක සඳහා ඊළඟ user_id එක සොයා ගැනීම
            int nextUserId = 1; 
            String getMaxIdQuery = "SELECT MAX(user_id) FROM users";
            try (Statement stm = con.createStatement(); 
                 ResultSet rs = stm.executeQuery(getMaxIdQuery)) {
                if (rs.next() && rs.getObject(1) != null) {
                    nextUserId = rs.getInt(1) + 1; 
                }
            }

            // Step 1: `users` table එකට Data දැමීම 
            String userQuery = "INSERT INTO users (user_id, email, password, role, status) VALUES (?, ?, ?, 'Student', ?)";
            PreparedStatement userPst = con.prepareStatement(userQuery);
            userPst.setInt(1, nextUserId); 
            userPst.setString(2, student.getEmail());
            userPst.setString(3, student.getPassword());
            userPst.setString(4, student.getStatus());
            
            int affectedRows = userPst.executeUpdate();
            if (affectedRows == 0) {
                con.rollback();
                return false;
            }

            // Step 2: `student` table එකට Data දැමීම (class_id සමඟ)
            String currentDateTime = DateandTimeConnection.getInstance().getCurrentDateTime();            
            String studentQuery = "INSERT INTO student (student_id, user_id, name, birthday, contact_number, address, gender, class_id, registration_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement studentPst = con.prepareStatement(studentQuery);
            studentPst.setString(1, student.getStudentId());
            studentPst.setInt(2, nextUserId); 
            studentPst.setString(3, student.getName());
            studentPst.setString(4, student.getBirthday());
            studentPst.setString(5, student.getContactNumber());
            studentPst.setString(6, student.getAddress());
            studentPst.setString(7, student.getGender());
            
            // --- FIX: Class ID එක Null හෝ Empty නම් Database එකට SQL NULL යැවීම ---
            if (student.getClassId() == null || student.getClassId().trim().isEmpty()) {
                studentPst.setNull(8, java.sql.Types.VARCHAR);
            } else {
                studentPst.setString(8, student.getClassId()); 
            }
            
            studentPst.setString(9, currentDateTime);

            int studentAffected = studentPst.executeUpdate();
            if (studentAffected > 0) {
                con.commit(); 
                return true;
            } else {
                con.rollback();
                return false;
            }

        } catch (Exception e) {
            if (con != null) {
                try { con.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            }
            e.printStackTrace();
            return false;
        } finally {
            if (con != null) {
                try { con.setAutoCommit(true); } catch (SQLException ex) { ex.printStackTrace(); }
            }
        }
    }

    // 3. Load Data for JTable (class_id ඇතුළුව)
    public List<StudentEntity> getAllStudents() {
        List<StudentEntity> students = new ArrayList<>();
        // LEFT JOIN මගින් class ටේබල් එක සම්බන්ධ කර ඇත
        String query = "SELECT s.student_id, s.name, s.birthday, s.contact_number, u.email, s.address, s.gender, s.class_id, c.class_name, u.password, u.status, s.registration_date " +
                       "FROM student s " +
                       "JOIN users u ON s.user_id = u.user_id " +
                       "LEFT JOIN class c ON s.class_id = c.class_id";

        try {
            Connection con = DBConnection.getInstance().getConnection();
            try (Statement stm = con.createStatement();
                 ResultSet rst = stm.executeQuery(query)) {

                while (rst.next()) {
                    StudentEntity student = new StudentEntity(
                        rst.getString("student_id"), rst.getString("name"), rst.getString("birthday"),
                        rst.getString("contact_number"), rst.getString("email"), rst.getString("address"),
                        rst.getString("gender"), rst.getString("password"), rst.getString("status")
                    );
                    
                    student.setClassId(rst.getString("class_id"));
                    student.setClassName(rst.getString("class_name")); // පන්තියේ නම සෙට් කිරීම
                    student.setRegistrationDate(rst.getString("registration_date"));
                    
                    students.add(student);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }

    // 4. Search Student by ID (class_id ඇතුළුව)
    public StudentEntity searchStudent(String studentId) {
        String query = "SELECT s.student_id, s.name, s.birthday, s.contact_number, u.email, s.address, s.gender, s.class_id, c.class_name, u.password, u.status " +
                       "FROM student s " +
                       "JOIN users u ON s.user_id = u.user_id " +
                       "LEFT JOIN class c ON s.class_id = c.class_id WHERE s.student_id = ?";
        
        try {
            Connection con = DBConnection.getInstance().getConnection();
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setString(1, studentId);
                try (ResultSet rst = pst.executeQuery()) {
                    if (rst.next()) {
                        StudentEntity student = new StudentEntity(
                            rst.getString("student_id"), rst.getString("name"), rst.getString("birthday"),
                            rst.getString("contact_number"), rst.getString("email"), rst.getString("address"),
                            rst.getString("gender"), rst.getString("password"), rst.getString("status")
                        );
                        student.setClassId(rst.getString("class_id"));
                        student.setClassName(rst.getString("class_name")); // පන්තියේ නම සෙට් කිරීම
                        return student;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    // 5. Update Student (class_id යාවත්කාලීන කිරීම ඇතුළුව)
    public boolean updateStudent(StudentEntity student) {
        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false); 

            String userQuery = "UPDATE users SET email=?, password=?, status=? WHERE user_id = (SELECT user_id FROM student WHERE student_id=?)";
            PreparedStatement userPst = con.prepareStatement(userQuery);
            userPst.setString(1, student.getEmail());
            userPst.setString(2, student.getPassword());
            userPst.setString(3, student.getStatus());
            userPst.setString(4, student.getStudentId());
            userPst.executeUpdate();

            // Query එකට class_id=? එකතු කර ඇත
            String studentQuery = "UPDATE student SET name=?, birthday=?, contact_number=?, address=?, gender=?, class_id=? WHERE student_id=?";
            PreparedStatement studentPst = con.prepareStatement(studentQuery);
            studentPst.setString(1, student.getName());
            studentPst.setString(2, student.getBirthday());
            studentPst.setString(3, student.getContactNumber());
            studentPst.setString(4, student.getAddress());
            studentPst.setString(5, student.getGender());
            
            // --- FIX: Update එකේදිත් Class ID එක Null හෝ Empty නම් Database එකට SQL NULL යැවීම ---
            if (student.getClassId() == null || student.getClassId().trim().isEmpty()) {
                studentPst.setNull(6, java.sql.Types.VARCHAR);
            } else {
                studentPst.setString(6, student.getClassId()); 
            }
            
            studentPst.setString(7, student.getStudentId());
            
            int affected = studentPst.executeUpdate();
            if (affected > 0) {
                con.commit();
                return true;
            } else {
                con.rollback();
                return false;
            }
        } catch (Exception e) {
            if (con != null) try { con.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            e.printStackTrace();
            return false;
        } finally {
            if (con != null) try { con.setAutoCommit(true); } catch (SQLException ex) { ex.printStackTrace(); }
        }
    }

    // 6. Delete Student 
    public boolean deleteStudent(String studentId) {
        String query = "DELETE FROM users WHERE user_id = (SELECT user_id FROM student WHERE student_id = ?)";
        
        try {
            Connection con = DBConnection.getInstance().getConnection();
            
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setString(1, studentId);
                return pst.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}