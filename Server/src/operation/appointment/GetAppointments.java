/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.appointment;

import domain.Appointment;
import domain.AppointmentItem;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author Nikolina
 */
public class GetAppointments extends AbstractGenericOperation {

    private List<Appointment> appointments;

    @Override
    protected void preconditions(Object param) throws Exception {

    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        appointments = repository.get((Appointment) param);
        List<AppointmentItem> items = repository.get(new AppointmentItem());
        for (int i = 0; i < appointments.size(); i++) {
            for (AppointmentItem item : items) {
                if (item.getAppointment().getId() == appointments.get(i).getId()) {
                    item.setAppointment(appointments.get(i));
                    appointments.get(i).getItems().add(item);

                }
            }
        }
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

}
