package dk.clausreimer.zmag.mailservice;

/**
 * Created by IntelliJ IDEA.
 * User: claus
 * Date: 09-07-11
 * Time: 21:27
 * To change this template use File | Settings | File Templates.
 */
public interface Mail {
    public void setTo(String to);
    public void setFrom(String from);
    public void setSubject(String subject);
    public void setMessage(String message);
    public void send();
}
