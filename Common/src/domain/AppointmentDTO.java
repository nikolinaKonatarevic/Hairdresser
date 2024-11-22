/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Nikolina
 */
public class AppointmentDTO implements Serializable{
    private Appointment appointment;
    private List<AppointmentItem> saveList;
    private List<AppointmentItem> deleteList;

    public AppointmentDTO() {
    }

    public AppointmentDTO(Appointment appointment, List<AppointmentItem> saveList, List<AppointmentItem> deleteList) {
        this.appointment = appointment;
        this.saveList = saveList;
        this.deleteList = deleteList;
    }

    public List<AppointmentItem> getDeleteList() {
        return deleteList;
    }

    public void setDeleteList(List<AppointmentItem> deleteList) {
        this.deleteList = deleteList;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public List<AppointmentItem> getSaveList() {
        return saveList;
    }

    public void setSaveList(List<AppointmentItem> saveList) {
        this.saveList = saveList;
    }

    @Override
    public String toString() {
        return "";
    }
    
    
    
}
