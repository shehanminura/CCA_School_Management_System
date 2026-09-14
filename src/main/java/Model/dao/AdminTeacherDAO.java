package Model.dao;

import Model.entity.TeacherEntity;
import DBConnection.DBConnection;
import DateandTimeConnection.DateandTimeConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminTeacherDAO {

    // 1. Generate Auto ID for Teacher
    public String generateNextTeacherId() {
        String nextId = "CCAT001";
        String query = "SELECT teacher_id FROM teacher ORDER BY teacher_id DESC LIMIT 1";

        try {
            Connection con = DBConnection.getInstance().getConnection();
            try (Statement stm = con.createStatement();
                 ResultSet rst = stm.executeQuery(query)) {

                if (rst.next()) {
                    String lastId = rst.getString("teacher_id");
                    int numericPart = Integer.parseInt(lastId.replace("CCAT", ""));
                    numericPart++;
                    nextId = String.format("CCAT%03d", numericPart);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nextId;
    }

    // 2. Add New Teacher (Inserts data into both 'users' and 'teacher' tables)
    public boolean addTeacher(TeacherEntity teacher) {
        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false); 

            // Get next user_id
            int nextUserId = 1; 
            String getMaxIdQuery = "SELECT MAX(user_id) FROM users";
            try (Statement stm = con.createStatement(); 
                 ResultSet rs = stm.executeQuery(getMaxIdQuery)) {
                if (rs.next() && rs.getObject(1) != null) {
                    nextUserId = rs.getInt(1) + 1; 
                }
            }

            // Step 1: Insert login credentials into 'users' table
            String userQuery = "INSERT INTO users (user_id, email, password, role, status) VALUES (?, ?, ?, 'Teacher', ?)";
            PreparedStatement userPst = con.prepareStatement(userQuery);
            userPst.setInt(1, nextUserId); 
            userPst.setString(2, teacher.getEmail());
            userPst.setString(3, teacher.getPassword());
            userPst.setString(4, teacher.getStatus());
            
            if (userPst.executeUpdate() == 0) {
                con.rollback(); 
                return false;
            }

            // Step 2: Insert professional details into 'teacher' table
            String currentDateTime = DateandTimeConnection.getInstance().getCurrentDateTime();            
            String teacherQuery = "INSERT INTO teacher (teacher_id, user_id, name, nic, birthday, contact_number, address, gender, salary, registration_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement teacherPst = con.prepareStatement(teacherQuery);
            teacherPst.setString(1, teacher.getTeacherId());
            teacherPst.setInt(2, nextUserId); 
            teacherPst.setString(3, teacher.getName());
            teacherPst.setString(4, teacher.getNic());
            teacherPst.setString(5, teacher.getBirthday());
            teacherPst.setString(6, teacher.getContactNumber());
            teacherPst.setString(7, teacher.getAddress());
            teacherPst.setString(8, teacher.getGender());
            teacherPst.setDouble(9, teacher.getSalary()); 
            teacherPst.setString(10, currentDateTime);

            if (teacherPst.executeUpdate() > 0) {
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

    // 3. Retrieve all teachers for JTable
    public List<TeacherEntity> getAllTeachers() {
        List<TeacherEntity> teachers = new ArrayList<>();
        String query = "SELECT t.teacher_id, t.name, t.nic, t.birthday, t.contact_number, u.email, t.address, t.gender, t.salary, u.password, u.status, t.registration_date " +
                       "FROM teacher t JOIN users u ON t.user_id = u.user_id";

        try {
            Connection con = DBConnection.getInstance().getConnection();
            try (Statement stm = con.createStatement(); ResultSet rst = stm.executeQuery(query)) {
                while (rst.next()) {
                    TeacherEntity teacher = new TeacherEntity(
                        rst.getString("teacher_id"), rst.getString("name"), rst.getString("nic"),
                        rst.getString("birthday"), rst.getString("contact_number"), rst.getString("email"),
                        rst.getString("address"), rst.getString("gender"), rst.getDouble("salary"),
                        rst.getString("password"), rst.getString("status")
                    );
                    teacher.setRegistrationDate(rst.getString("registration_date"));
                    teachers.add(teacher);
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        return teachers;
    }

    // 4. Search Teacher by ID
    public TeacherEntity searchTeacher(String teacherId) {
        String query = "SELECT t.teacher_id, t.name, t.nic, t.birthday, t.contact_number, u.email, t.address, t.gender, t.salary, u.password, u.status " +
                       "FROM teacher t JOIN users u ON t.user_id = u.user_id WHERE t.teacher_id = ?";
        
        try {
            Connection con = DBConnection.getInstance().getConnection();
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setString(1, teacherId);
                try (ResultSet rst = pst.executeQuery()) {
                    if (rst.next()) {
                        return new TeacherEntity(
                            rst.getString("teacher_id"), rst.getString("name"), rst.getString("nic"),
                            rst.getString("birthday"), rst.getString("contact_number"), rst.getString("email"),
                            rst.getString("address"), rst.getString("gender"), rst.getDouble("salary"),
                            rst.getString("password"), rst.getString("status")
                        );
                    }
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        return null; 
    }

    // 5. Update Teacher Data
    public boolean updateTeacher(TeacherEntity teacher) {
        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false); 

            String userQuery = "UPDATE users SET email=?, password=?, status=? WHERE user_id = (SELECT user_id FROM teacher WHERE teacher_id=?)";
            PreparedStatement userPst = con.prepareStatement(userQuery);
            userPst.setString(1, teacher.getEmail());
            userPst.setString(2, teacher.getPassword());
            userPst.setString(3, teacher.getStatus());
            userPst.setString(4, teacher.getTeacherId());
            userPst.executeUpdate();

            String teacherQuery = "UPDATE teacher SET name=?, nic=?, birthday=?, contact_number=?, address=?, gender=?, salary=? WHERE teacher_id=?";
            PreparedStatement teacherPst = con.prepareStatement(teacherQuery);
            teacherPst.setString(1, teacher.getName());
            teacherPst.setString(2, teacher.getNic());
            teacherPst.setString(3, teacher.getBirthday());
            teacherPst.setString(4, teacher.getContactNumber());
            teacherPst.setString(5, teacher.getAddress());
            teacherPst.setString(6, teacher.getGender());
            teacherPst.setDouble(7, teacher.getSalary());
            teacherPst.setString(8, teacher.getTeacherId());
            
            if (teacherPst.executeUpdate() > 0) {
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

    // 6. Delete Teacher 
    public boolean deleteTeacher(String teacherId) {
        String query = "DELETE FROM users WHERE user_id = (SELECT user_id FROM teacher WHERE teacher_id = ?)";
        try {
            Connection con = DBConnection.getInstance().getConnection();
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setString(1, teacherId);
                return pst.executeUpdate() > 0; 
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}