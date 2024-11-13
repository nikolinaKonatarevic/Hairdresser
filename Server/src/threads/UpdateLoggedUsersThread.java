/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threads;

import domain.User;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import loggedUsers.LoggedUsersSingleton;
import view.form.FormMain;

/**
 *
 * @author Nikolina
 */
public class UpdateLoggedUsersThread extends Thread{
    
    private final FormMain form; 

    public UpdateLoggedUsersThread(FormMain form) {
        this.form = form;
    }

    @Override
    public void run() {
        while (true){
           List<User> users = LoggedUsersSingleton.getInstance().getAllLoggedUsers();
           form.reloadLoggedUsers(users);
           
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                Logger.getLogger(UpdateLoggedUsersThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    
    
}
