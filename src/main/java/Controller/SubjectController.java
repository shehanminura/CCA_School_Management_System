package Controller;

import Model.dao.AdminSubjectDAO;
import Model.entity.SubjectEntity;
import View.SubjectPanel;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class SubjectController {

    private final SubjectPanel view;
    private final AdminSubjectDAO dao;

    public SubjectController(SubjectPanel view) {
        this.view = view;
        this.dao = new AdminSubjectDAO();
    }

    public void loadSubjectTable() {
        try {
            List<SubjectEntity> list = dao.getAllSubjects();

            String[] columns = {"Subject ID", "Subject Name"};
            DefaultTableModel model = new DefaultTableModel(columns, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; // Cell edit වීම වැළැක්වීම
                }
            };

            if (list != null) {
                for (SubjectEntity sub : list) {
                    model.addRow(new Object[]{
                        sub.getSubjectId(), 
                        sub.getSubjectName()
                    });
                }
            }

            // SubjectPanel එකේ jTable එකට Model එක Set කිරීම
            view.getTable().setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}