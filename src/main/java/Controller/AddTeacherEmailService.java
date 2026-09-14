package Controller;

import Model.dto.TeacherDto;
import MailConnection.MailConnection;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Session;
import DateandTimeConnection.DateandTimeConnection;

public class AddTeacherEmailService {
    
    // --- Welcome Email (Teacher - Navy Blue Theme) ---
    public static void sendWelcomeEmail(TeacherDto teacher) {
        try {
            Session session = MailConnection.getInstance().getSession();
            String senderEmail = MailConnection.getInstance().getSenderEmail();
            
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(teacher.getEmail()));
            message.setSubject("Official Staff Registration - Central College Anuradhapura");
            
            String emailBody = "<html>"
            + "<body style='margin:0; padding:0; background-color:#F0F4F8; font-family:Arial, Helvetica, sans-serif; color:#333333;'>"
            + "<div style='width:100%; padding:30px 0;'>"
            + "<table align='center' width='650' cellpadding='0' cellspacing='0' style='width:650px; max-width:650px; background-color:#ffffff; border-collapse:collapse; border-radius:12px; overflow:hidden; box-shadow:0 4px 15px rgba(0,0,0,0.08);'>"
            
            // HEADER (Navy Blue)
            + "<tr>"
            + "<td style='background-color:#004080; padding:30px 25px; text-align:center;'>"
            + "<img src='https://anucentralcollege.com/og-image.jpg' alt='Central College Anuradhapura Logo' style='width:85px; height:85px; border-radius:50%; background-color:#ffffff; padding:6px; border:3px solid #D4AF37;'>"
            + "<h1 style='margin:15px 0 5px 0; color:#ffffff; font-size:24px; letter-spacing:0.5px;'>CENTRAL COLLEGE ANURADHAPURA</h1>"
            + "<p style='margin:0; color:#F5D76E; font-size:14px; font-weight:bold; letter-spacing:0.5px;'>STAFF MANAGEMENT SYSTEM</p>"
            + "</td>"
            + "</tr>"
            
            // GOLD LINE
            + "<tr><td style='height:5px; background-color:#D4AF37;'></td></tr>"
            
            // GREETING
            + "<tr>"
            + "<td style='padding:35px 40px 10px 40px;'>"
            + "<p style='margin:0 0 12px 0; font-size:16px;'>Dear <strong style='color:#004080;'>" + teacher.getName() + "</strong>,</p>"
            + "<h2 style='margin:0 0 15px 0; color:#004080; font-size:23px;'>Staff Registration Confirmed</h2>"
            + "<p style='margin:0; color:#555555; font-size:15px; line-height:1.7;'>We are pleased to inform you that your registration with the <strong>Central College Anuradhapura Staff Management System</strong> has been successfully completed.</p>"
            + "</td>"
            + "</tr>"
            
            // TEACHER ID CARD (Light Blue)
            + "<tr>"
            + "<td style='padding:20px 40px;'>"
            + "<div style='background-color:#EBF1F6; border:1px solid #D0DCE8; border-left:5px solid #004080; border-radius:7px; padding:18px 20px;'>"
            + "<p style='margin:0; font-size:12px; color:#5B6B7A; font-weight:bold; letter-spacing:1px;'>YOUR TEACHER ID</p>"
            + "<p style='margin:7px 0 0 0; font-size:25px; font-weight:bold; color:#004080;'>" + teacher.getTeacherId() + "</p>"
            + "</div>"
            + "</td>"
            + "</tr>"
            
            // DETAILS TITLE
            + "<tr>"
            + "<td style='padding:5px 40px 12px 40px;'>"
            + "<h3 style='margin:0; color:#004080; font-size:18px;'>Registration Details</h3>"
            + "<div style='width:45px; height:3px; background-color:#D4AF37; margin-top:7px;'></div>"
            + "</td>"
            + "</tr>"
            
            // DETAILS TABLE (Blue Theme)
            + "<tr>"
            + "<td style='padding:5px 40px 25px 40px;'>"
            + "<table width='100%' cellpadding='0' cellspacing='0' style='border-collapse:collapse; font-size:14px;'>"
            + "<tr>"
            + "<td style='padding:12px; background-color:#F4F7F9; border-bottom:1px solid #E2E9F0; width:40%; font-weight:bold; color:#2C3E50;'>Full Name</td>"
            + "<td style='padding:12px; border-bottom:1px solid #E2E9F0;'>" + teacher.getName() + "</td>"
            + "</tr>"
            + "<tr>"
            + "<td style='padding:12px; background-color:#F4F7F9; border-bottom:1px solid #E2E9F0; font-weight:bold; color:#2C3E50;'>NIC Number</td>"
            + "<td style='padding:12px; border-bottom:1px solid #E2E9F0;'>" + teacher.getNic() + "</td>"
            + "</tr>"
            + "<tr>"
            + "<td style='padding:12px; background-color:#F4F7F9; border-bottom:1px solid #E2E9F0; font-weight:bold; color:#2C3E50;'>Date of Birth</td>"
            + "<td style='padding:12px; border-bottom:1px solid #E2E9F0;'>" + teacher.getBirthday() + "</td>"
            + "</tr>"
            + "<tr>"
            + "<td style='padding:12px; background-color:#F4F7F9; border-bottom:1px solid #E2E9F0; font-weight:bold; color:#2C3E50;'>Gender</td>"
            + "<td style='padding:12px; border-bottom:1px solid #E2E9F0;'>" + teacher.getGender() + "</td>"
            + "</tr>"
            + "<tr>"
            + "<td style='padding:12px; background-color:#F4F7F9; border-bottom:1px solid #E2E9F0; font-weight:bold; color:#2C3E50;'>Contact Number</td>"
            + "<td style='padding:12px; border-bottom:1px solid #E2E9F0;'>" + teacher.getContactNumber() + "</td>"
            + "</tr>"
            + "<tr>"
            + "<td style='padding:12px; background-color:#F4F7F9; border-bottom:1px solid #E2E9F0; font-weight:bold; color:#2C3E50;'>Address</td>"
            + "<td style='padding:12px; border-bottom:1px solid #E2E9F0;'>" + teacher.getAddress() + "</td>"
            + "</tr>"
            + "<tr>"
            + "<td style='padding:12px; background-color:#F4F7F9; border-bottom:1px solid #E2E9F0; font-weight:bold; color:#2C3E50;'>Basic Salary</td>"
            + "<td style='padding:12px; border-bottom:1px solid #E2E9F0;'>Rs. " + teacher.getSalary() + "</td>"
            + "</tr>"
            + "<tr>"
            + "<td style='padding:12px; background-color:#F4F7F9; border-bottom:1px solid #E2E9F0; font-weight:bold; color:#2C3E50;'>Registered Email</td>"
            + "<td style='padding:12px; border-bottom:1px solid #E2E9F0;'>" + teacher.getEmail() + "</td>"
            + "</tr>"
            + "<tr>"
            + "<td style='padding:12px; background-color:#F4F7F9; font-weight:bold; color:#2C3E50;'>Account Status</td>"
            + "<td style='padding:12px; color:#004080; font-weight:bold;'>✓ " + teacher.getStatus() + "</td>"
            + "</tr>"
            + "</table>"
            + "</td>"
            + "</tr>"
            
            // LOGIN INFORMATION (Gold Accent)
            + "<tr>"
            + "<td style='padding:0 40px 25px 40px;'>"
            + "<div style='background-color:#FFF9E8; border:1px solid #E8D58A; border-radius:8px; padding:20px;'>"
            + "<h3 style='margin:0 0 15px 0; color:#6B5200; font-size:17px;'>🔐 System Login Information</h3>"
            + "<p style='margin:9px 0; font-size:14px;'><strong>Teacher ID:</strong> " + teacher.getTeacherId() + "</p>"
            + "<p style='margin:9px 0; font-size:14px;'><strong>Email:</strong> " + teacher.getEmail() + "</p>"
            + "<p style='margin:9px 0; font-size:14px;'><strong>Temporary Password:</strong> <span style='background:#ffffff; padding:6px 10px; border:1px solid #D9D9D9; border-radius:4px; font-family:monospace; font-weight:bold;'>" + teacher.getPassword() + "</span></p>"
            + "</div>"
            + "</td>"
            + "</tr>"
            
            // SECURITY NOTICE (Blue Accent)
            + "<tr>"
            + "<td style='padding:0 40px 25px 40px;'>"
            + "<div style='background-color:#F0F4F8; border-left:4px solid #004080; padding:15px 18px; border-radius:5px;'>"
            + "<p style='margin:0; color:#3A4A5A; font-size:13px; line-height:1.6;'><strong style='color:#004080;'>Security Notice</strong><br>Please keep your login credentials confidential and do not share your password with anyone. For your security, please change your temporary password after your first login.</p>"
            + "</div>"
            + "</td>"
            + "</tr>"
            
            // CLOSING
            + "<tr>"
            + "<td style='padding:5px 40px 30px 40px;'>"
            + "<p style='margin:0; font-size:14px; color:#555555; line-height:1.7;'>We wish you a successful academic year ahead.</p>"
            + "<p style='margin:15px 0 0 0; font-size:14px; line-height:1.7;'>Sincerely,<br><strong style='color:#004080;'>The Administration</strong><br>Central College Anuradhapura</p>"
            + "</td>"
            + "</tr>"
            
            // FOOTER (Darker Navy Blue)
            + "<tr>"
            + "<td style='background-color:#00264D; border-top:4px solid #D4AF37; padding:22px 30px; text-align:center;'>"
            + "<p style='margin:0 0 6px 0; color:#ffffff; font-size:13px; font-weight:bold;'>CENTRAL COLLEGE ANURADHAPURA</p>"
            + "<p style='margin:0; color:#D0DCE8; font-size:11px;'>Staff Management System</p>"
            + "<p style='margin:8px 0 0 0; color:#9FB3C8; font-size:10px;'>This is an automated email. Please do not reply directly to this message.</p>"
            + "</td>"
            + "</tr>"
            + "</table>"
            + "</div>"
            + "</body>"
            + "</html>";
            
            message.setContent(emailBody, "text/html; charset=utf-8");
            Transport.send(message);
            System.out.println("Email Sent Successfully to: " + teacher.getEmail());
            
        } catch (Exception e) {
            System.out.println("Email Sending Failed: " + e.getMessage());
        }
    }

    // --- Update Email (Teacher - Navy Blue Theme) ---
    public static void sendUpdateEmail(TeacherDto teacher) {
        try {
            Session session = MailConnection.getInstance().getSession();
            String senderEmail = MailConnection.getInstance().getSenderEmail();
            
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(teacher.getEmail()));
            message.setSubject("Account Update Notification - Central College Anuradhapura");
            
            String emailBody = "<html>"
            + "<body style='margin:0; padding:0; background-color:#F0F4F8; font-family:Arial, Helvetica, sans-serif; color:#333333;'>"
            + "<div style='width:100%; padding:30px 0;'>"
            + "<table align='center' width='650' cellpadding='0' cellspacing='0' style='width:650px; max-width:650px; background-color:#ffffff; border-collapse:collapse; border-radius:12px; overflow:hidden; box-shadow:0 4px 15px rgba(0,0,0,0.08);'>"
            
            // HEADER
            + "<tr>"
            + "<td style='background-color:#004080; padding:30px 25px; text-align:center;'>"
            + "<img src='https://anucentralcollege.com/og-image.jpg' alt='Central College Anuradhapura Logo' style='width:85px; height:85px; border-radius:50%; background-color:#ffffff; padding:6px; border:3px solid #D4AF37;'>"
            + "<h1 style='margin:15px 0 5px 0; color:#ffffff; font-size:24px; letter-spacing:0.5px;'>CENTRAL COLLEGE ANURADHAPURA</h1>"
            + "<p style='margin:0; color:#F5D76E; font-size:14px; font-weight:bold; letter-spacing:0.5px;'>STAFF MANAGEMENT SYSTEM</p>"
            + "</td>"
            + "</tr>"
            
            // GOLD LINE
            + "<tr><td style='height:5px; background-color:#D4AF37;'></td></tr>"
            
            // GREETING
            + "<tr>"
            + "<td style='padding:35px 40px 10px 40px;'>"
            + "<p style='margin:0 0 12px 0; font-size:16px;'>Dear <strong style='color:#004080;'>" + teacher.getName() + "</strong>,</p>"
            + "<h2 style='margin:0 0 15px 0; color:#004080; font-size:23px;'>Staff Profile Updated</h2>"
            + "<p style='margin:0; color:#555555; font-size:15px; line-height:1.7;'>This is to inform you that your staff profile in the <strong>Central College Anuradhapura Staff Management System</strong> has been successfully updated by the school administration.</p>"
            + "</td>"
            + "</tr>"
            
            // TEACHER ID BOX
            + "<tr>"
            + "<td style='padding:20px 40px;'>"
            + "<div style='background-color:#EBF1F6; border:1px solid #D0DCE8; border-left:5px solid #004080; border-radius:7px; padding:18px 20px;'>"
            + "<p style='margin:0; font-size:12px; color:#5B6B7A; font-weight:bold; letter-spacing:1px;'>TEACHER ID</p>"
            + "<p style='margin:7px 0 0 0; font-size:24px; font-weight:bold; color:#004080;'>" + teacher.getTeacherId() + "</p>"
            + "</div>"
            + "</td>"
            + "</tr>"
            
            // UPDATE INFORMATION
            + "<tr>"
            + "<td style='padding:5px 40px 12px 40px;'>"
            + "<h3 style='margin:0; color:#004080; font-size:18px;'>Updated Profile Information</h3>"
            + "<div style='width:45px; height:3px; background-color:#D4AF37; margin-top:7px;'></div>"
            + "</td>"
            + "</tr>"
            
            // DETAILS TABLE
            + "<tr>"
            + "<td style='padding:5px 40px 25px 40px;'>"
            + "<table width='100%' cellpadding='0' cellspacing='0' style='border-collapse:collapse; font-size:14px;'>"
            + "<tr><td style='padding:12px; background-color:#F4F7F9; border-bottom:1px solid #E2E9F0; width:40%; font-weight:bold; color:#2C3E50;'>Full Name</td><td style='padding:12px; border-bottom:1px solid #E2E9F0;'>" + teacher.getName() + "</td></tr>"
            + "<tr><td style='padding:12px; background-color:#F4F7F9; border-bottom:1px solid #E2E9F0; font-weight:bold; color:#2C3E50;'>NIC Number</td><td style='padding:12px; border-bottom:1px solid #E2E9F0;'>" + teacher.getNic() + "</td></tr>"
            + "<tr><td style='padding:12px; background-color:#F4F7F9; border-bottom:1px solid #E2E9F0; font-weight:bold; color:#2C3E50;'>Date of Birth</td><td style='padding:12px; border-bottom:1px solid #E2E9F0;'>" + teacher.getBirthday() + "</td></tr>"
            + "<tr><td style='padding:12px; background-color:#F4F7F9; border-bottom:1px solid #E2E9F0; font-weight:bold; color:#2C3E50;'>Gender</td><td style='padding:12px; border-bottom:1px solid #E2E9F0;'>" + teacher.getGender() + "</td></tr>"
            + "<tr><td style='padding:12px; background-color:#F4F7F9; border-bottom:1px solid #E2E9F0; font-weight:bold; color:#2C3E50;'>Contact Number</td><td style='padding:12px; border-bottom:1px solid #E2E9F0;'>" + teacher.getContactNumber() + "</td></tr>"
            + "<tr><td style='padding:12px; background-color:#F4F7F9; border-bottom:1px solid #E2E9F0; font-weight:bold; color:#2C3E50;'>Address</td><td style='padding:12px; border-bottom:1px solid #E2E9F0;'>" + teacher.getAddress() + "</td></tr>"
            + "<tr><td style='padding:12px; background-color:#F4F7F9; border-bottom:1px solid #E2E9F0; font-weight:bold; color:#2C3E50;'>Basic Salary</td><td style='padding:12px; border-bottom:1px solid #E2E9F0;'>Rs. " + teacher.getSalary() + "</td></tr>"
            + "<tr><td style='padding:12px; background-color:#F4F7F9; border-bottom:1px solid #E2E9F0; font-weight:bold; color:#2C3E50;'>Registered Email</td><td style='padding:12px; border-bottom:1px solid #E2E9F0;'>" + teacher.getEmail() + "</td></tr>"
            + "<tr><td style='padding:12px; background-color:#F4F7F9; font-weight:bold; color:#2C3E50;'>Account Status</td><td style='padding:12px; color:#004080; font-weight:bold;'>✓ " + teacher.getStatus() + "</td></tr>"
            + "</table>"
            + "</td>"
            + "</tr>"
            
            // IMPORTANT NOTICE
            + "<tr>"
            + "<td style='padding:0 40px 25px 40px;'>"
            + "<div style='background-color:#FFF9E8; border:1px solid #E8D58A; border-left:5px solid #D4AF37; border-radius:7px; padding:18px 20px;'>"
            + "<h3 style='margin:0 0 10px 0; color:#6B5200; font-size:16px;'>Important Information</h3>"
            + "<p style='margin:0; color:#665A35; font-size:13px; line-height:1.7;'>The information displayed above represents the current staff profile details stored in the school management system. Please review the information carefully and ensure that all details are correct.</p>"
            + "</div>"
            + "</td>"
            + "</tr>"
            
            // SECURITY NOTICE
            + "<tr>"
            + "<td style='padding:0 40px 25px 40px;'>"
            + "<div style='background-color:#F0F4F8; border-left:4px solid #004080; padding:15px 18px; border-radius:5px;'>"
            + "<p style='margin:0; color:#3A4A5A; font-size:13px; line-height:1.6;'><strong style='color:#004080;'>Security Notice</strong><br>If you did not request or expect these changes, please contact the school administration or IT department immediately. Do not share your account credentials with anyone.</p>"
            + "</div>"
            + "</td>"
            + "</tr>"
            
            // CLOSING
            + "<tr>"
            + "<td style='padding:5px 40px 30px 40px;'>"
            + "<p style='margin:0; font-size:14px; color:#555555; line-height:1.7;'>Thank you for keeping your staff information up to date.</p>"
            + "<p style='margin:15px 0 0 0; font-size:14px; line-height:1.7;'>Sincerely,<br><strong style='color:#004080;'>The Administration</strong><br>Central College Anuradhapura</p>"
            + "</td>"
            + "</tr>"
            
            // FOOTER
            + "<tr>"
            + "<td style='background-color:#00264D; border-top:4px solid #D4AF37; padding:22px 30px; text-align:center;'>"
            + "<p style='margin:0 0 6px 0; color:#ffffff; font-size:13px; font-weight:bold;'>CENTRAL COLLEGE ANURADHAPURA</p>"
            + "<p style='margin:0; color:#D0DCE8; font-size:11px;'>Staff Management System</p>"
            + "<p style='margin:8px 0 0 0; color:#9FB3C8; font-size:10px;'>This is an automated email. Please do not reply directly to this message.</p>"
            + "</td>"
            + "</tr>"
            + "</table>"
            + "</div>"
            + "</body>"
            + "</html>";
            
            message.setContent(emailBody, "text/html; charset=utf-8");
            Transport.send(message);
            System.out.println("Update Email Sent to: " + teacher.getEmail());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // --- Delete Email (Teacher - Navy Blue Theme with Red Alerts) ---
    public static void sendDeleteEmail(String email, String name, String teacherId) {
        try {
            Session session = MailConnection.getInstance().getSession();
            String senderEmail = MailConnection.getInstance().getSenderEmail();
            
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Account Deletion Notice - Central College Anuradhapura");
            
            String deletionDateTime = DateandTimeConnection.getInstance().getCurrentDateTime();
            
            String emailBody = "<html>"
            + "<body style='margin:0; padding:0; background-color:#F0F4F8; font-family:Arial, Helvetica, sans-serif; color:#333333;'>"
            + "<div style='width:100%; padding:30px 0;'>"
            + "<table align='center' width='650' cellpadding='0' cellspacing='0' style='width:650px; max-width:650px; background-color:#ffffff; border-collapse:collapse; border-radius:12px; overflow:hidden; box-shadow:0 4px 15px rgba(0,0,0,0.08);'>"
            
            // HEADER
            + "<tr>"
            + "<td style='background-color:#004080; padding:30px 25px; text-align:center;'>"
            + "<img src='https://anucentralcollege.com/og-image.jpg' alt='Central College Anuradhapura Logo' style='width:85px; height:85px; border-radius:50%; background-color:#ffffff; padding:6px; border:3px solid #D4AF37;'>"
            + "<h1 style='margin:15px 0 5px 0; color:#ffffff; font-size:24px; letter-spacing:0.5px;'>CENTRAL COLLEGE ANURADHAPURA</h1>"
            + "<p style='margin:0; color:#F5D76E; font-size:14px; font-weight:bold; letter-spacing:0.5px;'>STAFF MANAGEMENT SYSTEM</p>"
            + "</td>"
            + "</tr>"
            
            // GOLD LINE
            + "<tr><td style='height:5px; background-color:#D4AF37;'></td></tr>"
            
            // GREETING
            + "<tr>"
            + "<td style='padding:35px 40px 10px 40px;'>"
            + "<p style='margin:0 0 12px 0; font-size:16px;'>Dear <strong style='color:#004080;'>" + name + "</strong>,</p>"
            + "<h2 style='margin:0 0 15px 0; color:#B02A37; font-size:23px;'>Staff Account Removed</h2>"
            + "<p style='margin:0; color:#555555; font-size:15px; line-height:1.7;'>We are writing to inform you that your staff account has been removed from the <strong>Central College Anuradhapura Staff Management System</strong>.</p>"
            + "</td>"
            + "</tr>"
            
            // DELETED ACCOUNT BOX (Red for alert)
            + "<tr>"
            + "<td style='padding:20px 40px;'>"
            + "<div style='background-color:#FFF4F4; border:1px solid #F1C6C6; border-left:5px solid #B02A37; border-radius:7px; padding:20px;'>"
            + "<p style='margin:0; font-size:12px; color:#777777; font-weight:bold; letter-spacing:1px;'>ACCOUNT STATUS</p>"
            + "<p style='margin:7px 0 0 0; font-size:21px; font-weight:bold; color:#B02A37;'>✕ Account Deleted</p>"
            + "</div>"
            + "</td>"
            + "</tr>"
            
            // ACCOUNT DETAILS
            + "<tr>"
            + "<td style='padding:5px 40px 12px 40px;'>"
            + "<h3 style='margin:0; color:#004080; font-size:18px;'>Deleted Account Details</h3>"
            + "<div style='width:45px; height:3px; background-color:#D4AF37; margin-top:7px;'></div>"
            + "</td>"
            + "</tr>"
            
            // DETAILS TABLE
            + "<tr>"
            + "<td style='padding:5px 40px 25px 40px;'>"
            + "<table width='100%' cellpadding='0' cellspacing='0' style='border-collapse:collapse; font-size:14px;'>"
            + "<tr>"
            + "<td style='padding:12px; background-color:#F4F7F9; border-bottom:1px solid #E2E9F0; width:40%; font-weight:bold; color:#2C3E50;'>Teacher ID</td>"
            + "<td style='padding:12px; border-bottom:1px solid #E2E9F0; font-weight:bold; color:#B02A37;'>" + teacherId + "</td>"
            + "</tr>"
            + "<tr>"
            + "<td style='padding:12px; background-color:#F4F7F9; border-bottom:1px solid #E2E9F0; font-weight:bold; color:#2C3E50;'>Staff Name</td>"
            + "<td style='padding:12px; border-bottom:1px solid #E2E9F0;'>" + name + "</td>"
            + "</tr>"
            + "<tr>"
            + "<td style='padding:12px; background-color:#F4F7F9; border-bottom:1px solid #E2E9F0; font-weight:bold; color:#2C3E50;'>Date & Time of Deletion</td>"
            + "<td style='padding:12px; border-bottom:1px solid #E2E9F0; color:#555555;'>" + deletionDateTime + "</td>"
            + "</tr>"
            + "<tr>"
            + "<td style='padding:12px; background-color:#F4F7F9; font-weight:bold; color:#2C3E50;'>Account Status</td>"
            + "<td style='padding:12px; color:#B02A37; font-weight:bold;'>Deleted</td>"
            + "</tr>"
            + "</table>"
            + "</td>"
            + "</tr>"
            
            // IMPORTANT NOTICE
            + "<tr>"
            + "<td style='padding:0 40px 25px 40px;'>"
            + "<div style='background-color:#FFF9E8; border:1px solid #E8D58A; border-left:5px solid #D4AF37; border-radius:7px; padding:18px 20px;'>"
            + "<h3 style='margin:0 0 10px 0; color:#6B5200; font-size:16px;'>Important Information</h3>"
            + "<p style='margin:0; color:#665A35; font-size:13px; line-height:1.7;'>Your staff account is no longer active in the school Staff Management System. You will no longer be able to access the system using this account.</p>"
            + "</div>"
            + "</td>"
            + "</tr>"
            
            // SECURITY NOTICE
            + "<tr>"
            + "<td style='padding:0 40px 25px 40px;'>"
            + "<div style='background-color:#F0F4F8; border-left:4px solid #004080; padding:15px 18px; border-radius:5px;'>"
            + "<p style='margin:0; color:#3A4A5A; font-size:13px; line-height:1.6;'><strong style='color:#004080;'>Did you not expect this?</strong><br>If you believe this account was removed by mistake or without proper authorization, please contact the school administration office or IT department immediately.</p>"
            + "</div>"
            + "</td>"
            + "</tr>"
            
            // SUPPORT & CLOSING
            + "<tr>"
            + "<td style='padding:0 40px 25px 40px;'>"
            + "<p style='margin:0; font-size:13px; color:#666666; line-height:1.7;'>For further assistance regarding your staff account, please contact the Central College Anuradhapura administration office.</p>"
            + "</td>"
            + "</tr>"
            + "<tr>"
            + "<td style='padding:5px 40px 30px 40px;'>"
            + "<p style='margin:0; font-size:14px; color:#555555; line-height:1.7;'>Thank you for your understanding.</p>"
            + "<p style='margin:15px 0 0 0; font-size:14px; line-height:1.7;'>Sincerely,<br><strong style='color:#004080;'>The Administration</strong><br>Central College Anuradhapura</p>"
            + "</td>"
            + "</tr>"
            
            // FOOTER (Darker Navy Blue)
            + "<tr>"
            + "<td style='background-color:#00264D; border-top:4px solid #D4AF37; padding:22px 30px; text-align:center;'>"
            + "<p style='margin:0 0 6px 0; color:#ffffff; font-size:13px; font-weight:bold;'>CENTRAL COLLEGE ANURADHAPURA</p>"
            + "<p style='margin:0; color:#D0DCE8; font-size:11px;'>Staff Management System</p>"
            + "<p style='margin:8px 0 0 0; color:#9FB3C8; font-size:10px;'>This is an automated email. Please do not reply directly to this message.</p>"
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