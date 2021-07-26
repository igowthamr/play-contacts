package jobs;
 
import org.apache.commons.mail.*; 
import play.*;
import play.mvc.*;
import java.util.*;
import models.Contact;
import models.User;
 
public class Mails extends Mailer {
 
   public static void welcome(User user,List<Contact> contacts) {
      setSubject("Welcome %s", user.name);
      addRecipient(user.email);
      setFrom("Me <me@me.com>");
      //EmailAttachment attachment = new EmailAttachment();
      //attachment.setDescription("A pdf document");
      //attachment.setPath(Play.getFile("rules.pdf").getPath());
      //addAttachment(attachment);
      send(user,contacts);
   }
 
   public static void lostPassword(User user) {
      String newpassword = user.password;
      setFrom("Robot <robot@thecompany.com>");
      setSubject("Your password has been reset");
      addRecipient(user.email);
      send(user, newpassword);
   }
 
}