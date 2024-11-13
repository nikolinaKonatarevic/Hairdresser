/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loggedUsers;

import domain.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nikolina
 */
public class LoggedUsersSingleton {
    private static LoggedUsersSingleton instance; 
    private List<User> loggedUsers;

    private LoggedUsersSingleton() {
        loggedUsers= new ArrayList<>();
    }
    
    public static LoggedUsersSingleton getInstance(){
        if (instance == null )
            instance = new LoggedUsersSingleton();
        return instance;
    }
    
    public synchronized void addLoggedUser (User user){
        loggedUsers.add(user);
    }
    
    public synchronized void remodeLoggedUser (User user){
        loggedUsers.remove(user);
    }
    
    public synchronized List<User> getAllLoggedUsers(){
        return new ArrayList<>(loggedUsers);
    }
}
