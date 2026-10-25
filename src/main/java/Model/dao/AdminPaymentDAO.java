package Model.dao;

import DBConnection.DBConnection;
import Model.dto.PaymentDto;
import Model.entity.PaymentEntity;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminPaymentDAO {

    public String generateNextStatusId() {
        String nextId = "FST001";
        String query = "SELECT status_id FROM student_fee_status ORDER BY status_id DESC LIMIT 1";
        try {
            Connection con = DBConnection.getInstance().getConnection();
            try (Statement stm = con.createStatement(); ResultSet rst = stm.executeQuery(query)) {
                if (rst.next()) {
                    String lastId = rst.getString("status_id");
                    int numericPart = Integer.parseInt(lastId.replace("FST", ""));
                    numericPart++;
                    nextId = String.format("FST%03d", numericPart);
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        return nextId;
    }

    public boolean addPaymentStatus(PaymentEntity entity) {
        String query = "INSERT INTO student_fee_status (status_id, student_id, class_id, fee_status, remark, updated_date) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            Connection con = DBConnection.getInstance().getConnection();
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setString(1, entity.getStatusId());
                pst.setString(2, entity.getStudentId());
                pst.setString(3, entity.getClassId());
                pst.setString(4, entity.getFeeStatus());
                pst.setString(5, entity.getRemark());
                pst.setString(6, entity.getUpdatedDate()); // දිනය පමණක් (yyyy-MM-dd)
                return pst.executeUpdate() > 0;
            }
        } catch (Exception e) { e.printStackTrace(); return false; }
    }

    public PaymentDto searchPayment(String studentId) {
        String query = "SELECT sfs.*, s.name, c.class_name FROM student_fee_status sfs " +
                       "JOIN student s ON sfs.student_id = s.student_id " +
                       "JOIN class c ON sfs.class_id = c.class_id " +
                       "WHERE sfs.student_id = ?";
        try {
            Connection con = DBConnection.getInstance().getConnection();
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setString(1, studentId);
                try (ResultSet rst = pst.executeQuery()) {
                    if (rst.next()) {
                        String dateStr = rst.getString("updated_date");
                        if (dateStr != null && dateStr.length() > 10) {
                            dateStr = dateStr.substring(0, 10);
                        }
                        return new PaymentDto(
                            rst.getString("status_id"),
                            rst.getString("student_id"),
                            rst.getString("name"),
                            rst.getString("class_id"),
                            rst.getString("class_name"),
                            rst.getString("fee_status"),
                            rst.getString("remark"),
                            dateStr
                        );
                    }
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    public boolean updatePaymentStatus(PaymentEntity entity) {
        String query = "UPDATE student_fee_status SET class_id = ?, fee_status = ?, remark = ?, updated_date = ? WHERE student_id = ?";
        try {
            Connection con = DBConnection.getInstance().getConnection();
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setString(1, entity.getClassId());
                pst.setString(2, entity.getFeeStatus());
                pst.setString(3, entity.getRemark());
                pst.setString(4, entity.getUpdatedDate());
                pst.setString(5, entity.getStudentId());
                return pst.executeUpdate() > 0;
            }
        } catch (Exception e) { e.printStackTrace(); return false; }
    }

    public boolean deletePayment(String studentId) {
        String query = "DELETE FROM student_fee_status WHERE student_id = ?";
        try {
            Connection con = DBConnection.getInstance().getConnection();
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setString(1, studentId);
                return pst.executeUpdate() > 0;
            }
        } catch (Exception e) { e.printStackTrace(); return false; }
    }

    public List<PaymentDto> getAllPaymentTableData() {
        List<PaymentDto> list = new ArrayList<>();
        String query = "SELECT sfs.status_id, s.student_id, s.name, sfs.class_id, c.class_name, " +
                       "sfs.fee_status, sfs.remark, sfs.updated_date " +
                       "FROM student_fee_status sfs " +
                       "JOIN student s ON sfs.student_id = s.student_id " +
                       "JOIN class c ON sfs.class_id = c.class_id";
        try {
            Connection con = DBConnection.getInstance().getConnection();
            try (Statement stm = con.createStatement(); ResultSet rst = stm.executeQuery(query)) {
                while (rst.next()) {
                    String dateStr = rst.getString("updated_date");
                    if (dateStr != null && dateStr.length() > 10) {
                        dateStr = dateStr.substring(0, 10); // Time එක කපා හැරීම
                    }
                    list.add(new PaymentDto(
                        rst.getString("status_id"),
                        rst.getString("student_id"),
                        rst.getString("name"),
                        rst.getString("class_id"),
                        rst.getString("class_name"),
                        rst.getString("fee_status"),
                        rst.getString("remark"),
                        dateStr
                    ));
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public String getClassIdByName(String className) {
        String classId = "CCAC001";
        try {
            Connection con = DBConnection.getInstance().getConnection();
            try (PreparedStatement pst = con.prepareStatement("SELECT class_id FROM class WHERE class_name = ?")) {
                pst.setString(1, className);
                try (ResultSet rst = pst.executeQuery()) {
                    if (rst.next()) classId = rst.getString("class_id");
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        return classId;
    }

    public List<String> getStudentIds() {
        List<String> list = new ArrayList<>();
        try {
            Connection con = DBConnection.getInstance().getConnection();
            try (Statement stm = con.createStatement(); ResultSet rst = stm.executeQuery("SELECT student_id FROM student")) {
                while (rst.next()) list.add(rst.getString("student_id"));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public List<String> getClassNames() {
        List<String> list = new ArrayList<>();
        try {
            Connection con = DBConnection.getInstance().getConnection();
            try (Statement stm = con.createStatement(); ResultSet rst = stm.executeQuery("SELECT class_name FROM class")) {
                while (rst.next()) list.add(rst.getString("class_name"));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }
    //get student email 
    public String getStudentEmailById(String studentId) {
        String email = null;
        // student email get users table using JOIN Query
        String query = "SELECT u.email FROM users u " +
                       "INNER JOIN student s ON u.user_id = s.user_id " +
                       "WHERE s.student_id = ?";
        try {
            Connection con = DBConnection.getInstance().getConnection();
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setString(1, studentId);
                try (ResultSet rst = pst.executeQuery()) {
                    if (rst.next()) {
                        email = rst.getString("email");
                    }
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        return email;
    }
}