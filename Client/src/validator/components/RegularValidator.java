/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validator.components;

import controller.Communication;
import domain.Appointment;
import domain.Hairdresser;
import domain.HairdresserStatusEnum;
import domain.RoleEnum;
import domain.ServiceType;
import domain.User;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    public void validateCreateNewUser(String firstname, String lastname, String email, String pass, RoleEnum role) throws ValidatorException {
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

        if (role == null) {
            throw new ValidatorException("Role is not selected!");
        }

    }

    @Override
    public void validateUpdateUser(Long id, String firstname, String lastname, String email, String pass, RoleEnum role) throws ValidatorException {
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
        if (pass.length() < 3) {
            throw new ValidatorException("Password has to contain at least 3 character!");
        }

        if (role == null) {
            throw new ValidatorException("Role is not selected!");
        }
    }

    @Override
    public void validateDeleteUser(long id) throws ValidatorException {
        List<Appointment> appointments = null;
        try {
            appointments = Communication.getInstance().getAllAppointments();
        } catch (Exception ex) {
            Logger.getLogger(RegularValidator.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (appointments == null) {
            return;
        }
        for (Appointment a : appointments) {
            if (a.getUser().getId() == id) {
                throw new ValidatorException("System cannot delete this user since has on or more appointments");
            }
        }

    }

    @Override
    public void validateDate(LocalDate date) throws ValidatorException {
        if (date.isBefore(LocalDate.now())) {
            throw new ValidatorException("Chosen date is in the past");
        }
    }

    @Override
    public void validateAppointment(int indexH, int indexSH, Date date, List<ServiceType> items) throws ValidatorException {
        if (indexH == -1) {
            throw new ValidatorException("Please select the hairdresser");
        }
        if (items == null || items.isEmpty()) {
            throw new ValidatorException("Please select at least one service");
        }
        if (date == null) {
            throw new ValidatorException("Please select the date");

        }
        if (indexSH == -1) {
            throw new ValidatorException("Please select the startHour");
        }

    }

    @Override
    public void validateAppointment(Object selectedItem, Object selectedItem0, Date date) throws ValidatorException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void validateNewHairdresser(String firstname, String lastname, String JMBG, Date localDate, HairdresserStatusEnum status) throws ValidatorException {
        if (firstname == null || firstname.isEmpty()) {
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
        if (JMBG == null || JMBG.isEmpty()) {
            throw new ValidatorException("JMBG field cant be empty");
        }
        if (!JMBG.matches("[0-9]+")) {
            throw new ValidatorException("JMBG cannot contain letters or special characters");
        }
        if (JMBG.length() != 13) {
            throw new ValidatorException("JMBG has to have 13 characters");
        }
        if (localDate == null) {
            throw new ValidatorException("Date field cannot be empty");
        }
        if (status == null) {
            throw new ValidatorException("Status field cannot be empty");
        }
        if (duplicateJMBG(JMBG)) {
            throw new ValidatorException("Status field cannot be empty");

        }
    }

    private boolean duplicateJMBG(String JMBG) {
        try {
            List<Hairdresser> hairdressers = Communication.getInstance().getAllHairdressers();

            for (Hairdresser hairdresser : hairdressers) {
                if (hairdresser.getJMBG().equals(JMBG)) {
                    return true;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(RegularValidator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private boolean duplicate(String email) {
        try {
            List<User> users = Communication.getInstance().getAllUsers();

            for (User u : users) {
                if (u.getEmail().equals(email)) {
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
    public void validateDeleteHairdresser(long id) throws ValidatorException {
        List<Appointment> appointments = null;
        try {
            appointments = Communication.getInstance().getAllAppointments();

        } catch (Exception ex) {
            Logger.getLogger(RegularValidator.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (appointments == null) {
            return;
        }
        for (Appointment a : appointments) {
            if (a.getHairdresser().getId() == id) {
                throw new ValidatorException("System cannot delete this hairdresser since he has one or more appointments");
            }
        }

    }

    @Override
    public void validateUpdateHairdresser(Long id, String firstname, String lastname, String JMBG, HairdresserStatusEnum status) throws ValidatorException {
        if (firstname == null || firstname.isEmpty()) {
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
        if (status == null) {
            throw new ValidatorException("Status field cannot be empty");
        }
    }

}
