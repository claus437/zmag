package dk.clausreimer.zmag.mailservice;


public interface Mail {
    public void setTo(String to);
    public void setFrom(String from);
    public void setSubject(String subject);
    public void setMessage(String message);
    public void send();
}
