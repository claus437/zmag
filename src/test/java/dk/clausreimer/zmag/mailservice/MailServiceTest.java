package dk.clausreimer.zmag.mailservice;

import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;
import dk.clausreimer.zmag.mailservice.rest.MailService;
import dk.clausreimer.zmag.wordrank.IOUtil;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.URL;
import java.net.URLConnection;

public class MailServiceTest {
    MailService mailService = new MailService();

    @Test
    public void testMailService() {
        String response;

        response = mailService.send("origin", "receiver", "mail subject", "mail text");

        Assert.assertEquals("OK", response);
        Assert.assertEquals("origin", MailMock.getFrom());
        Assert.assertEquals("receiver", MailMock.getTo());
        Assert.assertEquals("mail subject", MailMock.getSubject());
        Assert.assertEquals("mail text", MailMock.getMessage());
        Assert.assertEquals(1, MailMock.getSent());
    }

    @Test
    public void testErrorSendingMail() {
        String response;

        MailMock.raiseSendException(new MailException("transport error"));

        response = mailService.send("origin", "receiver", "mail subject", "mail text");

        Assert.assertEquals("transport error", response);
        Assert.assertEquals(0, MailMock.getSent());
    }


    @Test
    public void testRunServer() throws Exception {
        HttpServer server;
        URL url;
        URLConnection connection;
        String response;

        url = new URL("http://localhost:9998/sendMail?from=origin&to=receiver&subject=subject&message=text");

        server = HttpServerFactory.create("http://localhost:9998/");
        server.start();

        try {
            connection = url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(false);

            response = IOUtil.read(connection.getInputStream());
        } finally {
            server.stop(0);
        }

        Assert.assertEquals("OK", response);
        Assert.assertEquals("origin", MailMock.getFrom());
        Assert.assertEquals("receiver", MailMock.getTo());
        Assert.assertEquals("subject", MailMock.getSubject());
        Assert.assertEquals("text", MailMock.getMessage());
        Assert.assertEquals(1, MailMock.getSent());
    }

    @Before
    public void setup() {
        System.setProperty("dk.clausreimer.zmag.mailservice.MailImpl", MailMock.class.getCanonicalName());
        MailMock.clear();
    }

    @After
    public void tearDown() {
        System.getProperties().remove("dk.clausreimer.zmag.mailservice.MailImpl");
    }
}
