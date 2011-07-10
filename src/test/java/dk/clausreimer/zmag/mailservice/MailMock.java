package dk.clausreimer.zmag.mailservice;


public class MailMock implements Mail {
    private static String receiver;
    private static String origin;
    private static String mailSubject;
    private static String text;
    private static int sent;
    private static RuntimeException sendException;

    @Override
    public void setTo(String to) {
        receiver = to;
    }

    @Override
    public void setFrom(String from) {
        origin = from;
    }

    @Override
    public void setSubject(String subject) {
        mailSubject = subject;
    }

    @Override
    public void setMessage(String message) {
        text = message;
    }

    @Override
    public void send() {
        if (sendException != null) {
            throw sendException;
        }
        sent ++;
    }

    public static String getTo() {
        return receiver;
    }

    public static String getFrom() {
        return origin;
    }

    public static String getSubject() {
        return mailSubject;
    }

    public static String getMessage() {
        return text;
    }

    public static int getMailsSent() {
        return sent;
    }

    public static void raiseSendException(RuntimeException exception) {
        sendException = exception;
    }

    public static void clear() {
        receiver = null;
        origin = null;
        mailSubject = null ;
        text = null;
        sent = 0;
        sendException = null;
    }
}
