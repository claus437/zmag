package dk.clausreimer.zmag.mailservice;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


public class SmtpMail implements Mail {
    private MimeMessage message;

    public SmtpMail() {
        Properties properties;
        Session session;

        properties = System.getProperties();
        session = Session.getDefaultInstance(properties, new PropertyAuthenticator(properties));
        message = new MimeMessage(session);
    }

    public void setFrom(String from) {
        try {
            message.setFrom(new InternetAddress(from));
        } catch (MessagingException x) {
            throw new MailException("Invalid from address " + from, x);
        }
    }

    public void setTo(String to) {
        try {
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        } catch (MessagingException x) {
            throw new MailException("Invalid to address " + to, x);
        }
    }

    public void setSubject(String subject) {
        try {
            message.setSubject(subject);
        } catch (MessagingException x) {
            throw new MailException("Invalid subject " + subject, x);
        }
    }

    public void setMessage(String text) {
        try {
            message.setText(text);
        } catch (MessagingException x) {
            throw new MailException("Invalid message \"" + text + "\"", x);
        }
    }

    public void send() {
        try {
            Transport.send(message);
        } catch (MessagingException x) {
            throw new MailException("failed sending mail ", x);
        }
    }
}
