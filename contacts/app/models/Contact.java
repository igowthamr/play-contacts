package models;

import play.*;
import play.db.jpa.*;
import play.data.validation.*;

import javax.persistence.*;
import java.util.*;

@Entity
@NamedQuery(name="Contact.findNexts", query="SELECT c FROM Contact c where c.month=:month and c.day_of_month=:day") 
public class Contact extends Model {
    
    @Required
    public String firstname;
    
    @Required
    public String name;
    
    @Required
    public Date birthdate;
    
    
    public void setBirthdate(Date date){
        birthdate= date;
        day_of_month = date.getDate();
        month = date.getMonth();
        year = date.getYear();
    }
    
    public int month;
    
    public int day_of_month;
    
    public int year;
    
    @Required
    @Email
    public String email;
    
    @ManyToOne
    public User user;
    
    
            
}

