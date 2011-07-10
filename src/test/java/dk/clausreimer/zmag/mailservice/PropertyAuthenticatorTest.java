package dk.clausreimer.zmag.mailservice;

import org.junit.Test;

import org.junit.Assert;
import java.util.Properties;


public class PropertyAuthenticatorTest {

    @Test
    public void testAuthentication() {
        PropertyAuthenticator authenticator;
        Properties properties;

        properties = new Properties();
        properties.setProperty("mail.smtp.user", "user");
        properties.setProperty("mail.smtp.password", "password");

        authenticator = new PropertyAuthenticator(properties);
        Assert.assertEquals("user", authenticator.getPasswordAuthentication().getUserName());
        Assert.assertEquals("password", authenticator.getPasswordAuthentication().getPassword());
    }
}
