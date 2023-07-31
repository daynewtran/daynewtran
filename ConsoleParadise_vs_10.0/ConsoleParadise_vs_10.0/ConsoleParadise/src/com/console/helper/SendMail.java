
package com.console.helper;

import java.io.File;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;



/**
 *
 * @author DELL
 */
public class SendMail {
    public static void send(String tieuDe, String NoiDung,String gmail){
        final String username = "consoleparadise17304@gmail.com";
        final String password = "oxrkxkdftcirglyo";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            String to = gmail;
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("consoleparadise17304@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(to)
            );
            message.setSubject("CONSOLE PARADISE "+tieuDe);
            message.setText(NoiDung);
            //System.out.println("Done");
            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            System.out.println(e);
        }
    }
    
    public static void send(String tieuDe, String NoiDung,String gmail, String file){
        final String username = "consoleparadise17304@gmail.com";
        final String password = "oxrkxkdftcirglyo";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            String to = gmail;
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("consoleparadise17304@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(to)
            );
            message.setSubject("CONSOLE PARADISE "+tieuDe);
            message.setText(NoiDung);
            //String imagePath = file;
//            System.out.println(file);
//            //DataSource source2 = new FileDataSource(imagePath);
//            
//            //        String imagePath = file;
//        DataSource source2 = new FileDataSource(file);
//        System.out.println(source2);
            
            // tạo một đối tượng BodyPart để ghi nội dung vào body của Email
            BodyPart bodyPart = new MimeBodyPart();
            //bodyPart.setText(content);
            // tạo lớp MultyPart để chứa BodyPart
            Multipart mtp = new MimeMultipart();
            mtp.addBodyPart(bodyPart);
            // nếu có File thì tạo thêm một bodyPart khác để chứa file
            
                MimeBodyPart bodyPart2 = new MimeBodyPart();
                DataSource source = new FileDataSource(file);
                bodyPart2.setDataHandler(new DataHandler(source));
                mtp.addBodyPart(bodyPart2);
            message.setContent(mtp);
//------------------------------------------------------------------------------------------            
// phan 3 chua tap tin image
//        MimeBodyPart messageBodyPart3 = new MimeBodyPart();
//        // Duong dan den file cua ban
//        String imagePath = file;
//        DataSource source2 = new FileDataSource(imagePath);
//        messageBodyPart3.setDataHandler(new DataHandler(source2));
//        messageBodyPart3.setFileName(imagePath);
//        Multipart multipart = new MimeMultipart();
////        multipart.addBodyPart(messageBodyPart1);
////        multipart.addBodyPart(messageBodyPart2);
//        multipart.addBodyPart(messageBodyPart3);
//        message.setContent(multipart);
         // Gui cac phan day du cua message
         
            Transport.send(message, message.getAllRecipients());

            System.out.println("Done");

        } catch (MessagingException e) {
            System.out.println(e);
        }
    }
    public static void SendPDF(String tieuDe, String noiDung, String mail, String file) throws MessagingException {


        final String fromEmail = "consoleparadise17304@gmail.com";
        // Mat khai email cua ban
        final String password = "oxrkxkdftcirglyo";
        // dia chi email nguoi nhan
        final String toEmail = mail;

        final String subject = tieuDe;
        final String body = noiDung;

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(fromEmail));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
        message.setSubject(subject);

        // Phan 1 gom doan tin nhan
        BodyPart messageBodyPart1 = new MimeBodyPart();
        messageBodyPart1.setText(body);

        // phan 2 chua tap tin txt
        MimeBodyPart messageBodyPart2 = new MimeBodyPart();
        // Duong dan den file cua ban
        String filePath = file;
        DataSource source1 = new FileDataSource(filePath);
        messageBodyPart2.setDataHandler(new DataHandler(source1));
        messageBodyPart2.setFileName(filePath);

//        // phan 3 chua tap tin image
//        MimeBodyPart messageBodyPart3 = new MimeBodyPart();
//        // Duong dan den file cua ban
//        String imagePath = "Capture.JPG";
//        DataSource source2 = new FileDataSource(imagePath);
//        messageBodyPart3.setDataHandler(new DataHandler(source2));
//        messageBodyPart3.setFileName(imagePath);

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart1);
        multipart.addBodyPart(messageBodyPart2);
        //multipart.addBodyPart(messageBodyPart3);
        message.setContent(multipart);

        Transport.send(message);

        System.out.println("Gui mail thanh cong");

    }
}
    
    


