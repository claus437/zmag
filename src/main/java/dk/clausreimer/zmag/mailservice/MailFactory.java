package dk.clausreimer.zmag.mailservice;


/**
 * Created by IntelliJ IDEA.
 * User: claus
 * Date: 09-07-11
 * Time: 22:39
 * To change this template use File | Settings | File Templates.
 */
public class MailFactory {
    private static final Class<SmtpMail> DEFAULT_MAIL = SmtpMail.class;


    public static Mail create() {
        String implementation;

        implementation = System.getProperty("dk.clausreimer.zmag.mailservice.MailImpl");

        if (implementation == null) {
            return createInstance(DEFAULT_MAIL);
        } else {
            try {
                return createInstance((Class<? extends Mail>) Class.forName(implementation));
            } catch (ClassNotFoundException x) {
                throw new MailFactoryException("mail implementation " + implementation + " not found", x);
            }
        }
    }

    private static Mail createInstance(Class<? extends Mail> clazz) {
        try {
            return clazz.newInstance();
        } catch (IllegalAccessException x) {
            throw new MailFactoryException("class " + clazz.getCanonicalName() + " has no public constructor", x);
        } catch (InstantiationException x) {
            throw new MailFactoryException("unable to instantiate class " + clazz.getCanonicalName(), x);
        }
    }
}
