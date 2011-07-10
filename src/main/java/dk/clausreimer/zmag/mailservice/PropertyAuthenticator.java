package dk.clausreimer.zmag.mailservice;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import java.util.Properties;

public class PropertyAuthenticator extends Authenticator {
    private Properties properties;

    public PropertyAuthenticator(Properties properties) {
        this.properties = properties;
    }

    @Override
    public PasswordAuthentication getPasswordAuthentication() {
        String user;
        String password;

        user = properties.getProperty("mail.smtp.user");
        password = properties.getProperty("mail.smtp.password");

        return new PasswordAuthentication(user, password);
    }
}
