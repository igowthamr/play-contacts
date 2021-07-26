/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jobs;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import models.Contact;
import models.User;
import play.Logger;
import play.db.jpa.JPA;
import play.jobs.*;

/**
 *
 * @author dev
 */
@Every("10s")
public class EmailJobs extends Job {
    
    public void doJob() {
        TypedQuery<Contact> query = JPA.em().createNamedQuery("Contact.findNexts", Contact.class);
        Calendar calendar = Calendar.getInstance();
        Logger.info("Calendar1  %s", calendar.getTime());
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        query.setParameter("month",calendar.get(Calendar.MONTH));
        query.setParameter("day",calendar.get(Calendar.DAY_OF_MONTH));
        //query.setParameter("hours",24-Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
        Logger.info("hours %s", 24-Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
        Logger.info("Calendar 2 %s", calendar.getTime());
        //Logger.info("Query %s", query.getParameters());
        List<Contact> newUsers = query.getResultList();
        List<User> users = new ArrayList<User>();
        
        for(User user : users) {
           
           Mails.welcome(user);
        }
    }
}


