package dk.clausreimer.zmag.mailservice;

import org.junit.Test;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: claus
 * Date: 10-07-11
 * Time: 12:03
 * To change this template use File | Settings | File Templates.
 */
public class GMailTest {

    @Test
    public void noTest() {
    }

    public static void main(String[] args) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("claus437","minitrans");
				}
			});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("claus437@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse("cbr@fujitsu.dk"));
			message.setSubject("Testing Subject");
			message.setText("Dear Mail Crawler," +
					"\n\n No spam to my email, please!");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
    }

    public static void sendPlain(String[] args) {
		String host = "smtp.gmail.com";
		int port = 587;
		String username = "claus437@gmail.com";
		String password = "minitrans";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");

		Session session = Session.getInstance(props);

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("claus437@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse("claus437@gmail.com"));
			message.setSubject("Testing Subject");
			message.setText("Dear Mail Crawler," +
					"\n\n No spam to my email, please!");

			Transport transport = session.getTransport("smtp");
			transport.connect(host, port, username, password);

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
