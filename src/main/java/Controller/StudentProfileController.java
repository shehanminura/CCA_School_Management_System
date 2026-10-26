package Controller;

import Model.dao.AdminStudentDAO;
import Model.entity.StudentEntity;
import View.StudentProfilePanel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class StudentProfileController {

    // ලොග් වූ Student ගේ ඊමේල් එක මෙහි තාවකාලිකව ගබඩා කෙරේ
    public static String loggedInStudentEmail;

    private final StudentProfilePanel view;
    private final AdminStudentDAO dao;
    private StudentEntity currentStudent;

    public StudentProfileController(StudentProfilePanel view) {
        this.view = view;
        this.dao = new AdminStudentDAO(); // ඔබේ පවතින DAO එකම භාවිතා කරයි
        loadProfileData();
    }

    public final void loadProfileData() {
        if (loggedInStudentEmail != null && !loggedInStudentEmail.trim().isEmpty()) {
            // ඔබේ AdminStudentDAO හි ඇති Email එකෙන් Search කරන Method එක (අපි කලින් එකතු කළ එක)
            currentStudent = dao.searchStudentByEmail(loggedInStudentEmail.trim());

            if (currentStudent != null) {
                view.setStudentId(currentStudent.getStudentId());
                view.setName(currentStudent.getName());
                view.setBirthday(currentStudent.getBirthday());
                view.setEmail(currentStudent.getEmail());
                view.setAddress(currentStudent.getAddress());
                view.setContactNumber(currentStudent.getContactNumber());
                view.setGender(currentStudent.getGender());
                
                String className = currentStudent.getClassName();
                view.setClassName((className != null && !className.isEmpty()) ? className : "N/A");
                
                view.setStatus(currentStudent.getStatus());
                
                String regDate = currentStudent.getRegistrationDate();
                view.setRegistrationDate((regDate != null && !regDate.isEmpty()) ? regDate : "N/A");
            } else {
                JOptionPane.showMessageDialog(view, "Student profile data not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Password එක Change කිරීම
    public void changePassword() {
        if (currentStudent == null) {
            JOptionPane.showMessageDialog(view, "No Student profile loaded!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JPasswordField oldPassField = new JPasswordField();
        int okCxl1 = JOptionPane.showConfirmDialog(view, oldPassField, "Enter Current Password:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (okCxl1 != JOptionPane.OK_OPTION) return;

        String oldPass = new String(oldPassField.getPassword());
        if (!oldPass.equals(currentStudent.getPassword())) {
            JOptionPane.showMessageDialog(view, "Incorrect Current Password!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JPasswordField newPassField = new JPasswordField();
        int okCxl2 = JOptionPane.showConfirmDialog(view, newPassField, "Enter New Password:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (okCxl2 != JOptionPane.OK_OPTION) return;

        String newPass = new String(newPassField.getPassword()).trim();
        if (newPass.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Password cannot be empty!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Entity එකේ Password එක අලුත් කර, දැනටමත් තියෙන updateStudent() එක කෝල් කිරීම
        currentStudent.setPassword(newPass);
        
        if (dao.updateStudent(currentStudent)) {
            JOptionPane.showMessageDialog(view, "Password Changed Successfully!");
        } else {
            JOptionPane.showMessageDialog(view, "Failed to change password!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}