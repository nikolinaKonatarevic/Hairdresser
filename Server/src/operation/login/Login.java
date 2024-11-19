/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.login;

import domain.User;
import java.util.List;
import operation.AbstractGenericOperation;
import validator.IValidator;
import validator.components.RegularValidator;

/**
 *
 * @author Nikolina
 */
public class Login extends AbstractGenericOperation{
    private User loggedUser;
    private IValidator validator;

    @Override
    protected void preconditions(Object param) throws Exception {
        validator = new RegularValidator();
        validator.validateLogin((User)param);
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        //System.out.println(param);
        User user = (User)param;
        List<User> users = repository.get((User)param);
        //System.out.println(user);
        for(User u: users){
            if(u.getEmail().equals(user.getEmail())
                && u.getPassword().equals(user.getPassword())){
                loggedUser = u;
                return;
            }
        }

    }
    
    public Object getLogin(){
        return loggedUser;
    }
}
