package Controller;

import Model.dto.StudentDto;
import MailConnection.MailConnection;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Session;
import DateandTimeConnection.DateandTimeConnection;

public class AddStuEmailService {
    
    // --- Welcome Email යැවීම ---
    public static void sendWelcomeEmail(StudentDto student) {
        try {
            Session session = MailConnection.getInstance().getSession();
            String senderEmail = MailConnection.getInstance().getSenderEmail();
            
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(student.getEmail()));
            message.setSubject("Official Student Registration - Central College Anuradhapura");
            
            String emailBody = "<html>"
            + "<body style='margin:0; padding:0; background-color:#F3F7F4; font-family:Arial, Helvetica, sans-serif; color:#333333;'>"
            + "<div style='width:100%; padding:30px 0;'>"
            + "<table align='center' width='650' cellpadding='0' cellspacing='0' style='width:650px; max-width:650px; background-color:#ffffff; border-collapse:collapse; border-radius:12px; overflow:hidden; box-shadow:0 4px 15px rgba(0,0,0,0.08);'>"
            
            // HEADER
            + "<tr>"
            + "<td style='background-color:#006B3C; padding:30px 25px; text-align:center;'>"
            + "<img src='https://anucentralcollege.com/og-image.jpg' alt='Central College Anuradhapura Logo' style='width:85px; height:85px; border-radius:50%; background-color:#ffffff; padding:6px; border:3px solid #D4AF37;'>"
            + "<h1 style='margin:15px 0 5px 0; color:#ffffff; font-size:24px; letter-spacing:0.5px;'>CENTRAL COLLEGE ANURADHAPURA</h1>"
            + "<p style='margin:0; color:#F5D76E; font-size:14px; font-weight:bold; letter-spacing:0.5px;'>STUDENT MANAGEMENT SYSTEM</p>"
            + "</td>"
            + "</tr>"
            
            // GOLD LINE
            + "<tr><td style='height:5px; background-color:#D4AF37;'></td></tr>"
            
            // GREETING
            + "<tr>"
            + "<td style='padding:35px 40px 10px 40px;'>"
            + "<p style='margin:0 0 12px 0; font-size:16px;'>Dear <strong style='color:#006B3C;'>" + student.getName() + "</strong>,</p>"
            + "<h2 style='margin:0 0 15px 0; color:#006B3C; font-size:23px;'>Student Registration Confirmed</h2>"
            + "<p style='margin:0; color:#555555; font-size:15px; line-height:1.7;'>We are pleased to inform you that your registration with the <strong>Central College Anuradhapura Student Management System</strong> has been successfully completed.</p>"
            + "</td>"
            + "</tr>"
            
            // STUDENT ID CARD
            + "<tr>"
            + "<td style='padding:20px 40px;'>"
            + "<div style='background-color:#F0F8F3; border:1px solid #C8E6D4; border-left:5px solid #006B3C; border-radius:7px; padding:18px 20px;'>"
            + "<p style='margin:0; font-size:12px; color:#6B6B6B; font-weight:bold; letter-spacing:1px;'>YOUR STUDENT ID</p>"
            + "<p style='margin:7px 0 0 0; font-size:25px; font-weight:bold; color:#006B3C;'>" + student.getStudentId() + "</p>"
            + "</div>"
            + "</td>"
            + "</tr>"
            
            // DETAILS TITLE
            + "<tr>"
            + "<td style='padding:5px 40px 12px 40px;'>"
            + "<h3 style='margin:0; color:#006B3C; font-size:18px;'>Student Registration Details</h3>"
            + "<div style='width:45px; height:3px; background-color:#D4AF37; margin-top:7px;'></div>"
            + "</td>"
            + "</tr>"
            
            // DETAILS TABLE
            + "<tr>"
            + "<td style='padding:5px 40px 25px 40px;'>"
            + "<table width='100%' cellpadding='0' cellspacing='0' style='border-collapse:collapse; font-size:14px;'>"
            
            // Full Name
            + "<tr>"
            + "<td style='padding:12px; background-color:#F5F9F6; border-bottom:1px solid #E4EAE6; width:40%; font-weight:bold; color:#365443;'>Full Name</td>"
            + "<td style='padding:12px; border-bottom:1px solid #E4EAE6;'>" + student.getName() + "</td>"
            + "</tr>"
            
            // Birthday
            + "<tr>"
            + "<td style='padding:12px; background-color:#F5F9F6; border-bottom:1px solid #E4EAE6; font-weight:bold; color:#365443;'>Date of Birth</td>"
            + "<td style='padding:12px; border-bottom:1px solid #E4EAE6;'>" + student.getBirthday() + "</td>"
            + "</tr>"
            
            // Gender
            + "<tr>"
            + "<td style='padding:12px; background-color:#F5F9F6; border-bottom:1px solid #E4EAE6; font-weight:bold; color:#365443;'>Gender</td>"
            + "<td style='padding:12px; border-bottom:1px solid #E4EAE6;'>" + student.getGender() + "</td>"
            + "</tr>"
            
            // Class (වෙනස: getClassId වෙනුවට getClassName යොදා ඇත)
            + "<tr>"
            + "<td style='padding:12px; background-color:#F5F9F6; border-bottom:1px solid #E4EAE6; font-weight:bold; color:#365443;'>Class</td>"
            + "<td style='padding:12px; border-bottom:1px solid #E4EAE6;'>" + (student.getClassName() != null ? student.getClassName() : "-") + "</td>"
            + "</tr>"
            
            // Contact
            + "<tr>"
            + "<td style='padding:12px; background-color:#F5F9F6; border-bottom:1px solid #E4EAE6; font-weight:bold; color:#365443;'>Contact Number</td>"
            + "<td style='padding:12px; border-bottom:1px solid #E4EAE6;'>" + student.getContactNumber() + "</td>"
            + "</tr>"
            
            // Address
            + "<tr>"
            + "<td style='padding:12px; background-color:#F5F9F6; border-bottom:1px solid #E4EAE6; font-weight:bold; color:#365443;'>Address</td>"
            + "<td style='padding:12px; border-bottom:1px solid #E4EAE6;'>" + student.getAddress() + "</td>"
            + "</tr>"
            
            // Email
            + "<tr>"
            + "<td style='padding:12px; background-color:#F5F9F6; border-bottom:1px solid #E4EAE6; font-weight:bold; color:#365443;'>Registered Email</td>"
            + "<td style='padding:12px; border-bottom:1px solid #E4EAE6;'>" + student.getEmail() + "</td>"
            + "</tr>"
            
            // Status
            + "<tr>"
            + "<td style='padding:12px; background-color:#F5F9F6; font-weight:bold; color:#365443;'>Account Status</td>"
            + "<td style='padding:12px; color:#198754; font-weight:bold;'>✓ " + student.getStatus() + "</td>"
            + "</tr>"
            + "</table>"
            + "</td>"
            + "</tr>"
            
            // LOGIN INFORMATION
            + "<tr>"
            + "<td style='padding:0 40px 25px 40px;'>"
            + "<div style='background-color:#FFF9E8; border:1px solid #E8D58A; border-radius:8px; padding:20px;'>"
            + "<h3 style='margin:0 0 15px 0; color:#6B5200; font-size:17px;'>🔐 System Login Information</h3>"
            + "<p style='margin:9px 0; font-size:14px;'><strong>Student ID:</strong> " + student.getStudentId() + "</p>"
            + "<p style='margin:9px 0; font-size:14px;'><strong>Email:</strong> " + student.getEmail() + "</p>"
            + "<p style='margin:9px 0; font-size:14px;'><strong>Temporary Password:</strong> <span style='background:#ffffff; padding:6px 10px; border:1px solid #D9D9D9; border-radius:4px; font-family:monospace; font-weight:bold;'>" + student.getPassword() + "</span></p>"
            + "</div>"
            + "</td>"
            + "</tr>"
            
            // SECURITY NOTICE
            + "<tr>"
            + "<td style='padding:0 40px 25px 40px;'>"
            + "<div style='background-color:#F4F9F6; border-left:4px solid #D4AF37; padding:15px 18px; border-radius:5px;'>"
            + "<p style='margin:0; color:#4B5B51; font-size:13px; line-height:1.6;'><strong style='color:#006B3C;'>Security Notice</strong><br>Please keep your login credentials confidential and do not share your password with anyone. For your security, please change your temporary password after your first login.</p>"
            + "</div>"
            + "</td>"
            + "</tr>"
            
            // CLOSING
            + "<tr>"
            + "<td style='padding:5px 40px 30px 40px;'>"
            + "<p style='margin:0; font-size:14px; color:#555555; line-height:1.7;'>We wish you every success in your academic journey.</p>"
            + "<p style='margin:15px 0 0 0; font-size:14px; line-height:1.7;'>Sincerely,<br><strong style='color:#006B3C;'>The Administration</strong><br>Central College Anuradhapura</p>"
            + "</td>"
            + "</tr>"
            
            // FOOTER
            + "<tr>"
            + "<td style='background-color:#004D2C; border-top:4px solid #D4AF37; padding:22px 30px; text-align:center;'>"
            + "<p style='margin:0 0 6px 0; color:#ffffff; font-size:13px; font-weight:bold;'>CENTRAL COLLEGE ANURADHAPURA</p>"
            + "<p style='margin:0; color:#C8DED3; font-size:11px;'>Student Management System</p>"
            + "<p style='margin:8px 0 0 0; color:#A9C7B8; font-size:10px;'>This is an automated email. Please do not reply directly to this message.</p>"
            + "</td>"
            + "</tr>"
            + "</table>"
            + "</div>"
            + "</body>"
            + "</html>";
            
            message.setContent(emailBody, "text/html; charset=utf-8");
            Transport.send(message);
            System.out.println("Email Sent Successfully to: " + student.getEmail());
            
        } catch (Exception e) {
            System.out.println("Email Sending Failed: " + e.getMessage());
        }
    }

    // --- Update Email යැවීම ---
    public static void sendUpdateEmail(StudentDto student) {
        try {
            Session session = MailConnection.getInstance().getSession();
            String senderEmail = MailConnection.getInstance().getSenderEmail();
            
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(student.getEmail()));
            message.setSubject("Account Update Notification - Central College Anuradhapura");
            
            String emailBody = "<html>"
            + "<body style='margin:0; padding:0; background-color:#F3F7F4; font-family:Arial, Helvetica, sans-serif; color:#333333;'>"
            + "<div style='width:100%; padding:30px 0;'>"
            + "<table align='center' width='650' cellpadding='0' cellspacing='0' style='width:650px; max-width:650px; background-color:#ffffff; border-collapse:collapse; border-radius:12px; overflow:hidden; box-shadow:0 4px 15px rgba(0,0,0,0.08);'>"
            
            // HEADER
            + "<tr>"
            + "<td style='background-color:#006B3C; padding:30px 25px; text-align:center;'>"
            + "<img src='https://anucentralcollege.com/og-image.jpg' alt='Central College Anuradhapura Logo' style='width:85px; height:85px; border-radius:50%; background-color:#ffffff; padding:6px; border:3px solid #D4AF37;'>"
            + "<h1 style='margin:15px 0 5px 0; color:#ffffff; font-size:24px; letter-spacing:0.5px;'>CENTRAL COLLEGE ANURADHAPURA</h1>"
            + "<p style='margin:0; color:#F5D76E; font-size:14px; font-weight:bold; letter-spacing:0.5px;'>STUDENT MANAGEMENT SYSTEM</p>"
            + "</td>"
            + "</tr>"
            
            // GOLD LINE
            + "<tr><td style='height:5px; background-color:#D4AF37;'></td></tr>"
            
            // GREETING
            + "<tr>"
            + "<td style='padding:35px 40px 10px 40px;'>"
            + "<p style='margin:0 0 12px 0; font-size:16px;'>Dear <strong style='color:#006B3C;'>" + student.getName() + "</strong>,</p>"
            + "<h2 style='margin:0 0 15px 0; color:#006B3C; font-size:23px;'>Student Profile Updated</h2>"
            + "<p style='margin:0; color:#555555; font-size:15px; line-height:1.7;'>This is to inform you that your student profile in the <strong>Central College Anuradhapura Student Management System</strong> has been successfully updated by the school administration.</p>"
            + "</td>"
            + "</tr>"
            
            // STUDENT ID BOX
            + "<tr>"
            + "<td style='padding:20px 40px;'>"
            + "<div style='background-color:#F0F8F3; border:1px solid #C8E6D4; border-left:5px solid #006B3C; border-radius:7px; padding:18px 20px;'>"
            + "<p style='margin:0; font-size:12px; color:#6B6B6B; font-weight:bold; letter-spacing:1px;'>STUDENT ID</p>"
            + "<p style='margin:7px 0 0 0; font-size:24px; font-weight:bold; color:#006B3C;'>" + student.getStudentId() + "</p>"
            + "</div>"
            + "</td>"
            + "</tr>"
            
            // UPDATE INFORMATION
            + "<tr>"
            + "<td style='padding:5px 40px 12px 40px;'>"
            + "<h3 style='margin:0; color:#006B3C; font-size:18px;'>Updated Student Information</h3>"
            + "<div style='width:45px; height:3px; background-color:#D4AF37; margin-top:7px;'></div>"
            + "</td>"
            + "</tr>"
            
            // DETAILS TABLE
            + "<tr>"
            + "<td style='padding:5px 40px 25px 40px;'>"
            + "<table width='100%' cellpadding='0' cellspacing='0' style='border-collapse:collapse; font-size:14px;'>"
            
            // Full Name
            + "<tr>"
            + "<td style='padding:12px; background-color:#F5F9F6; border-bottom:1px solid #E4EAE6; width:40%; font-weight:bold; color:#365443;'>Full Name</td>"
            + "<td style='padding:12px; border-bottom:1px solid #E4EAE6;'>" + student.getName() + "</td>"
            + "</tr>"
            
            // Date of Birth
            + "<tr>"
            + "<td style='padding:12px; background-color:#F5F9F6; border-bottom:1px solid #E4EAE6; font-weight:bold; color:#365443;'>Date of Birth</td>"
            + "<td style='padding:12px; border-bottom:1px solid #E4EAE6;'>" + student.getBirthday() + "</td>"
            + "</tr>"
            
            // Gender
            + "<tr>"
            + "<td style='padding:12px; background-color:#F5F9F6; border-bottom:1px solid #E4EAE6; font-weight:bold; color:#365443;'>Gender</td>"
            + "<td style='padding:12px; border-bottom:1px solid #E4EAE6;'>" + student.getGender() + "</td>"
            + "</tr>"
            
            // Class (වෙනස: getClassId වෙනුවට getClassName යොදා ඇත)
            + "<tr>"
            + "<td style='padding:12px; background-color:#F5F9F6; border-bottom:1px solid #E4EAE6; font-weight:bold; color:#365443;'>Class</td>"
            + "<td style='padding:12px; border-bottom:1px solid #E4EAE6;'>" + (student.getClassName() != null ? student.getClassName() : "-") + "</td>"
            + "</tr>"
            
            // Contact Number
            + "<tr>"
            + "<td style='padding:12px; background-color:#F5F9F6; border-bottom:1px solid #E4EAE6; font-weight:bold; color:#365443;'>Contact Number</td>"
            + "<td style='padding:12px; border-bottom:1px solid #E4EAE6;'>" + student.getContactNumber() + "</td>"
            + "</tr>"
            
            // Address
            + "<tr>"
            + "<td style='padding:12px; background-color:#F5F9F6; border-bottom:1px solid #E4EAE6; font-weight:bold; color:#365443;'>Address</td>"
            + "<td style='padding:12px; border-bottom:1px solid #E4EAE6;'>" + student.getAddress() + "</td>"
            + "</tr>"
            
            // Email
            + "<tr>"
            + "<td style='padding:12px; background-color:#F5F9F6; border-bottom:1px solid #E4EAE6; font-weight:bold; color:#365443;'>Registered Email</td>"
            + "<td style='padding:12px; border-bottom:1px solid #E4EAE6;'>" + student.getEmail() + "</td>"
            + "</tr>"
            
            // Status
            + "<tr>"
            + "<td style='padding:12px; background-color:#F5F9F6; font-weight:bold; color:#365443;'>Account Status</td>"
            + "<td style='padding:12px; color:#198754; font-weight:bold;'>✓ " + student.getStatus() + "</td>"
            + "</tr>"
            + "</table>"
            + "</td>"
            + "</tr>"
            
            // IMPORTANT NOTICE
            + "<tr>"
            + "<td style='padding:0 40px 25px 40px;'>"
            + "<div style='background-color:#FFF9E8; border:1px solid #E8D58A; border-left:5px solid #D4AF37; border-radius:7px; padding:18px 20px;'>"
            + "<h3 style='margin:0 0 10px 0; color:#6B5200; font-size:16px;'>Important Information</h3>"
            + "<p style='margin:0; color:#665A35; font-size:13px; line-height:1.7;'>The information displayed above represents the current student profile details stored in the school management system. Please review the information carefully and ensure that all details are correct.</p>"
            + "</div>"
            + "</td>"
            + "</tr>"
            
            // SECURITY NOTICE
            + "<tr>"
            + "<td style='padding:0 40px 25px 40px;'>"
            + "<div style='background-color:#F4F9F6; border-left:4px solid #006B3C; padding:15px 18px; border-radius:5px;'>"
            + "<p style='margin:0; color:#4B5B51; font-size:13px; line-height:1.6;'><strong style='color:#006B3C;'>Security Notice</strong><br>If you did not request or expect these changes, please contact the school administration or IT department immediately. Do not share your account credentials with anyone.</p>"
            + "</div>"
            + "</td>"
            + "</tr>"
            
            // CLOSING
            + "<tr>"
            + "<td style='padding:5px 40px 30px 40px;'>"
            + "<p style='margin:0; font-size:14px; color:#555555; line-height:1.7;'>Thank you for keeping your student information up to date.</p>"
            + "<p style='margin:15px 0 0 0; font-size:14px; line-height:1.7;'>Sincerely,<br><strong style='color:#006B3C;'>The Administration</strong><br>Central College Anuradhapura</p>"
            + "</td>"
            + "</tr>"
            
            // FOOTER
            + "<tr>"
            + "<td style='background-color:#004D2C; border-top:4px solid #D4AF37; padding:22px 30px; text-align:center;'>"
            + "<p style='margin:0 0 6px 0; color:#ffffff; font-size:13px; font-weight:bold;'>CENTRAL COLLEGE ANURADHAPURA</p>"
            + "<p style='margin:0; color:#C8DED3; font-size:11px;'>Student Management System</p>"
            + "<p style='margin:8px 0 0 0; color:#A9C7B8; font-size:10px;'>This is an automated email. Please do not reply directly to this message.</p>"
            + "</td>"
            + "</tr>"
            + "</table>"
            + "</div>"
            + "</body>"
            + "</html>";
            
            message.setContent(emailBody, "text/html; charset=utf-8");
            Transport.send(message);
            System.out.println("Update Email Sent to: " + student.getEmail());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // --- Delete Email යැවීම (අලුත් Template එක සමග) ---
    public static void sendDeleteEmail(String email, String name, String studentId) {
        try {
            Session session = MailConnection.getInstance().getSession();
            String senderEmail = MailConnection.getInstance().getSenderEmail();
            
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Account Deletion Notice - Central College Anuradhapura");
            
            String deletionDateTime = DateandTimeConnection.getInstance().getCurrentDateTime();
            
            String emailBody = "<html>"
            + "<body style='margin:0; padding:0; background-color:#F3F7F4; font-family:Arial, Helvetica, sans-serif; color:#333333;'>"
            + "<div style='width:100%; padding:30px 0;'>"
            + "<table align='center' width='650' cellpadding='0' cellspacing='0' style='width:650px; max-width:650px; background-color:#ffffff; border-collapse:collapse; border-radius:12px; overflow:hidden; box-shadow:0 4px 15px rgba(0,0,0,0.08);'>"
            
            // HEADER
            + "<tr>"
            + "<td style='background-color:#006B3C; padding:30px 25px; text-align:center;'>"
            + "<img src='https://anucentralcollege.com/og-image.jpg' alt='Central College Anuradhapura Logo' style='width:85px; height:85px; border-radius:50%; background-color:#ffffff; padding:6px; border:3px solid #D4AF37;'>"
            + "<h1 style='margin:15px 0 5px 0; color:#ffffff; font-size:24px; letter-spacing:0.5px;'>CENTRAL COLLEGE ANURADHAPURA</h1>"
            + "<p style='margin:0; color:#F5D76E; font-size:14px; font-weight:bold; letter-spacing:0.5px;'>STUDENT MANAGEMENT SYSTEM</p>"
            + "</td>"
            + "</tr>"
            
            // GOLD LINE
            + "<tr><td style='height:5px; background-color:#D4AF37;'></td></tr>"
            
            // GREETING
            + "<tr>"
            + "<td style='padding:35px 40px 10px 40px;'>"
            + "<p style='margin:0 0 12px 0; font-size:16px;'>Dear <strong style='color:#006B3C;'>" + name + "</strong>,</p>"
            + "<h2 style='margin:0 0 15px 0; color:#B02A37; font-size:23px;'>Student Account Removed</h2>"
            + "<p style='margin:0; color:#555555; font-size:15px; line-height:1.7;'>We are writing to inform you that your student account has been removed from the <strong>Central College Anuradhapura Student Management System</strong>.</p>"
            + "</td>"
            + "</tr>"
            
            // DELETED ACCOUNT BOX
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
            + "<h3 style='margin:0; color:#006B3C; font-size:18px;'>Deleted Account Details</h3>"
            + "<div style='width:45px; height:3px; background-color:#D4AF37; margin-top:7px;'></div>"
            + "</td>"
            + "</tr>"
            
            // DETAILS TABLE
            + "<tr>"
            + "<td style='padding:5px 40px 25px 40px;'>"
            + "<table width='100%' cellpadding='0' cellspacing='0' style='border-collapse:collapse; font-size:14px;'>"
            + "<tr>"
            + "<td style='padding:12px; background-color:#F5F9F6; border-bottom:1px solid #E4EAE6; width:40%; font-weight:bold; color:#365443;'>Student ID</td>"
            + "<td style='padding:12px; border-bottom:1px solid #E4EAE6; font-weight:bold; color:#B02A37;'>" + studentId + "</td>"
            + "</tr>"
            + "<tr>"
            + "<td style='padding:12px; background-color:#F5F9F6; border-bottom:1px solid #E4EAE6; font-weight:bold; color:#365443;'>Student Name</td>"
            + "<td style='padding:12px; border-bottom:1px solid #E4EAE6;'>" + name + "</td>"
            + "</tr>"
            + "<tr>"
            + "<td style='padding:12px; background-color:#F5F9F6; border-bottom:1px solid #E4EAE6; font-weight:bold; color:#365443;'>Date & Time of Deletion</td>"
            + "<td style='padding:12px; border-bottom:1px solid #E4EAE6; color:#555555;'>" + deletionDateTime + "</td>"
            + "</tr>"
            + "<tr>"
            + "<td style='padding:12px; background-color:#F5F9F6; font-weight:bold; color:#365443;'>Account Status</td>"
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
            + "<p style='margin:0; color:#665A35; font-size:13px; line-height:1.7;'>Your student account is no longer active in the school Student Management System. You will no longer be able to access the system using this account.</p>"
            + "</div>"
            + "</td>"
            + "</tr>"
            
            // SECURITY NOTICE
            + "<tr>"
            + "<td style='padding:0 40px 25px 40px;'>"
            + "<div style='background-color:#F4F9F6; border-left:4px solid #006B3C; padding:15px 18px; border-radius:5px;'>"
            + "<p style='margin:0; color:#4B5B51; font-size:13px; line-height:1.6;'><strong style='color:#006B3C;'>Did you not expect this?</strong><br>If you believe this account was removed by mistake or without proper authorization, please contact the school administration office or IT department immediately.</p>"
            + "</div>"
            + "</td>"
            + "</tr>"
            
            // SUPPORT & CLOSING
            + "<tr>"
            + "<td style='padding:0 40px 25px 40px;'>"
            + "<p style='margin:0; font-size:13px; color:#666666; line-height:1.7;'>For further assistance regarding your student account, please contact the Central College Anuradhapura administration office.</p>"
            + "</td>"
            + "</tr>"
            + "<tr>"
            + "<td style='padding:5px 40px 30px 40px;'>"
            + "<p style='margin:0; font-size:14px; color:#555555; line-height:1.7;'>Thank you for your understanding.</p>"
            + "<p style='margin:15px 0 0 0; font-size:14px; line-height:1.7;'>Sincerely,<br><strong style='color:#006B3C;'>The Administration</strong><br>Central College Anuradhapura</p>"
            + "</td>"
            + "</tr>"
            
            // FOOTER
            + "<tr>"
            + "<td style='background-color:#004D2C; border-top:4px solid #D4AF37; padding:22px 30px; text-align:center;'>"
            + "<p style='margin:0 0 6px 0; color:#ffffff; font-size:13px; font-weight:bold;'>CENTRAL COLLEGE ANURADHAPURA</p>"
            + "<p style='margin:0; color:#C8DED3; font-size:11px;'>Student Management System</p>"
            + "<p style='margin:8px 0 0 0; color:#A9C7B8; font-size:10px;'>This is an automated email. Please do not reply directly to this message.</p>"
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