package Controller;

import MailConnection.MailConnection;
import java.io.File;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.Session;
import DateandTimeConnection.DateandTimeConnection;

public class CommonSupportEmailService {

    public static boolean sendSupportRequest(String userInfo, String issueMessage, String attachmentFilePath) {
        try {
            Session session = MailConnection.getInstance().getSession();
            String senderEmail = MailConnection.getInstance().getSenderEmail();
            
            // 🔴 මෙතනට ඔයාගේ Admin ගේ ඊමේල් එක දෙන්න
            String adminEmail = "your.admin.email@gmail.com"; 
            
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(adminEmail));
            message.setSubject("SYSTEM SUPPORT TICKET - Central College Anuradhapura");
            
            String requestTime = DateandTimeConnection.getInstance().getCurrentDateTime();
            
            boolean hasAttachment = (attachmentFilePath != null && !attachmentFilePath.isEmpty());
            String fileName = "";
            File fileToAttach = null;
            
            // Attachment එකට අනන්‍ය වූ ID එකක් (CID) සෑදීම
            String contentId = "attachment_" + System.currentTimeMillis(); 
            
            if (hasAttachment) {
                fileToAttach = new File(attachmentFilePath);
                fileName = fileToAttach.getName(); 
            }

            // Premium Black & Gold Email Body (HTML) සැකසීම
            StringBuilder emailHtml = new StringBuilder();
            emailHtml.append("<html>")
            .append("<body style='margin:0; padding:0; background-color:#F5F5F5; font-family:Arial, Helvetica, sans-serif; color:#333333;'>")
            .append("<div style='width:100%; padding:30px 0;'>")
            .append("<table align='center' width='650' cellpadding='0' cellspacing='0' style='width:650px; max-width:650px; background-color:#ffffff; border-collapse:collapse; border-radius:12px; overflow:hidden; box-shadow:0 4px 15px rgba(0,0,0,0.08);'>")
            
            // HEADER (Premium Black)
            .append("<tr>")
            .append("<td style='background-color:#1C1C1C; padding:30px 25px; text-align:center;'>")
            .append("<img src='https://anucentralcollege.com/og-image.jpg' alt='Central College Anuradhapura Logo' style='width:85px; height:85px; border-radius:50%; background-color:#ffffff; padding:6px; border:3px solid #D4AF37;'>")
            .append("<h1 style='margin:15px 0 5px 0; color:#ffffff; font-size:24px; letter-spacing:0.5px;'>CENTRAL COLLEGE ANURADHAPURA</h1>")
            .append("<p style='margin:0; color:#D4AF37; font-size:14px; font-weight:bold; letter-spacing:0.5px;'>SYSTEM SUPPORT TICKET</p>")
            .append("</td>")
            .append("</tr>")
            
            // GOLD LINE
            .append("<tr><td style='height:5px; background-color:#D4AF37;'></td></tr>")
            
            // GREETING
            .append("<tr>")
            .append("<td style='padding:35px 40px 10px 40px;'>")
            .append("<h2 style='margin:0 0 15px 0; color:#1C1C1C; font-size:23px;'>Help Request Submitted</h2>")
            .append("<p style='margin:0; color:#555555; font-size:15px; line-height:1.7;'>A user is experiencing an issue and has requested administrative assistance via the Central College Anuradhapura System.</p>")
            .append("</td>")
            .append("</tr>")
            
            // REQUEST DETAILS BOX
            .append("<tr>")
            .append("<td style='padding:20px 40px;'>")
            .append("<div style='background-color:#F9F9F9; border:1px solid #EEEEEE; border-left:5px solid #1C1C1C; border-radius:7px; padding:18px 20px;'>")
            .append("<p style='margin:0 0 10px 0; font-size:14px; color:#333333;'><strong>User ID / Email:</strong> <span style='color:#1C1C1C; font-weight:bold;'>").append(userInfo).append("</span></p>")
            .append("<p style='margin:0 0 15px 0; font-size:14px; color:#333333;'><strong>Time of Request:</strong> ").append(requestTime).append("</p>")
            .append("<p style='margin:0 0 8px 0; font-size:14px; color:#333333;'><strong>Issue / Message Description:</strong></p>")
            .append("<div style='background-color:#ffffff; padding:12px; border:1px solid #D9D9D9; border-radius:4px; color:#444444; font-size:14px; font-style:italic; line-height:1.6;'>").append(issueMessage).append("</div>")
            .append("</div>")
            .append("</td>")
            .append("</tr>");
            
            // ATTACHMENT CARD & CID DOWNLOAD BUTTON
            if (hasAttachment && fileToAttach.exists()) {
                emailHtml.append("<tr>")
                .append("<td style='padding:0 40px 25px 40px;'>")
                .append("<div style='background-color:#FFF9E8; border:1px dashed #D4AF37; border-radius:6px; padding:20px; text-align:center;'>")
                .append("<h4 style='margin:0 0 10px 0; color:#1C1C1C; font-size:16px;'>📎 Attached Evidence / File</h4>")
                
                .append("<div style='display:inline-block; background-color:#ffffff; border:1px solid #E8D58A; padding:10px 20px; border-radius:4px; font-weight:bold; color:#333333; font-size:14px; box-shadow:0 2px 5px rgba(0,0,0,0.05); margin-bottom:15px;'>")
                .append(fileName)
                .append("</div>")
                
                .append("<br>")
                // Content-ID use to add attagement to html body
                .append("<a href='cid:").append(contentId).append("' style='background-color:#1C1C1C; color:#D4AF37; padding:10px 25px; text-decoration:none; border-radius:5px; font-weight:bold; font-size:14px; display:inline-block; border:1px solid #D4AF37;'>⬇ Open / View Attached File</a>")
                
                .append("<p style='margin:15px 0 0 0; color:#888888; font-size:11px;'>* If the button does not work, please find the attachment at the very bottom of this email.</p>")
                .append("</div>")
                .append("</td>")
                .append("</tr>");
            }
            
            // FOOTER (Pitch Black)
            emailHtml.append("<tr>")
            .append("<td style='background-color:#0A0A0A; border-top:4px solid #D4AF37; padding:22px 30px; text-align:center;'>")
            .append("<p style='margin:0 0 6px 0; color:#ffffff; font-size:13px; font-weight:bold;'>CENTRAL COLLEGE ANURADHAPURA</p>")
            .append("<p style='margin:0; color:#CCCCCC; font-size:11px;'>Administration Portal - Support Services</p>")
            .append("<p style='margin:8px 0 0 0; color:#888888; font-size:10px;'>This is an automated system support alert. Please review the request and take necessary actions.</p>")
            .append("</td>")
            .append("</tr>")
            .append("</table>")
            .append("</div>")
            .append("</body>")
            .append("</html>");

            Multipart multipart = new MimeMultipart();

            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setContent(emailHtml.toString(), "text/html; charset=utf-8");
            multipart.addBodyPart(textPart);

            // setup file and CID  Set 
            if (hasAttachment && fileToAttach.exists()) {
                MimeBodyPart attachmentPart = new MimeBodyPart();
                DataSource source = new FileDataSource(attachmentFilePath);
                attachmentPart.setDataHandler(new DataHandler(source));
                attachmentPart.setFileName(fileName);
            // get Content-ID for link html
                attachmentPart.setContentID("<" + contentId + ">");
                attachmentPart.setDisposition(MimeBodyPart.ATTACHMENT);
                
                multipart.addBodyPart(attachmentPart);
            }

            message.setContent(multipart);
            Transport.send(message);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}