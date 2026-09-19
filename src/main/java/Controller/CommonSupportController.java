package Controller;

import View.Startview;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import java.awt.Component;

public class CommonSupportController {

    private Component view;

    public CommonSupportController(Component view) {
        this.view = view;
    }

    public CommonSupportController(Startview aThis) {
        this.view = view;    }

    // View එකෙන් ලැබෙන String දත්ත පමණක් භාරගැනීම
    public void processSupportRequest(String userInfo, String issue, String attachmentPath) {
        
        if (userInfo.isEmpty() || issue.isEmpty()) {
            JOptionPane.showMessageDialog(view, "ID/Email and Message are strictly required!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(view, "Submitting your support request... Please wait.");

        // Email Service
        new Thread(() -> {
            boolean isSent = CommonSupportEmailService.sendSupportRequest(userInfo, issue, attachmentPath);
            
            SwingUtilities.invokeLater(() -> {
                if (isSent) {
                    JOptionPane.showMessageDialog(view, "Support ticket submitted successfully! The administration will contact you shortly.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(view, "Failed to send the request. Please check your internet connection.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
        }).start();
    }
}