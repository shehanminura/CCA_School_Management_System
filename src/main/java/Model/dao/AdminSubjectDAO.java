package Model.dao;

import Model.entity.SubjectEntity;
import DBConnection.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminSubjectDAO {

    public String generateNextSubjectId() {
        String nextId = "CCASU001";
        String query = "SELECT subject_id FROM subject ORDER BY subject_id DESC LIMIT 1";
        try {
            Connection con = DBConnection.getInstance().getConnection();
            try (Statement stm = con.createStatement(); ResultSet rst = stm.executeQuery(query)) {
                if (rst.next()) {
                    String lastId = rst.getString("subject_id");
                    // CCASU යන්න ඉවත් කර අංකය පමණක් ලබා ගැනීම
                    int numericPart = Integer.parseInt(lastId.replace("CCASU", ""));
                    numericPart++;
                    nextId = String.format("CCASU%03d", numericPart);
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        return nextId;
    }

    public boolean addSubject(SubjectEntity subjectEntity) {
        String query = "INSERT INTO subject (subject_id, subject_name) VALUES (?, ?)";
        try {
            Connection con = DBConnection.getInstance().getConnection();
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setString(1, subjectEntity.getSubjectId());
                pst.setString(2, subjectEntity.getSubjectName());
                return pst.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<SubjectEntity> getAllSubjects() {
        List<SubjectEntity> subjectList = new ArrayList<>();
        String query = "SELECT * FROM subject";
        try {
            Connection con = DBConnection.getInstance().getConnection();
            try (Statement stm = con.createStatement(); ResultSet rst = stm.executeQuery(query)) {
                while (rst.next()) {
                    subjectList.add(new SubjectEntity(
                        rst.getString("subject_id"), 
                        rst.getString("subject_name")
                    ));
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        return subjectList;
    }

    public SubjectEntity searchSubject(String subjectId) {
        String query = "SELECT * FROM subject WHERE subject_id = ?";
        try {
            Connection con = DBConnection.getInstance().getConnection();
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setString(1, subjectId);
                try (ResultSet rst = pst.executeQuery()) {
                    if (rst.next()) {
                        return new SubjectEntity(
                            rst.getString("subject_id"), 
                            rst.getString("subject_name")
                        );
                    }
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    public boolean updateSubject(SubjectEntity subjectEntity) {
        String query = "UPDATE subject SET subject_name = ? WHERE subject_id = ?";
        try {
            Connection con = DBConnection.getInstance().getConnection();
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setString(1, subjectEntity.getSubjectName());
                pst.setString(2, subjectEntity.getSubjectId());
                return pst.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteSubject(String subjectId) {
        String query = "DELETE FROM subject WHERE subject_id = ?";
        try {
            Connection con = DBConnection.getInstance().getConnection();
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setString(1, subjectId);
                return pst.executeUpdate() > 0;
            }
        } catch (Exception e) { e.printStackTrace(); return false; }
    }
}