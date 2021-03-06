package dk.clausreimer.zmag.mailservice;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

import org.junit.rules.ExpectedException;
import java.io.IOException;


public class SmtpMailTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private SmtpMail mail = new SmtpMail();


    @Test
    public void testExceptionThrownWhenMalformedOrigin() {
        thrown.expect(MailException.class);
        thrown.expectMessage("Invalid from address claus reimer");

        mail.setFrom("claus reimer");
    }

    @Test
    public void testExceptionThrownWhenMalformedReceiver() {
        thrown.expect(MailException.class);
        thrown.expectMessage("Invalid to address claus reimer");

        mail.setTo("claus reimer");
    }

    @Test
    public void testExceptionThrownWhenTransportError() {
        thrown.expect(MailException.class);
        thrown.expectMessage("failed sending mail");

        System.setProperty("mail.smtp.host", "unknownhost");
        mail = new SmtpMail();

        mail.setTo("claus437@gmail.com");
        mail.setFrom("claus438@gmail.com");
        mail.setSubject("Test");
        mail.setMessage("Hello World");
        mail.send();
    }

    @Test
    @Ignore // requires a valid smtp config, see src/test/resources/dk/clausreimer/zmag/mailservice/gmail.properties
    public void testSendMailUsingRealSmtpServer() throws Exception {
        mail.setTo("claus437@gmail.com");
        mail.setFrom("claus438@gmail.com");
        mail.setSubject("Test");
        mail.setMessage("Hello World");
        mail.send();
    }

    @Before
    public void setup() throws IOException {
        System.getProperties().load(getClass().getResourceAsStream("gmail.properties"));
    }
}
