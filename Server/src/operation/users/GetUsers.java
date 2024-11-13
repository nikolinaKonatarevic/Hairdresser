/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.users;

import domain.User;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author Nikolina
 */
public class GetUsers extends AbstractGenericOperation{
    
    private List<User> users;

    @Override
    protected void preconditions(Object param) throws Exception {
        //nema ogranicenja
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        users = repository.get((User)param);
    }
    
    public List<User> getUsers(){
        return users;
    }
    
}
