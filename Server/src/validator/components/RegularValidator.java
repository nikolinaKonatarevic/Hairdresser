/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validator.components;

import domain.Appointment;
import domain.AppointmentItem;
import domain.Hairdresser;
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

    @Override
    public void validateDeleteUser(long id) throws ValidatorException {
        if (id == -1) {
            throw new ValidatorException("ID is not known");
        }
    }

    @Override
    public void validateAppointment(Appointment appointment) throws ValidatorException {
        if (appointment.getDate() == null) {
            throw new ValidatorException("Date field is required");
        }

        if (appointment.getHairdresser() == null) {
            throw new ValidatorException("Hairdresser field is required");
        }

        for (AppointmentItem ai : appointment.getItems()) {
            validateAppointmentItem(ai);
        }

    }

    @Override
    public void validateAppointmentItem(AppointmentItem appointmentItem) throws ValidatorException {
        if (appointmentItem.getServiceType() == null) {
            throw new ValidatorException("Service type field is required");
        }
    }

    @Override
    public void validateHairdresser(Hairdresser hairdresser) throws ValidatorException {
        if (hairdresser.getJMBG() == null) {
            throw new ValidatorException("Hairdresser has to have JMBG field");
        }
        if (hairdresser.getFirstname() == null) {
            throw new ValidatorException("Hairdresser has to have firstname field");
        }
        if (hairdresser.getLastname() == null) {
            throw new ValidatorException("Hairdresser has to have lastname field");
        }
        if (hairdresser.getDateOfEmpleyment() == null) {
            throw new ValidatorException("Hairdresser has to have date of emplyment field");
        }
        if (hairdresser.getStatus() == null) {
            throw new ValidatorException("Hairdresser has to have status field");
        }
    }

}
