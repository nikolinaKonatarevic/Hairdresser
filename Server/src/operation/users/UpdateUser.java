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
public class UpdateUser extends AbstractGenericOperation{

    private User user;
    private IValidator validator; 

    public UpdateUser() {
    }
    
    
    @Override
    protected void preconditions(Object param) throws Exception {
        validator = new RegularValidator();
        validator.validateUser((User) param);
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        user = (User) repository.update((User) param);
    }

    public User getUser() {
        return user;
    }
 
    
}
