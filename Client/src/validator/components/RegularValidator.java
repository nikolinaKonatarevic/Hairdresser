/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validator.components;

import controller.Communication;
import domain.Role;
import domain.User;
import java.util.List;
import javax.swing.JOptionPane;
import validator.IValidator;
import validator.ValidatorException;

/**
 *
 * @author Nikolina
 */
public class RegularValidator implements IValidator {

    @Override
    public void validateLogin(String username, String password) throws ValidatorException {
        if (username == null || username.isEmpty()) {
            throw new ValidatorException("Username field is empty");
        }

        if (password == null || password.isEmpty()) {
            throw new ValidatorException("Password field is empty");
        }
    }

    @Override
    public void validateCreateNewUser(String firstname, String lastname, String email, String pass, Role role) throws ValidatorException {
        if (firstname == null || firstname.isEmpty()) {
            //JOptionPane.showMessageDialog();
            throw new ValidatorException("Firstname field cant be empty");
        }
        if (!firstname.matches("[A-Za-z]+")) {
            throw new ValidatorException("Firstname cannot contain numbers or special characters");
        }
        if (firstname.length() > 30) {
            throw new ValidatorException("Firstname cannot be longer than 30 characters");
        }
        if (firstname.length() == 1) {
            throw new ValidatorException("Firstname has to contain at least 2 character!");
        }

        if (lastname == null || lastname.isEmpty()) {
            throw new ValidatorException("Lastname field cant be empty");
        }
        if (!lastname.matches("[A-Za-z]+")) {
            throw new ValidatorException("Lastname cannot contain numbers or special characters");
        }
        if (lastname.length() > 30) {
            throw new ValidatorException("Lastname cannot be longer than 30 characters");
        }
        if (lastname.length() == 1) {
            throw new ValidatorException("Lastname has to contain at least 1 character!");
        }

        if (email == null || email.isEmpty()) {
            throw new ValidatorException("Email field cant be empty");
        }
        if (!email.contains("@") || !email.contains(".")) {
            throw new ValidatorException("Not the right form of email");
        }
        if (duplicate(email)) {
            throw new ValidatorException("User with this email is already registered!");
        }

        if (pass == null || pass.isEmpty()) {
            throw new ValidatorException("Password field cant be empty");
        }
        if (pass.length() == 3) {
            throw new ValidatorException("Password has to contain at least 3 character!");
        }
        
        if(role==null){
             throw new ValidatorException("Role is not selected!");
        }

    }

    private boolean duplicate(String email) {
        try {
            List<User> users = Communication.getInstance().getAllUsers();

            for (User u : users) {
                if (u.getEmail().equals(email)){
                System.out.println("Users are" + u + " email is " + email);
                return true;
                }
            }

            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void validateUpdateUser(Long id, String firstname, String lastname, String email, String pass, Role role) throws ValidatorException {
        if (firstname == null || firstname.isEmpty()) {
            //JOptionPane.showMessageDialog();
            throw new ValidatorException("Firstname field cant be empty");
        }
        if (!firstname.matches("[A-Za-z]+")) {
            throw new ValidatorException("Firstname cannot contain numbers or special characters");
        }
        if (firstname.length() > 30) {
            throw new ValidatorException("Firstname cannot be longer than 30 characters");
        }
        if (firstname.length() == 1) {
            throw new ValidatorException("Firstname has to contain at least 2 character!");
        }
        if (lastname == null || lastname.isEmpty()) {
            throw new ValidatorException("Lastname field cant be empty");
        }
        if (!lastname.matches("[A-Za-z]+")) {
            throw new ValidatorException("Lastname cannot contain numbers or special characters");
        }
        if (lastname.length() > 30) {
            throw new ValidatorException("Lastname cannot be longer than 30 characters");
        }
        if (lastname.length() == 1) {
            throw new ValidatorException("Lastname has to contain at least 1 character!");
        }

        if (email == null || email.isEmpty()) {
            throw new ValidatorException("Email field cant be empty");
        }
        if (!email.contains("@") || !email.contains(".")) {
            throw new ValidatorException("Not the right form of email");
        }
        
        if (pass == null || pass.isEmpty()) {
            throw new ValidatorException("Password field cant be empty");
        }
        if (pass.length() == 3) {
            throw new ValidatorException("Password has to contain at least 3 character!");
        }
        
        if(role==null){
             throw new ValidatorException("Role is not selected!");
        }
    }

}
