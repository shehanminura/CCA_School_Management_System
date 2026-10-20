package Model.dao;

import Model.entity.ClassEntity;
import DBConnection.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminClassDAO {

    public String generateNextClassId() {
        String nextId = "CCAC001";
        String query = "SELECT class_id FROM class ORDER BY class_id DESC LIMIT 1";
        try {
            Connection con = DBConnection.getInstance().getConnection();
            try (Statement stm = con.createStatement(); ResultSet rst = stm.executeQuery(query)) {
                if (rst.next()) {
                    String lastId = rst.getString("class_id");
                    int numericPart = Integer.parseInt(lastId.replace("CCAC", ""));
                    numericPart++;
                    nextId = String.format("CCAC%03d", numericPart);
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        return nextId;
    }

    public boolean addClass(ClassEntity classEntity) {
        String query = "INSERT INTO class (class_id, class_name) VALUES (?, ?)";
        try {
            Connection con = DBConnection.getInstance().getConnection();
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setString(1, classEntity.getClassId());
                pst.setString(2, classEntity.getClassName());
                return pst.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<ClassEntity> getAllClasses() {
        List<ClassEntity> classList = new ArrayList<>();
        String query = "SELECT * FROM class";
        try {
            Connection con = DBConnection.getInstance().getConnection();
            try (Statement stm = con.createStatement(); ResultSet rst = stm.executeQuery(query)) {
                while (rst.next()) {
                    classList.add(new ClassEntity(
                        rst.getString("class_id"), 
                        rst.getString("class_name")
                    ));
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        return classList;
    }

    public ClassEntity searchClass(String classId) {
        String query = "SELECT * FROM class WHERE class_id = ?";
        try {
            Connection con = DBConnection.getInstance().getConnection();
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setString(1, classId);
                try (ResultSet rst = pst.executeQuery()) {
                    if (rst.next()) {
                        return new ClassEntity(
                            rst.getString("class_id"), 
                            rst.getString("class_name")
                        );
                    }
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    public boolean updateClass(ClassEntity classEntity) {
        String query = "UPDATE class SET class_name = ? WHERE class_id = ?";
        try {
            Connection con = DBConnection.getInstance().getConnection();
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setString(1, classEntity.getClassName());
                pst.setString(2, classEntity.getClassId());
                return pst.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteClass(String classId) {
        String query = "DELETE FROM class WHERE class_id = ?";
        try {
            Connection con = DBConnection.getInstance().getConnection();
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setString(1, classId);
                return pst.executeUpdate() > 0;
            }
        } catch (Exception e) { e.printStackTrace(); return false; }
    }
}