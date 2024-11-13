/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.users;

import domain.User;
import operation.AbstractGenericOperation;
import validator.IValidator;
import validator.components.RegularValidator;

/**
 *
 * @author Nikolina
 */
public class CreateUser extends AbstractGenericOperation{
    
    private User user;
    IValidator validator;

    public CreateUser(User user) {
        this.user = user;
        validator = new RegularValidator();
    }

    
    @Override
    protected void preconditions(Object param) throws Exception {
        validator = new RegularValidator();
        validator.validateUser((User) param);
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        user = (User) repository.create(user);
    }

    public User getUser() {
        return user;
    }

   
    
}
