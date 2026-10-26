package Controller;

import Model.dao.AdminDAO;
import Model.entity.AdminEntity;
import View.AdminProfilePanel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class AdminProfileController {

    // 🔴 ලොග් වෙන Admin ගේ Email එක මතක තබා ගන්නා Static Variable එක
    public static String loggedInEmail;

    private final AdminProfilePanel view;
    private final AdminDAO dao;
    private AdminEntity currentAdmin;

    public AdminProfileController(AdminProfilePanel view) {
        this.view = view;
        this.dao = new AdminDAO();
        loadProfileData();
    }

    public final void loadProfileData() {
        try {
            // ලොග් වුණු Email එකෙන්ම පමණක් ඩේටාබේස් එකෙන් විස්තර ලබාගැනීම
            if (loggedInEmail != null && !loggedInEmail.trim().isEmpty()) {
                currentAdmin = dao.getAdminProfileData(loggedInEmail.trim());
            }

            if (currentAdmin != null) {
                view.setAdminId(currentAdmin.getAdminId());
                view.setName(currentAdmin.getName());
                view.setNic(currentAdmin.getNic());
                view.setBirthday(currentAdmin.getBirthday());
                view.setEmail(currentAdmin.getEmail());
                view.setAddress(currentAdmin.getAddress());
                view.setContactNumber(currentAdmin.getContactNumber());
                view.setSalary(String.format("Rs. %.2f", currentAdmin.getSalary()));
                view.setGender(currentAdmin.getGender());
                view.setStatus(currentAdmin.getStatus());
                view.setRegistrationDate(currentAdmin.getRegistrationDate() != null ? currentAdmin.getRegistrationDate() : "N/A");
            } else {
                JOptionPane.showMessageDialog(view, "Logged-in Admin details not found! (Email: " + loggedInEmail + ")", "Not Found", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Error loading profile: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void changePassword() {
        if (currentAdmin == null) {
            JOptionPane.showMessageDialog(view, "No Admin profile loaded!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JPasswordField oldPassField = new JPasswordField();
        int okCxl1 = JOptionPane.showConfirmDialog(view, oldPassField, "Enter Current Password:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (okCxl1 != JOptionPane.OK_OPTION) return;

        String oldPass = new String(oldPassField.getPassword());
        if (!oldPass.equals(currentAdmin.getPassword())) {
            JOptionPane.showMessageDialog(view, "Incorrect Current Password!", "Authentication Failed", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JPasswordField newPassField = new JPasswordField();
        int okCxl2 = JOptionPane.showConfirmDialog(view, newPassField, "Enter New Password:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (okCxl2 != JOptionPane.OK_OPTION) return;

        String newPass = new String(newPassField.getPassword()).trim();
        if (newPass.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Password cannot be empty!", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (dao.updateAdminPassword(currentAdmin.getAdminId(), newPass)) {
            currentAdmin.setPassword(newPass);
            JOptionPane.showMessageDialog(view, "Password Changed Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(view, "Failed to update password!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}