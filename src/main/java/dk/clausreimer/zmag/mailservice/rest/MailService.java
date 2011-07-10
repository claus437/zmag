package dk.clausreimer.zmag.mailservice.rest;

import dk.clausreimer.zmag.mailservice.Mail;
import dk.clausreimer.zmag.mailservice.MailFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;


@Path("/sendMail")
public class MailService {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String send(
            @QueryParam("from") String from,
            @QueryParam("to") String to,
            @QueryParam("subject") String subject,
            @QueryParam("message") String message) {

        Mail mail;
        String response;

        response = "OK";

        try {
            mail = MailFactory.create();
            mail.setFrom(from);
            mail.setTo(to);
            mail.setSubject(subject);
            mail.setMessage(message);

            mail.send();
        } catch (Throwable x) {
            response = x.getMessage();
        }

        return response;
    }
}
