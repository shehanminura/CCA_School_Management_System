package Controller;

import Model.dto.AdminDto;
import MailConnection.MailConnection;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Session;
import DateandTimeConnection.DateandTimeConnection;

public class AddAdminEmailService {
    
    // --- Welcome Email (Admin - Executive Slate Theme) ---
    public static void sendWelcomeEmail(AdminDto admin) {
        try {
            Session session = MailConnection.getInstance().getSession();
            String senderEmail = MailConnection.getInstance().getSenderEmail();
            
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(admin.getEmail()));
            message.setSubject("Official Administrator Registration - Central College Anuradhapura");
            
            String emailBody = "<html>"
            + "<body style='margin:0; padding:0; background-color:#F4F6F7; font-family:Arial, Helvetica, sans-serif; color:#333333;'>"
            + "<div style='width:100%; padding:30px 0;'>"
            + "<table align='center' width='650' cellpadding='0' cellspacing='0' style='width:650px; max-width:650px; background-color:#ffffff; border-collapse:collapse; border-radius:12px; overflow:hidden; box-shadow:0 4px 15px rgba(0,0,0,0.08);'>"
            
            // HEADER (Executive Slate/Charcoal)
            + "<tr>"
            + "<td style='background-color:#2C3E50; padding:30px 25px; text-align:center;'>"
            + "<img src='https://anucentralcollege.com/og-image.jpg' alt='Central College Anuradhapura Logo' style='width:85px; height:85px; border-radius:50%; background-color:#ffffff; padding:6px; border:3px solid #D4AF37;'>"
            + "<h1 style='margin:15px 0 5px 0; color:#ffffff; font-size:24px; letter-spacing:0.5px;'>CENTRAL COLLEGE ANURADHAPURA</h1>"
            + "<p style='margin:0; color:#F5D76E; font-size:14px; font-weight:bold; letter-spacing:0.5px;'>ADMINISTRATION PORTAL</p>"
            + "</td>"
            + "</tr>"
            
            // GOLD LINE
            + "<tr><td style='height:5px; background-color:#D4AF37;'></td></tr>"
            
            // GREETING
            + "<tr>"
            + "<td style='padding:35px 40px 10px 40px;'>"
            + "<p style='margin:0 0 12px 0; font-size:16px;'>Dear <strong style='color:#2C3E50;'>" + admin.getName() + "</strong>,</p>"
            + "<h2 style='margin:0 0 15px 0; color:#2C3E50; font-size:23px;'>Administrator Registration Confirmed</h2>"
            + "<p style='margin:0; color:#555555; font-size:15px; line-height:1.7;'>We are pleased to inform you that your executive registration with the <strong>Central College Anuradhapura Administration Portal</strong> has been successfully completed.</p>"
            + "</td>"
            + "</tr>"
            
            // ADMIN ID CARD (Light Slate)
            + "<tr>"
            + "<td style='padding:20px 40px;'>"
            + "<div style='background-color:#EAECEE; border:1px solid #D5DBDB; border-left:5px solid #2C3E50; border-radius:7px; padding:18px 20px;'>"
            + "<p style='margin:0; font-size:12px; color:#566573; font-weight:bold; letter-spacing:1px;'>YOUR ADMIN ID</p>"
            + "<p style='margin:7px 0 0 0; font-size:25px; font-weight:bold; color:#2C3E50;'>" + admin.getAdminId() + "</p>"
            + "</div>"
            + "</td>"
            + "</tr>"
            
            // DETAILS TITLE
            + "<tr>"
            + "<td style='padding:5px 40px 12px 40px;'>"
            + "<h3 style='margin:0; color:#2C3E50; font-size:18px;'>Registration Details</h3>"
            + "<div style='width:45px; height:3px; background-color:#D4AF37; margin-top:7px;'></div>"
            + "</td>"
            + "</tr>"
            
            // DETAILS TABLE (Slate Theme)
            + "<tr>"
            + "<td style='padding:5px 40px 25px 40px;'>"
            + "<table width='100%' cellpadding='0' cellspacing='0' style='border-collapse:collapse; font-size:14px;'>"
            + "<tr>"
            + "<td style='padding:12px; background-color:#F8F9F9; border-bottom:1px solid #E5E8E8; width:40%; font-weight:bold; color:#212F3C;'>Full Name</td>"
            + "<td style='padding:12px; border-bottom:1px solid #E5E8E8;'>" + admin.getName() + "</td>"
            + "</tr>"
            + "<tr>"
            + "<td style='padding:12px; background-color:#F8F9F9; border-bottom:1px solid #E5E8E8; font-weight:bold; color:#212F3C;'>NIC Number</td>"
            + "<td style='padding:12px; border-bottom:1px solid #E5E8E8;'>" + admin.getNic() + "</td>"
            + "</tr>"
            + "<tr>"
            + "<td style='padding:12px; background-color:#F8F9F9; border-bottom:1px solid #E5E8E8; font-weight:bold; color:#212F3C;'>Date of Birth</td>"
            + "<td style='padding:12px; border-bottom:1px solid #E5E8E8;'>" + admin.getBirthday() + "</td>"
            + "</tr>"
            + "<tr>"
            + "<td style='padding:12px; background-color:#F8F9F9; border-bottom:1px solid #E5E8E8; font-weight:bold; color:#212F3C;'>Gender</td>"
            + "<td style='padding:12px; border-bottom:1px solid #E5E8E8;'>" + admin.getGender() + "</td>"
            + "</tr>"
            + "<tr>"
            + "<td style='padding:12px; background-color:#F8F9F9; border-bottom:1px solid #E5E8E8; font-weight:bold; color:#212F3C;'>Contact Number</td>"
            + "<td style='padding:12px; border-bottom:1px solid #E5E8E8;'>" + admin.getContactNumber() + "</td>"
            + "</tr>"
            + "<tr>"
            + "<td style='padding:12px; background-color:#F8F9F9; border-bottom:1px solid #E5E8E8; font-weight:bold; color:#212F3C;'>Address</td>"
            + "<td style='padding:12px; border-bottom:1px solid #E5E8E8;'>" + admin.getAddress() + "</td>"
            + "</tr>"
            + "<tr>"
            + "<td style='padding:12px; background-color:#F8F9F9; border-bottom:1px solid #E5E8E8; font-weight:bold; color:#212F3C;'>Basic Salary</td>"
            + "<td style='padding:12px; border-bottom:1px solid #E5E8E8;'>Rs. " + admin.getSalary() + "</td>"
            + "</tr>"
            + "<tr>"
            + "<td style='padding:12px; background-color:#F8F9F9; border-bottom:1px solid #E5E8E8; font-weight:bold; color:#212F3C;'>Registered Email</td>"
            + "<td style='padding:12px; border-bottom:1px solid #E5E8E8;'>" + admin.getEmail() + "</td>"
            + "</tr>"
            + "<tr>"
            + "<td style='padding:12px; background-color:#F8F9F9; font-weight:bold; color:#212F3C;'>Account Status</td>"
            + "<td style='padding:12px; color:#2C3E50; font-weight:bold;'>✓ " + admin.getStatus() + "</td>"
            + "</tr>"
            + "</table>"
            + "</td>"
            + "</tr>"
            
            // LOGIN INFORMATION (Gold Accent)
            + "<tr>"
            + "<td style='padding:0 40px 25px 40px;'>"
            + "<div style='background-color:#FFF9E8; border:1px solid #E8D58A; border-radius:8px; padding:20px;'>"
            + "<h3 style='margin:0 0 15px 0; color:#6B5200; font-size:17px;'>🔐 Administrative Login Information</h3>"
            + "<p style='margin:9px 0; font-size:14px;'><strong>Admin ID:</strong> " + admin.getAdminId() + "</p>"
            + "<p style='margin:9px 0; font-size:14px;'><strong>Email:</strong> " + admin.getEmail() + "</p>"
            + "<p style='margin:9px 0; font-size:14px;'><strong>Temporary Password:</strong> <span style='background:#ffffff; padding:6px 10px; border:1px solid #D9D9D9; border-radius:4px; font-family:monospace; font-weight:bold;'>" + admin.getPassword() + "</span></p>"
            + "</div>"
            + "</td>"
            + "</tr>"
            
            // SECURITY NOTICE (Slate Accent)
            + "<tr>"
            + "<td style='padding:0 40px 25px 40px;'>"
            + "<div style='background-color:#EAECEE; border-left:4px solid #2C3E50; padding:15px 18px; border-radius:5px;'>"
            + "<p style='margin:0; color:#2C3E50; font-size:13px; line-height:1.6;'><strong style='color:#2C3E50;'>Security Notice</strong><br>As an administrator, your account holds elevated privileges. Please keep your credentials strictly confidential and change your temporary password immediately upon your first login.</p>"
            + "</div>"
            + "</td>"
            + "</tr>"
            
            // CLOSING
            + "<tr>"
            + "<td style='padding:5px 40px 30px 40px;'>"
            + "<p style='margin:0; font-size:14px; color:#555555; line-height:1.7;'>We look forward to your valuable contribution to the administration team.</p>"
            + "<p style='margin:15px 0 0 0; font-size:14px; line-height:1.7;'>Sincerely,<br><strong style='color:#2C3E50;'>Executive Board</strong><br>Central College Anuradhapura</p>"
            + "</td>"
            + "</tr>"
            
            // FOOTER (Darker Charcoal)
            + "<tr>"
            + "<td style='background-color:#1A252C; border-top:4px solid #D4AF37; padding:22px 30px; text-align:center;'>"
            + "<p style='margin:0 0 6px 0; color:#ffffff; font-size:13px; font-weight:bold;'>CENTRAL COLLEGE ANURADHAPURA</p>"
            + "<p style='margin:0; color:#ABB2B9; font-size:11px;'>Administration Portal</p>"
            + "<p style='margin:8px 0 0 0; color:#808B96; font-size:10px;'>This is an automated executive email. Please do not reply directly to this message.</p>"
            + "</td>"
            + "</tr>"
            + "</table>"
            + "</div>"
            + "</body>"
            + "</html>";
            
            message.setContent(emailBody, "text/html; charset=utf-8");
            Transport.send(message);
            System.out.println("Email Sent Successfully to: " + admin.getEmail());
            
        } catch (Exception e) {
            System.out.println("Email Sending Failed: " + e.getMessage());
        }
    }

    // --- Update Email (Admin - Executive Slate Theme) ---
    public static void sendUpdateEmail(AdminDto admin) {
        try {
            Session session = MailConnection.getInstance().getSession();
            String senderEmail = MailConnection.getInstance().getSenderEmail();
            
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(admin.getEmail()));
            message.setSubject("Account Update Notification - Central College Anuradhapura");
            
            String emailBody = "<html>"
            + "<body style='margin:0; padding:0; background-color:#F4F6F7; font-family:Arial, Helvetica, sans-serif; color:#333333;'>"
            + "<div style='width:100%; padding:30px 0;'>"
            + "<table align='center' width='650' cellpadding='0' cellspacing='0' style='width:650px; max-width:650px; background-color:#ffffff; border-collapse:collapse; border-radius:12px; overflow:hidden; box-shadow:0 4px 15px rgba(0,0,0,0.08);'>"
            
            // HEADER
            + "<tr>"
            + "<td style='background-color:#2C3E50; padding:30px 25px; text-align:center;'>"
            + "<img src='https://anucentralcollege.com/og-image.jpg' alt='Central College Anuradhapura Logo' style='width:85px; height:85px; border-radius:50%; background-color:#ffffff; padding:6px; border:3px solid #D4AF37;'>"
            + "<h1 style='margin:15px 0 5px 0; color:#ffffff; font-size:24px; letter-spacing:0.5px;'>CENTRAL COLLEGE ANURADHAPURA</h1>"
            + "<p style='margin:0; color:#F5D76E; font-size:14px; font-weight:bold; letter-spacing:0.5px;'>ADMINISTRATION PORTAL</p>"
            + "</td>"
            + "</tr>"
            
            // GOLD LINE
            + "<tr><td style='height:5px; background-color:#D4AF37;'></td></tr>"
            
            // GREETING
            + "<tr>"
            + "<td style='padding:35px 40px 10px 40px;'>"
            + "<p style='margin:0 0 12px 0; font-size:16px;'>Dear <strong style='color:#2C3E50;'>" + admin.getName() + "</strong>,</p>"
            + "<h2 style='margin:0 0 15px 0; color:#2C3E50; font-size:23px;'>Administrator Profile Updated</h2>"
            + "<p style='margin:0; color:#555555; font-size:15px; line-height:1.7;'>This is to inform you that your executive profile in the <strong>Central College Anuradhapura Administration Portal</strong> has been successfully updated.</p>"
            + "</td>"
            + "</tr>"
            
            // ADMIN ID BOX
            + "<tr>"
            + "<td style='padding:20px 40px;'>"
            + "<div style='background-color:#EAECEE; border:1px solid #D5DBDB; border-left:5px solid #2C3E50; border-radius:7px; padding:18px 20px;'>"
            + "<p style='margin:0; font-size:12px; color:#566573; font-weight:bold; letter-spacing:1px;'>ADMIN ID</p>"
            + "<p style='margin:7px 0 0 0; font-size:24px; font-weight:bold; color:#2C3E50;'>" + admin.getAdminId() + "</p>"
            + "</div>"
            + "</td>"
            + "</tr>"
            
            // UPDATE INFORMATION
            + "<tr>"
            + "<td style='padding:5px 40px 12px 40px;'>"
            + "<h3 style='margin:0; color:#2C3E50; font-size:18px;'>Updated Profile Information</h3>"
            + "<div style='width:45px; height:3px; background-color:#D4AF37; margin-top:7px;'></div>"
            + "</td>"
            + "</tr>"
            
            // DETAILS TABLE
            + "<tr>"
            + "<td style='padding:5px 40px 25px 40px;'>"
            + "<table width='100%' cellpadding='0' cellspacing='0' style='border-collapse:collapse; font-size:14px;'>"
            + "<tr><td style='padding:12px; background-color:#F8F9F9; border-bottom:1px solid #E5E8E8; width:40%; font-weight:bold; color:#212F3C;'>Full Name</td><td style='padding:12px; border-bottom:1px solid #E5E8E8;'>" + admin.getName() + "</td></tr>"
            + "<tr><td style='padding:12px; background-color:#F8F9F9; border-bottom:1px solid #E5E8E8; font-weight:bold; color:#212F3C;'>NIC Number</td><td style='padding:12px; border-bottom:1px solid #E5E8E8;'>" + admin.getNic() + "</td></tr>"
            + "<tr><td style='padding:12px; background-color:#F8F9F9; border-bottom:1px solid #E5E8E8; font-weight:bold; color:#212F3C;'>Date of Birth</td><td style='padding:12px; border-bottom:1px solid #E5E8E8;'>" + admin.getBirthday() + "</td></tr>"
            + "<tr><td style='padding:12px; background-color:#F8F9F9; border-bottom:1px solid #E5E8E8; font-weight:bold; color:#212F3C;'>Gender</td><td style='padding:12px; border-bottom:1px solid #E5E8E8;'>" + admin.getGender() + "</td></tr>"
            + "<tr><td style='padding:12px; background-color:#F8F9F9; border-bottom:1px solid #E5E8E8; font-weight:bold; color:#212F3C;'>Contact Number</td><td style='padding:12px; border-bottom:1px solid #E5E8E8;'>" + admin.getContactNumber() + "</td></tr>"
            + "<tr><td style='padding:12px; background-color:#F8F9F9; border-bottom:1px solid #E5E8E8; font-weight:bold; color:#212F3C;'>Address</td><td style='padding:12px; border-bottom:1px solid #E5E8E8;'>" + admin.getAddress() + "</td></tr>"
            + "<tr><td style='padding:12px; background-color:#F8F9F9; border-bottom:1px solid #E5E8E8; font-weight:bold; color:#212F3C;'>Basic Salary</td><td style='padding:12px; border-bottom:1px solid #E5E8E8;'>Rs. " + admin.getSalary() + "</td></tr>"
            + "<tr><td style='padding:12px; background-color:#F8F9F9; border-bottom:1px solid #E5E8E8; font-weight:bold; color:#212F3C;'>Registered Email</td><td style='padding:12px; border-bottom:1px solid #E5E8E8;'>" + admin.getEmail() + "</td></tr>"
            + "<tr><td style='padding:12px; background-color:#F8F9F9; font-weight:bold; color:#212F3C;'>Account Status</td><td style='padding:12px; color:#2C3E50; font-weight:bold;'>✓ " + admin.getStatus() + "</td></tr>"
            + "</table>"
            + "</td>"
            + "</tr>"
            
            // IMPORTANT NOTICE
            + "<tr>"
            + "<td style='padding:0 40px 25px 40px;'>"
            + "<div style='background-color:#FFF9E8; border:1px solid #E8D58A; border-left:5px solid #D4AF37; border-radius:7px; padding:18px 20px;'>"
            + "<h3 style='margin:0 0 10px 0; color:#6B5200; font-size:16px;'>Important Information</h3>"
            + "<p style='margin:0; color:#665A35; font-size:13px; line-height:1.7;'>The information displayed above represents the current administrator profile details. Please review the information carefully to ensure all details are accurate.</p>"
            + "</div>"
            + "</td>"
            + "</tr>"
            
            // SECURITY NOTICE
            + "<tr>"
            + "<td style='padding:0 40px 25px 40px;'>"
            + "<div style='background-color:#EAECEE; border-left:4px solid #2C3E50; padding:15px 18px; border-radius:5px;'>"
            + "<p style='margin:0; color:#2C3E50; font-size:13px; line-height:1.6;'><strong style='color:#2C3E50;'>Security Notice</strong><br>If you did not request or expect these changes, please contact the IT department immediately. Do not share your account credentials with anyone.</p>"
            + "</div>"
            + "</td>"
            + "</tr>"
            
            // CLOSING
            + "<tr>"
            + "<td style='padding:5px 40px 30px 40px;'>"
            + "<p style='margin:0; font-size:14px; color:#555555; line-height:1.7;'>Thank you for keeping your profile information up to date.</p>"
            + "<p style='margin:15px 0 0 0; font-size:14px; line-height:1.7;'>Sincerely,<br><strong style='color:#2C3E50;'>Executive Board</strong><br>Central College Anuradhapura</p>"
            + "</td>"
            + "</tr>"
            
            // FOOTER
            + "<tr>"
            + "<td style='background-color:#1A252C; border-top:4px solid #D4AF37; padding:22px 30px; text-align:center;'>"
            + "<p style='margin:0 0 6px 0; color:#ffffff; font-size:13px; font-weight:bold;'>CENTRAL COLLEGE ANURADHAPURA</p>"
            + "<p style='margin:0; color:#ABB2B9; font-size:11px;'>Administration Portal</p>"
            + "<p style='margin:8px 0 0 0; color:#808B96; font-size:10px;'>This is an automated executive email. Please do not reply directly to this message.</p>"
            + "</td>"
            + "</tr>"
            + "</table>"
            + "</div>"
            + "</body>"
            + "</html>";
            
            message.setContent(emailBody, "text/html; charset=utf-8");
            Transport.send(message);
            System.out.println("Update Email Sent to: " + admin.getEmail());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // --- Delete Email (Admin - Slate Theme with Red Alerts) ---
    public static void sendDeleteEmail(String email, String name, String adminId) {
        try {
            Session session = MailConnection.getInstance().getSession();
            String senderEmail = MailConnection.getInstance().getSenderEmail();
            
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Administrator Access Revoked - Central College Anuradhapura");
            
            String deletionDateTime = DateandTimeConnection.getInstance().getCurrentDateTime();
            
            String emailBody = "<html>"
            + "<body style='margin:0; padding:0; background-color:#F4F6F7; font-family:Arial, Helvetica, sans-serif; color:#333333;'>"
            + "<div style='width:100%; padding:30px 0;'>"
            + "<table align='center' width='650' cellpadding='0' cellspacing='0' style='width:650px; max-width:650px; background-color:#ffffff; border-collapse:collapse; border-radius:12px; overflow:hidden; box-shadow:0 4px 15px rgba(0,0,0,0.08);'>"
            
            // HEADER
            + "<tr>"
            + "<td style='background-color:#2C3E50; padding:30px 25px; text-align:center;'>"
            + "<img src='https://anucentralcollege.com/og-image.jpg' alt='Central College Anuradhapura Logo' style='width:85px; height:85px; border-radius:50%; background-color:#ffffff; padding:6px; border:3px solid #D4AF37;'>"
            + "<h1 style='margin:15px 0 5px 0; color:#ffffff; font-size:24px; letter-spacing:0.5px;'>CENTRAL COLLEGE ANURADHAPURA</h1>"
            + "<p style='margin:0; color:#F5D76E; font-size:14px; font-weight:bold; letter-spacing:0.5px;'>ADMINISTRATION PORTAL</p>"
            + "</td>"
            + "</tr>"
            
            // GOLD LINE
            + "<tr><td style='height:5px; background-color:#D4AF37;'></td></tr>"
            
            // GREETING
            + "<tr>"
            + "<td style='padding:35px 40px 10px 40px;'>"
            + "<p style='margin:0 0 12px 0; font-size:16px;'>Dear <strong style='color:#2C3E50;'>" + name + "</strong>,</p>"
            + "<h2 style='margin:0 0 15px 0; color:#B02A37; font-size:23px;'>Administrative Access Revoked</h2>"
            + "<p style='margin:0; color:#555555; font-size:15px; line-height:1.7;'>We are writing to inform you that your administrative access has been removed from the <strong>Central College Anuradhapura Administration Portal</strong>.</p>"
            + "</td>"
            + "</tr>"
            
            // DELETED ACCOUNT BOX (Red for alert)
            + "<tr>"
            + "<td style='padding:20px 40px;'>"
            + "<div style='background-color:#FFF4F4; border:1px solid #F1C6C6; border-left:5px solid #B02A37; border-radius:7px; padding:20px;'>"
            + "<p style='margin:0; font-size:12px; color:#777777; font-weight:bold; letter-spacing:1px;'>ACCOUNT STATUS</p>"
            + "<p style='margin:7px 0 0 0; font-size:21px; font-weight:bold; color:#B02A37;'>✕ Access Revoked</p>"
            + "</div>"
            + "</td>"
            + "</tr>"
            
            // ACCOUNT DETAILS
            + "<tr>"
            + "<td style='padding:5px 40px 12px 40px;'>"
            + "<h3 style='margin:0; color:#2C3E50; font-size:18px;'>Revoked Account Details</h3>"
            + "<div style='width:45px; height:3px; background-color:#D4AF37; margin-top:7px;'></div>"
            + "</td>"
            + "</tr>"
            
            // DETAILS TABLE
            + "<tr>"
            + "<td style='padding:5px 40px 25px 40px;'>"
            + "<table width='100%' cellpadding='0' cellspacing='0' style='border-collapse:collapse; font-size:14px;'>"
            + "<tr>"
            + "<td style='padding:12px; background-color:#F8F9F9; border-bottom:1px solid #E5E8E8; width:40%; font-weight:bold; color:#212F3C;'>Admin ID</td>"
            + "<td style='padding:12px; border-bottom:1px solid #E5E8E8; font-weight:bold; color:#B02A37;'>" + adminId + "</td>"
            + "</tr>"
            + "<tr>"
            + "<td style='padding:12px; background-color:#F8F9F9; border-bottom:1px solid #E5E8E8; font-weight:bold; color:#212F3C;'>Full Name</td>"
            + "<td style='padding:12px; border-bottom:1px solid #E5E8E8;'>" + name + "</td>"
            + "</tr>"
            + "<tr>"
            + "<td style='padding:12px; background-color:#F8F9F9; border-bottom:1px solid #E5E8E8; font-weight:bold; color:#212F3C;'>Date & Time of Revocation</td>"
            + "<td style='padding:12px; border-bottom:1px solid #E5E8E8; color:#555555;'>" + deletionDateTime + "</td>"
            + "</tr>"
            + "<tr>"
            + "<td style='padding:12px; background-color:#F8F9F9; font-weight:bold; color:#212F3C;'>Account Status</td>"
            + "<td style='padding:12px; color:#B02A37; font-weight:bold;'>Revoked / Deleted</td>"
            + "</tr>"
            + "</table>"
            + "</td>"
            + "</tr>"
            
            // IMPORTANT NOTICE
            + "<tr>"
            + "<td style='padding:0 40px 25px 40px;'>"
            + "<div style='background-color:#FFF9E8; border:1px solid #E8D58A; border-left:5px solid #D4AF37; border-radius:7px; padding:18px 20px;'>"
            + "<h3 style='margin:0 0 10px 0; color:#6B5200; font-size:16px;'>Important Information</h3>"
            + "<p style='margin:0; color:#665A35; font-size:13px; line-height:1.7;'>Your administrative privileges have been revoked. You will no longer be able to access the management portal using this account.</p>"
            + "</div>"
            + "</td>"
            + "</tr>"
            
            // SECURITY NOTICE
            + "<tr>"
            + "<td style='padding:0 40px 25px 40px;'>"
            + "<div style='background-color:#EAECEE; border-left:4px solid #2C3E50; padding:15px 18px; border-radius:5px;'>"
            + "<p style='margin:0; color:#2C3E50; font-size:13px; line-height:1.6;'><strong style='color:#2C3E50;'>Did you not expect this?</strong><br>If you believe this action was taken by mistake or without proper authorization, please contact the Executive Board or IT department immediately.</p>"
            + "</div>"
            + "</td>"
            + "</tr>"
            
            // SUPPORT & CLOSING
            + "<tr>"
            + "<td style='padding:0 40px 25px 40px;'>"
            + "<p style='margin:0; font-size:13px; color:#666666; line-height:1.7;'>For further assistance regarding your administrative access, please contact the Central College Anuradhapura administration office.</p>"
            + "</td>"
            + "</tr>"
            + "<tr>"
            + "<td style='padding:5px 40px 30px 40px;'>"
            + "<p style='margin:0; font-size:14px; color:#555555; line-height:1.7;'>Thank you for your understanding.</p>"
            + "<p style='margin:15px 0 0 0; font-size:14px; line-height:1.7;'>Sincerely,<br><strong style='color:#2C3E50;'>Executive Board</strong><br>Central College Anuradhapura</p>"
            + "</td>"
            + "</tr>"
            
            // FOOTER (Darker Charcoal)
            + "<tr>"
            + "<td style='background-color:#1A252C; border-top:4px solid #D4AF37; padding:22px 30px; text-align:center;'>"
            + "<p style='margin:0 0 6px 0; color:#ffffff; font-size:13px; font-weight:bold;'>CENTRAL COLLEGE ANURADHAPURA</p>"
            + "<p style='margin:0; color:#ABB2B9; font-size:11px;'>Administration Portal</p>"
            + "<p style='margin:8px 0 0 0; color:#808B96; font-size:10px;'>This is an automated executive email. Please do not reply directly to this message.</p>"
            + "</td>"
            + "</tr>"
            + "</table>"
            + "</div>"
            + "</body>"
            + "</html>";
            
            message.setContent(emailBody, "text/html; charset=utf-8");
            Transport.send(message);
            System.out.println("Delete Email Sent to: " + email);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}