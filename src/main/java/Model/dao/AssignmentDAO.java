package Model.dao;

import DBConnection.DBConnection;
import Model.entity.AssignmentEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AssignmentDAO {

    // 1. Auto ID generate කිරීම (Format: CCASA001)
    public String generateNextAssignmentId() {
        String nextId = "CCASA001";
        String query = "SELECT assignment_id FROM assignment ORDER BY assignment_id DESC LIMIT 1";
        try {
            Connection con = DBConnection.getInstance().getConnection();
            try (Statement stm = con.createStatement(); ResultSet rst = stm.executeQuery(query)) {
                if (rst.next()) {
                    String lastId = rst.getString("assignment_id");
                    // CCASA කොටස ඉවත් කර අංකය පමණක් ලබා ගෙන 1කින් වැඩි කිරීම
                    int numericPart = Integer.parseInt(lastId.replace("CCASA", ""));
                    numericPart++;
                    nextId = String.format("CCASA%03d", numericPart);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nextId;
    }

    // 2. Teacher විසින් Assignment එක Link එක සමඟ Add කිරීම
    public boolean addAssignment(AssignmentEntity entity) {
        String query = "INSERT INTO assignment (assignment_id, subject_id, title, description, assignment_link, due_date) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            Connection con = DBConnection.getInstance().getConnection();
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setString(1, entity.getAssignmentId());
                pst.setString(2, entity.getSubjectId());
                pst.setString(3, entity.getTitle());
                pst.setString(4, entity.getDescription());
                pst.setString(5, entity.getAssignmentLink());
                pst.setString(6, entity.getDueDate());
                return pst.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 3. Students ලාට පෙන්වීම සඳහා සියලුම Assignments ලබා ගැනීම
    public List<AssignmentEntity> getAllAssignments() {
        List<AssignmentEntity> list = new ArrayList<>();
        String query = "SELECT * FROM assignment";
        try {
            Connection con = DBConnection.getInstance().getConnection();
            try (Statement stm = con.createStatement(); ResultSet rst = stm.executeQuery(query)) {
                while (rst.next()) {
                    list.add(new AssignmentEntity(
                        rst.getString("assignment_id"),
                        rst.getString("subject_id"),
                        rst.getString("title"),
                        rst.getString("description"),
                        rst.getString("assignment_link"),
                        rst.getString("due_date")
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}