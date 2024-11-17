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
public class DeleteUser extends AbstractGenericOperation{
    
    private boolean flag = false;
    private IValidator validator;

    @Override
    protected void preconditions(Object param) throws Exception {
        validator = new RegularValidator();
        validator.validateDeleteUser(((User)param).getId());
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        flag = repository.delete((User)param);
    }

    public boolean isFlag() {
        return flag;
    }
    
    
}
