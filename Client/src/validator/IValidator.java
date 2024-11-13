/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package validator;

/**
 *
 * @author Nikolina
 */
public interface IValidator {

    public void validateLogin(String username, String password)throws ValidatorException;

    public void validateCreateNewUser(String firstname, String lastname, String email, String pass, String role) throws ValidatorException;
    
}
