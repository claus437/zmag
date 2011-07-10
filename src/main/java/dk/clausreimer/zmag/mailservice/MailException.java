package dk.clausreimer.zmag.mailservice;

/**
 * Created by IntelliJ IDEA.
 * User: claus
 * Date: 09-07-11
 * Time: 23:25
 * To change this template use File | Settings | File Templates.
 */
public class MailException extends RuntimeException {
    public MailException(String message) {
        super(message);
    }

    public MailException(String message, Throwable cause) {
        super(message, cause);
    }
}
