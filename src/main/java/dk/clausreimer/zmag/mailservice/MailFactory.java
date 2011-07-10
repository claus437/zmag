package dk.clausreimer.zmag.mailservice;


public class MailFactory {
    private static final Class<SmtpMail> DEFAULT_MAIL = SmtpMail.class;

    public static Mail create() {
        String implementationClassName;
        Class implementationClass;

        implementationClassName = System.getProperty("dk.clausreimer.zmag.mailservice.MailImpl");

        if (implementationClassName == null) {
            implementationClass = DEFAULT_MAIL;
        } else {
            implementationClass = findClass(implementationClassName);
        }

        return createInstance(implementationClass);
    }

    private static Class findClass(String className) {
        Class clazz;

        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException x) {
            throw new MailFactoryException("mail implementation " + className + " not found", x);
        }

        return clazz;
    }

    private static Mail createInstance(Class clazz) {
        try {
            return (Mail) clazz.newInstance();
        } catch (IllegalAccessException x) {
            throw new MailFactoryException("class " + clazz.getCanonicalName() + " has no public constructor", x);
        } catch (InstantiationException x) {
            throw new MailFactoryException("unable to instantiate class " + clazz.getCanonicalName(), x);
        }
    }
}
