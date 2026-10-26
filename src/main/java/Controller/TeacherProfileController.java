package Controller;

import Model.dao.AdminTeacherDAO;
import Model.entity.TeacherEntity;
import View.TeacherProfilePanel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class TeacherProfileController {

    public static String loggedInTeacherEmail;

    private final TeacherProfilePanel view;
    private final AdminTeacherDAO dao;
    private TeacherEntity currentTeacher;

    public TeacherProfileController(TeacherProfilePanel view) {
        this.view = view;
        this.dao = new AdminTeacherDAO();
        loadProfileData();
    }

    public final void loadProfileData() {
        try {
            if (loggedInTeacherEmail != null && !loggedInTeacherEmail.trim().isEmpty()) {
                // get datat use current loging email
                currentTeacher = dao.searchTeacherByEmail(loggedInTeacherEmail.trim());

                if (currentTeacher != null) {
                    view.setTeacherId(currentTeacher.getTeacherId());
                    view.setName(currentTeacher.getName());
                    view.setBirthday(currentTeacher.getBirthday());
                    view.setEmail(currentTeacher.getEmail());
                    view.setAddress(currentTeacher.getAddress());
                    view.setContactNumber(currentTeacher.getContactNumber());
                    view.setGender(currentTeacher.getGender());
                    view.setStatus(currentTeacher.getStatus());
                    view.setNic(currentTeacher.getNic());
                    view.setSalary(currentTeacher.getSalary());
                    
                    String regDate = currentTeacher.getRegistrationDate();
                    view.setRegistrationDate((regDate != null && !regDate.isEmpty()) ? regDate : "N/A");
                } else {
                    JOptionPane.showMessageDialog(view, "Teacher profile data not found!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Error loading profile: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void changePassword() {
        if (currentTeacher == null) {
            JOptionPane.showMessageDialog(view, "No Teacher profile loaded!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JPasswordField oldPass = new JPasswordField();
        if (JOptionPane.showConfirmDialog(view, oldPass, "Enter Current Password:", JOptionPane.OK_CANCEL_OPTION) != JOptionPane.OK_OPTION) return;

        if (!new String(oldPass.getPassword()).equals(currentTeacher.getPassword())) {
            JOptionPane.showMessageDialog(view, "Incorrect Current Password!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JPasswordField newPass = new JPasswordField();
        if (JOptionPane.showConfirmDialog(view, newPass, "Enter New Password:", JOptionPane.OK_CANCEL_OPTION) != JOptionPane.OK_OPTION) return;

        String newPassword = new String(newPass.getPassword()).trim();
        if (newPassword.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Password cannot be empty!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        currentTeacher.setPassword(newPassword);
        
        if (dao.updateTeacher(currentTeacher)) { 
            JOptionPane.showMessageDialog(view, "Password Changed Successfully!");
        } else {
            JOptionPane.showMessageDialog(view, "Failed to change password!");
        }
    }
}