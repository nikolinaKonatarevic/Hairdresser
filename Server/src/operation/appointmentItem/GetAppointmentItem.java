/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.appointmentItem;

import domain.AppointmentItem;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author Nikolina
 */
public class GetAppointmentItem extends AbstractGenericOperation{
    private List<AppointmentItem> items;
    @Override
    protected void preconditions(Object param) throws Exception {

    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        items = repository.get((AppointmentItem)param);
    }

    public List<AppointmentItem> getItems() {
        return items;
    }
    
    
}
