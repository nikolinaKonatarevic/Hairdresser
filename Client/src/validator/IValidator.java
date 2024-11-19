/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package validator;

import domain.RoleEnum;
import domain.ServiceType;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Nikolina
 */
public interface IValidator {

    public void validateLogin(String username, String password)throws ValidatorException;

    public void validateCreateNewUser(String firstname, String lastname, String email, String pass, RoleEnum role) throws ValidatorException;

    public void validateUpdateUser(Long id, String firstname, String lastname, String email, String password, RoleEnum role) throws ValidatorException;

    public void validateDeleteUser(long id) throws ValidatorException;


    public void validateDate(LocalDate date) throws ValidatorException;

    public void validateAppointment(int indexH, int indexSH, Date date,  List<ServiceType> items) throws ValidatorException;

    public void validateDate();

    public void validateAppointment(Object selectedItem, Object selectedItem0, Date date);
    
}
