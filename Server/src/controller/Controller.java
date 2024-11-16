/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domain.User;
import operation.AbstractGenericOperation;
import operation.login.Login;
import operation.users.CreateUser;
import operation.users.GetUsers;
import operation.users.UpdateUser;

/**
 *
 * @author Nikolina
 */
public class Controller {
    private static Controller instance; 
    private AbstractGenericOperation operation; 

    private Controller() {
    }
    
    public static Controller getInstance(){
        if(instance==null)
            instance = new Controller();
        return instance;
    }

    public Object login(User user) throws Exception {
        operation = new Login();
        operation.execute(user);
        return ((Login) operation).getLogin();
    }
    
    public Object get(Object object) throws Exception {
        if (object instanceof User){
           return getUsers(object);
        }
        return null;
    }

 
    public Object create(Object object) throws Exception {
        if (object instanceof User)
            return createNewUser(object);
        
        return null;
    }

     public Object update(Object object) throws Exception {
        if (object instanceof User) {
            return updateUser(object);
        }
        
        return null;
    }

     
     
       private Object getUsers(Object object) throws Exception {
        operation = new GetUsers();
        operation.execute(object);
        return ((GetUsers)operation).getUsers();
        
    }

    private Object createNewUser(Object object) throws Exception {
        operation = new CreateUser ((User) object);
        operation.execute(object);
        return ((CreateUser)operation).getUser();
        
    }

    private Object updateUser(Object object) throws Exception {
        operation = new UpdateUser();
        operation.execute(object);
        return((UpdateUser)operation).getUser();
    }

   
    
}
