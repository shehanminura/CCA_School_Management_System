package Controller;

import Model.dao.LoginDAO;
import Model.entity.UserEntity;
import View.Loginview;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {
    
    private Loginview view;
    private LoginDAO model; // මෙතැන DAO එක Model එක ලෙස භාවිත වේ

    // Constructor එක ඇතුළේ View එකයි Model එකයි දෙකම භාරගන්නවා
    public LoginController(Loginview view, LoginDAO model) {
        this.view = view;
        this.model = model;
        
        // View එකේ Login බට්න් එක ඔබන එක අල්ලගන්න Listener එක Set කරනවා
        this.view.addLoginListener(new LoginListener());
    }

    // බට්න් එක එබුවම සිදුවෙන දේවල් තියෙන්නේ මේ Inner Class එක ඇතුළේ
    class LoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String email = view.getEmail();
            String password = view.getPassword();

            if (email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Please enter both Email and Password!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // 1. DAO (Model) හරහා Database එක චෙක් කිරීම
            UserEntity user = model.authenticateUser(email, password);

            // 2. ලොගින් එක සාර්ථක නම්
            if (user != null) {
                
                if ("Deactivated".equals(user.getStatus())) {
                    JOptionPane.showMessageDialog(view, "Your account is deactivated. Please contact Administration.", "Account Disabled", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String role = user.getRole();
                view.dispose(); // සාර්ථක නම් ලොගින් එක වහනවා

                // 3. Role එකට අදාළ Dashboard එක විවෘත කිරීම
                switch (role) {
                    case "Admin":
                        JOptionPane.showMessageDialog(null, "Welcome Admin!");
                         
                        View.DashbordAdmin adminDash = new View.DashbordAdmin();
                        adminDash.setVisible(true);
                        break;
                        
                    case "Teacher":
                        JOptionPane.showMessageDialog(null, "Welcome Teacher!");
                        
                        View.DashbordTeacher dashbordTeacher = new View.DashbordTeacher();
                        dashbordTeacher.setVisible(true);
                        break;
                        
                    case "Student":
                        JOptionPane.showMessageDialog(null, "Welcome Student!");
                        
                        View.DashbordStudent dashbordStudent = new View.DashbordStudent();
                        dashbordStudent.setVisible(true);
                        break;
                        
                    default:
                        JOptionPane.showMessageDialog(null, "Unknown Role Detected!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                
            } else {
                // 4. දත්ත වැරදි නම්
                JOptionPane.showMessageDialog(view, "Invalid Email or Password!", "Login Failed", JOptionPane.ERROR_MESSAGE);
                view.clearPassword(); 
            }
        }

        private void dispose() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }
}