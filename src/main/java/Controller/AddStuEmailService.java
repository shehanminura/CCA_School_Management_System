package Controller;

import Model.dto.StudentDto;
import MailConnection.MailConnection;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Session;

public class AddStuEmailService {
    
    public static void sendWelcomeEmail(StudentDto student) {
        try {
            // 1. MailConnection එකෙන් Session එක සහ Sender Email එක ගැනීම
            Session session = MailConnection.getInstance().getSession();
            String senderEmail = MailConnection.getInstance().getSenderEmail();
            
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            
            // 2. අලුත් ළමයාගේ Email එකට යැවීම
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(student.getEmail()));
            message.setSubject("Official Student Registration - Central College Anuradhapura");
            
            // 3. Email Body
          // 3. HTML Email Body එක ලස්සනට සැකසීම
           String emailBody = "<html>"
        + "<body style='margin:0; padding:0; background-color:#f4f7fb; font-family:Arial, Helvetica, sans-serif; color:#333333;'>"

        // Main Container
        + "<div style='width:100%; padding:30px 0;'>"

        + "<table align='center' width='650' cellpadding='0' cellspacing='0' "
        + "style='background-color:#ffffff; border-radius:12px; overflow:hidden; "
        + "box-shadow:0 4px 15px rgba(0,0,0,0.08);'>"

        // ================= HEADER =================
        + "<tr>"
        + "<td style='background-color:#063970; padding:28px 30px; text-align:center;'>"

        + "<img src='https://anucentralcollege.com/og-image.jpg' "
        + "alt='Central College Anuradhapura' "
        + "style='width:85px; height:85px; border-radius:50%; background-color:white; padding:5px;'>"

        + "<h1 style='color:#ffffff; margin:15px 0 5px 0; font-size:25px;'>"
        + "Central College Anuradhapura"
        + "</h1>"

        + "<p style='color:#dcecff; margin:0; font-size:14px;'>"
        + "Student Management System"
        + "</p>"

        + "</td>"
        + "</tr>"

        // ================= WELCOME =================
        + "<tr>"
        + "<td style='padding:35px 40px 15px 40px;'>"

        + "<p style='font-size:16px; margin:0 0 15px 0;'>"
        + "Dear <strong>" + student.getName() + "</strong>,"
        + "</p>"

        + "<h2 style='color:#063970; margin:0 0 15px 0; font-size:22px;'>"
        + "Registration Successful!"
        + "</h2>"

        + "<p style='font-size:15px; line-height:1.7; color:#555555;'>"
        + "We are pleased to inform you that your registration with the "
        + "<strong>Central College Anuradhapura Student Management System</strong> "
        + "has been successfully completed."
        + "</p>"

        + "<p style='font-size:15px; line-height:1.7; color:#555555;'>"
        + "Please find your registration details and system login information below."
        + "</p>"

        + "</td>"
        + "</tr>"

        // ================= STUDENT ID BOX =================
        + "<tr>"
        + "<td style='padding:5px 40px 20px 40px;'>"

        + "<div style='background-color:#eef6ff; border-left:5px solid #063970; "
        + "padding:18px 20px; border-radius:6px;'>"

        + "<p style='margin:0; color:#555555; font-size:13px;'>"
        + "YOUR STUDENT ID"
        + "</p>"

        + "<p style='margin:6px 0 0 0; color:#063970; font-size:24px; font-weight:bold;'>"
        + student.getStudentId()
        + "</p>"

        + "</div>"

        + "</td>"
        + "</tr>"

        // ================= DETAILS TITLE =================
        + "<tr>"
        + "<td style='padding:10px 40px 5px 40px;'>"

        + "<h3 style='color:#063970; margin:0; font-size:18px;'>"
        + "Student Registration Details"
        + "</h3>"

        + "</td>"
        + "</tr>"

        // ================= DETAILS TABLE =================
        + "<tr>"
        + "<td style='padding:10px 40px 20px 40px;'>"

        + "<table width='100%' cellpadding='0' cellspacing='0' "
        + "style='border-collapse:collapse; font-size:14px;'>"

        + "<tr>"
        + "<td style='padding:12px; border-bottom:1px solid #eeeeee; "
        + "background-color:#f8fafc; font-weight:bold; width:40%;'>Full Name</td>"
        + "<td style='padding:12px; border-bottom:1px solid #eeeeee;'>"
        + student.getName()
        + "</td>"
        + "</tr>"

        + "<tr>"
        + "<td style='padding:12px; border-bottom:1px solid #eeeeee; "
        + "background-color:#f8fafc; font-weight:bold;'>Date of Birth</td>"
        + "<td style='padding:12px; border-bottom:1px solid #eeeeee;'>"
        + student.getBirthday()
        + "</td>"
        + "</tr>"

        + "<tr>"
        + "<td style='padding:12px; border-bottom:1px solid #eeeeee; "
        + "background-color:#f8fafc; font-weight:bold;'>Gender</td>"
        + "<td style='padding:12px; border-bottom:1px solid #eeeeee;'>"
        + student.getGender()
        + "</td>"
        + "</tr>"

        + "<tr>"
        + "<td style='padding:12px; border-bottom:1px solid #eeeeee; "
        + "background-color:#f8fafc; font-weight:bold;'>Contact Number</td>"
        + "<td style='padding:12px; border-bottom:1px solid #eeeeee;'>"
        + student.getContactNumber()
        + "</td>"
        + "</tr>"

        + "<tr>"
        + "<td style='padding:12px; border-bottom:1px solid #eeeeee; "
        + "background-color:#f8fafc; font-weight:bold;'>Address</td>"
        + "<td style='padding:12px; border-bottom:1px solid #eeeeee;'>"
        + student.getAddress()
        + "</td>"
        + "</tr>"

        + "<tr>"
        + "<td style='padding:12px; border-bottom:1px solid #eeeeee; "
        + "background-color:#f8fafc; font-weight:bold;'>Email</td>"
        + "<td style='padding:12px; border-bottom:1px solid #eeeeee;'>"
        + student.getEmail()
        + "</td>"
        + "</tr>"

        + "<tr>"
        + "<td style='padding:12px; background-color:#f8fafc; font-weight:bold;'>"
        + "Account Status"
        + "</td>"

        + "<td style='padding:12px; color:#198754; font-weight:bold;'>"
        + "✓ " + student.getStatus()
        + "</td>"

        + "</tr>"

        + "</table>"

        + "</td>"
        + "</tr>"

        // ================= LOGIN SECTION =================
        + "<tr>"
        + "<td style='padding:5px 40px 25px 40px;'>"

        + "<div style='background-color:#f8f9fa; border:1px solid #e1e5ea; "
        + "border-radius:8px; padding:20px;'>"

        + "<h3 style='margin:0 0 15px 0; color:#063970;'>"
        + "System Login Information"
        + "</h3>"

        + "<p style='margin:8px 0; font-size:14px;'>"
        + "<strong>Student ID:</strong> "
        + student.getStudentId()
        + "</p>"

        + "<p style='margin:8px 0; font-size:14px;'>"
        + "<strong>Registered Email:</strong> "
        + student.getEmail()
        + "</p>"

        + "<p style='margin:8px 0; font-size:14px;'>"
        + "<strong>Temporary Password:</strong> "
        + "<span style='font-family:monospace; background:#ffffff; "
        + "padding:5px 8px; border:1px solid #ddd; border-radius:4px;'>"
        + student.getPassword()
        + "</span>"
        + "</p>"

        + "</div>"

        + "</td>"
        + "</tr>"

        // ================= SECURITY WARNING =================
        + "<tr>"
        + "<td style='padding:0 40px 25px 40px;'>"

        + "<div style='background-color:#fff8e6; border:1px solid #ffe08a; "
        + "border-radius:7px; padding:15px;'>"

        + "<p style='margin:0; color:#7a5b00; font-size:13px; line-height:1.6;'>"
        + "<strong>🔐 Security Notice</strong><br>"
        + "Please keep your login credentials confidential. Do not share your "
        + "password with other students or unauthorized persons. "
        + "For your security, please change your temporary password after your first login."
        + "</p>"

        + "</div>"

        + "</td>"
        + "</tr>"

        // ================= ADMIN MESSAGE =================
        + "<tr>"
        + "<td style='padding:0 40px 25px 40px;'>"

        + "<p style='font-size:14px; color:#555555; line-height:1.7; margin:0;'>"
        + "If any of the information provided in this email is incorrect, "
        + "please contact the school administration or system administrator "
        + "as soon as possible."
        + "</p>"

        + "</td>"
        + "</tr>"

        // ================= CLOSING =================
        + "<tr>"
        + "<td style='padding:5px 40px 30px 40px;'>"

        + "<p style='font-size:14px; line-height:1.7; margin:0;'>"
        + "We wish you every success in your academic journey."
        + "</p>"

        + "<p style='font-size:14px; line-height:1.7; margin:15px 0 0 0;'>"
        + "Sincerely,<br>"
        + "<strong style='color:#063970;'>The Administration</strong><br>"
        + "Central College Anuradhapura"
        + "</p>"

        + "</td>"
        + "</tr>"

        // ================= FOOTER =================
        + "<tr>"
        + "<td style='background-color:#063970; padding:20px 30px; text-align:center;'>"

        + "<p style='color:#ffffff; margin:0 0 5px 0; font-size:13px;'>"
        + "<strong>Central College Anuradhapura</strong>"
        + "</p>"

        + "<p style='color:#bcd2e8; margin:0; font-size:11px;'>"
        + "This is an automated email from the Student Management System."
        + "</p>"

        + "<p style='color:#bcd2e8; margin:8px 0 0 0; font-size:11px;'>"
        + "Please do not reply directly to this email."
        + "</p>"

        + "</td>"
        + "</tr>"

        + "</table>"
        + "</div>"

        + "</body>"
        + "</html>";
            // 4. සකස් කළ HTML එක Email එකට Set කිරීම (setText වෙනුවට setContent භාවිතය)
            message.setContent(emailBody, "text/html; charset=utf-8");
            
            // 4. Email එක යැවීම
            Transport.send(message);
            System.out.println("Email Sent Successfully to: " + student.getEmail());
            
        } catch (Exception e) {
            System.out.println("Email Sending Failed: " + e.getMessage());
        }
    }
}