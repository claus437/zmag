package dk.clausreimer.zmag.mailservice;

import org.junit.Rule;
import org.junit.Test;

import org.junit.Assert;
import org.junit.rules.ExpectedException;


public class MailFactoryTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

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

    @Test
    public void testErrorWhenNoClass() {
        thrown.expect(MailFactoryException.class);
        thrown.expectMessage("mail implementation NoClass not found");

        try {
            System.setProperty("dk.clausreimer.zmag.mailservice.MailImpl", "NoClass");
            MailFactory.create();
        } finally {
            System.getProperties().remove("dk.clausreimer.zmag.mailservice.MailImpl");
        }
    }

    @Test
    public void testErrorWhenNoPublicConstructor() {
        thrown.expect(MailFactoryException.class);
        thrown.expectMessage("class dk.clausreimer.zmag.mailservice.MailPrivateConstructor has no public constructor");

        try {
            System.setProperty("dk.clausreimer.zmag.mailservice.MailImpl", MailPrivateConstructor.class.getCanonicalName());
            MailFactory.create();
        } finally {
            System.getProperties().remove("dk.clausreimer.zmag.mailservice.MailImpl");
        }
    }

    @Test
    public void testErrorWhenInstantiation() {
        thrown.expect(MailFactoryException.class);
        thrown.expectMessage("unable to instantiate class dk.clausreimer.zmag.mailservice.MailInitiationError");

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

