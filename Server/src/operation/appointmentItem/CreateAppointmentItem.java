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
public class CreateAppointmentItem extends AbstractGenericOperation{

    private AppointmentItem item; 
    IValidator validator;

    public CreateAppointmentItem(AppointmentItem item) {
        this.item = item;
    }

    
    
    @Override
    protected void preconditions(Object param) throws Exception {
        validator = new RegularValidator();
        validator.validateAppointmentItem((AppointmentItem)param);
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        item = (AppointmentItem) repository.create(item);
    }

    public AppointmentItem getItem() {
        return item;
    }
    
    
    
}
