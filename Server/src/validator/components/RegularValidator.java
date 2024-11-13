/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validator.components;

import domain.User;
import validator.IValidator;
import validator.ValidatorException;

/**
 *
 * @author Nikolina
 */
public class RegularValidator implements IValidator {

    @Override
    public void validateLogin(User user) throws ValidatorException {
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new ValidatorException("Email field is  required");
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new ValidatorException("Password field is required");
        }
    }

    @Override
    public void validateUser(User user) throws ValidatorException {
        if (user.getEmail() == null) {
            throw new ValidatorException("Email field is required");
        }
        if (user.getPassword() == null) {
            throw new ValidatorException("Password field is required");
        }
        if (user.getFirstname() == null) {
            throw new ValidatorException("Firstname field is required");
        }
        if (user.getLastname() == null) {
            throw new ValidatorException("Lastname field is required");
        }
        if (user.getRole() == null) {
            throw new ValidatorException("Role field is required");
        }
    }

}
