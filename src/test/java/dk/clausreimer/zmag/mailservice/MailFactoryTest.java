package dk.clausreimer.zmag.mailservice;

import org.junit.Assert;
import org.junit.Test;

public class MailFactoryTest {

    @Test
    public void testFactoryForDefaultMail() {
        Mail mail;

        mail = MailFactory.create();
        Assert.assertEquals(SmtpMail.class, mail.getClass());
    }

    @Test
    public void testFactoryForCustomMail() {
        Mail mail;

        try {
            System.setProperty("dk.clausreimer.zmag.mailservice.MailImpl", MailMock.class.getCanonicalName());

            mail = MailFactory.create();
            Assert.assertEquals(MailMock.class, mail.getClass());
        } finally {
            System.getProperties().remove("dk.clausreimer.zmag.mailservice.MailImpl");
        }
    }

    @Test (expected = MailFactoryException.class)
    public void testErrorWhenNoClass() {
        try {
            System.setProperty("dk.clausreimer.zmag.mailservice.MailImpl", "NoClass");
            MailFactory.create();
        } finally {
            System.getProperties().remove("dk.clausreimer.zmag.mailservice.MailImpl");
        }
    }

    @Test (expected = MailFactoryException.class)
    public void testErrorWhenNoPublicConstructor() {
        try {
            System.setProperty("dk.clausreimer.zmag.mailservice.MailImpl", MailPrivateConstructor.class.getCanonicalName());
            MailFactory.create();
        } finally {
            System.getProperties().remove("dk.clausreimer.zmag.mailservice.MailImpl");
        }
    }

    @Test (expected = MailFactoryException.class)
    public void testErrorWhenInstantiation() {
        try {
            System.setProperty("dk.clausreimer.zmag.mailservice.MailImpl", MailInitiationError.class.getCanonicalName());
            MailFactory.create();
        } finally {
            System.getProperties().remove("dk.clausreimer.zmag.mailservice.MailImpl");
        }
    }

}

class MailPrivateConstructor extends MailMock {
    private MailPrivateConstructor() {
    }
}

interface MailInitiationError extends Mail {
}

