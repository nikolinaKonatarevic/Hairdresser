/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.appointmentItem;

import domain.AppointmentItem;
import operation.AbstractGenericOperation;
import validator.IValidator;
import validator.components.RegularValidator;

/**
 *
 * @author Nikolina
 */
public class UpdateAppointmentItem extends AbstractGenericOperation{
    
    private AppointmentItem item; 
    IValidator validator; 

    @Override
    protected void preconditions(Object param) throws Exception {
        validator = new RegularValidator();
        validator.validateAppointmentItem((AppointmentItem) param);
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        
    }
}
