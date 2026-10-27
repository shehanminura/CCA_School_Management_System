package Controller;

import Model.dao.AdminSubjectDAO;
import Model.dao.AssignmentDAO;
import Model.dto.AssignmentDto;
import Model.entity.AssignmentEntity;
import Model.entity.SubjectEntity;
import View.StudentAssignmentPanel;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class StudentAssignmentController {

    private final StudentAssignmentPanel view;
    private final AssignmentDAO dao;
    private final AdminSubjectDAO subjectDao;

    public StudentAssignmentController(StudentAssignmentPanel view) {
        this.view = view;
        this.dao = new AssignmentDAO();
        this.subjectDao = new AdminSubjectDAO();
    }

    public void loadSubjectIds() {
        try {
            List<SubjectEntity> subjects = subjectDao.getAllSubjects();
            view.getSubjectComboBox().removeAllItems();
            view.getSubjectComboBox().addItem("-- Subject --");
            for (SubjectEntity sub : subjects) {
                view.getSubjectComboBox().addItem(sub.getSubjectId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadTableData() {
        try {
            List<AssignmentEntity> list = dao.getAllAssignments();
            String[] columns = {"Assignment ID", "Subject ID", "Title", "Due Date", "Link", "Description"};
            DefaultTableModel model = new DefaultTableModel(columns, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; // සිසුන්ට Table එක Edit කළ නොහැක
                }
            };

            for (AssignmentEntity a : list) {
                model.addRow(new Object[]{
                    a.getAssignmentId(),
                    a.getSubjectId(),
                    a.getTitle(),
                    a.getDueDate(),
                    a.getAssignmentLink(),
                    a.getDescription()
                });
            }
            view.getTable().setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchAssignment() {
        try {
            String searchId = view.getSearchId().trim();
            if (searchId.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Please enter an Assignment ID to search!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String query = "SELECT * FROM assignment WHERE assignment_id = ?";
            java.sql.Connection con = DBConnection.DBConnection.getInstance().getConnection();
            try (java.sql.PreparedStatement pst = con.prepareStatement(query)) {
                pst.setString(1, searchId);
                try (java.sql.ResultSet rst = pst.executeQuery()) {
                    if (rst.next()) {
                        AssignmentDto dto = new AssignmentDto(
                            rst.getString("assignment_id"),
                            rst.getString("subject_id"),
                            rst.getString("title"),
                            rst.getString("description"),
                            rst.getString("assignment_link"),
                            rst.getString("due_date")
                        );
                        view.setAssignmentData(dto);
                        JOptionPane.showMessageDialog(view, "Assignment Found Successfully!", "Found", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(view, "Assignment not found!", "Not Found", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}