package dk.clausreimer.zmag.mailservice;

import org.junit.Assert;
import org.junit.Test;

import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: claus
 * Date: 10-07-11
 * Time: 14:53
 * To change this template use File | Settings | File Templates.
 */
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
