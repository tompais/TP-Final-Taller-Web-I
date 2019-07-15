package ar.edu.unlam.tallerweb1.Helpers;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailHelper {
    private static void sendAsHtml(String to, String title, String html) throws MessagingException {
        Session session = createSession();

        //create message using session
        MimeMessage message = new MimeMessage(session);
        prepareEmailMessage(message, to, title, html);

        //sending message
        Transport.send(message);
    }

    private static void prepareEmailMessage(MimeMessage message, String to, String title, String html)
            throws MessagingException {
        message.setContent(html, "text/html; charset=utf-8");
        message.setFrom(new InternetAddress(ConstanteHelper.CINEAPPEMAIL));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(title);
    }

    private static Session createSession() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        return Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(ConstanteHelper.CINEAPPEMAIL, ConstanteHelper.CINEAPPPEMAILPASS);
            }
        });
    }

    public static void EnviarMail(String asunto, String html, String mail) throws MessagingException {
        MailHelper.sendAsHtml(mail,
                asunto,
                html);
    }
}
