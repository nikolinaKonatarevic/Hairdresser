/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.appointment;

import domain.Appointment;
import domain.AppointmentItem;
import operation.AbstractGenericOperation;
import validator.IValidator;
import validator.components.RegularValidator;

/**
 *
 * @author Nikolina
 */
public class CreateAppointment  extends AbstractGenericOperation{

    Appointment appointment;
    private IValidator validator;

    public CreateAppointment(Appointment appointment) {
        this.appointment = appointment;
        
    }

    
    @Override
    protected void preconditions(Object param) throws Exception {
        validator = new RegularValidator();
        validator.validateAppointment((Appointment)param);
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        appointment = (Appointment) repository.create(appointment);
        for(int i = 0; i<appointment.getItems().size(); i++){
            appointment.getItems().set(i, (AppointmentItem) repository.create(appointment.getItems().get(i)));
        }
    }
    
    public Appointment getAppointment(){
        return appointment;
    }
}
