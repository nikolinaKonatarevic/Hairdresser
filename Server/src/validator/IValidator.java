/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package validator;

import domain.User;

/**
 *
 * @author Nikolina
 */
public interface IValidator {
    
    public void validateLogin(User user) throws ValidatorException;

    public void validateUser(User user) throws ValidatorException;

    public void validateDeleteUser(long id) throws ValidatorException;

}
