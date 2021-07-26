package controllers;

import play.mvc.*;
import play.data.validation.*;



import java.util.*;

import models.*;
import play.Logger;
import play.mvc.results.Result;

@With(Secure.class)
public class Application extends Controller {

    public static void index() {
        Date now = new Date();
        String user = Security.connected();
        render(user);
        //render(now);
    }
    
    public static void list() {
        User user = User.find("byEmail", Secure.Security.connected()).<User>first();
        Logger.info(user.email);
        List<Contact> contacts = user.contacts;
        render(contacts);
    }
    
    public static void form(Long id) {
        if(id == null) {
            render();
        }
        Contact contact = Contact.findById(id);
        render(contact);
    }
    
    public static void save(@Valid Contact contact) {
        if(validation.hasErrors()) {
            if(request.isAjax()) error("Invalid value");
            render("@form", contact);
        }
        contact.user = User.find("byEmail", Secure.Security.connected()).<User>first();
        contact.save();
        list();
    }
    
    public static void settings(){
        User user = User.find("byEmail", Secure.Security.connected()).<User>first();
        System.out.println(user.email + " user");
        render(user);
    }
    
    public static void saveSettings(User user){
        User user1 = User.find("byEmail", Secure.Security.connected()).<User>first();        
        user1.hours = user.hours;
        user1.save();
        System.out.println(user.email + " user");
        System.out.println(user.hours + " user.hours");
        settings();
    }
    
    

    

}