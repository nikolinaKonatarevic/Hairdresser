/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.appointment;

import domain.Appointment;
import domain.AppointmentDTO;
import domain.AppointmentItem;
import java.util.List;
import operation.AbstractGenericOperation;
import validator.IValidator;
import validator.components.RegularValidator;

/**
 *
 * @author Nikolina
 */
public class UpdateAppointment extends AbstractGenericOperation{

    private Appointment appointment;
    IValidator validator;

    public UpdateAppointment() {
    }
    
    
    @Override
    protected void preconditions(Object param) throws Exception {
        validator = new RegularValidator();
        validator.validateAppointment(((AppointmentDTO)param).getAppointment());
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        if(param==null) 
            throw new Exception("DTO is null");
        appointment = ((AppointmentDTO)param).getAppointment();
        List<AppointmentItem> saveList = ((AppointmentDTO)param).getSaveList();
        List<AppointmentItem> deleteList = ((AppointmentDTO)param).getDeleteList();
        
        
        for(int i =0 ; i<saveList.size(); i++){
            
            repository.create(saveList.get(i));
        }
        
        for(int i =0 ; i<deleteList.size(); i++){
            
            repository.delete(deleteList.get(i));
        }
        
        appointment = (Appointment) repository.update(((AppointmentDTO)param).getAppointment());
    
    }

    public Appointment getAppointment() {
        return appointment;
    }
    
    
}
