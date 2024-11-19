/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.components;

import domain.Appointment;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Nikolina
 */
public class TableModelAppointments extends AbstractTableModel{

    private List<Appointment> appointmnets;
    private Class [] columnClass = new Class[]{LocalDate.class,Double.class,Double.class,LocalDateTime.class, BigDecimal.class, String.class, String.class};

    public TableModelAppointments() {
    }

    
    @Override
    public int getRowCount() {
        if(appointmnets==null){
            return 0;
        }
        else 
            return appointmnets.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(appointmnets!=null) {
        Appointment a = appointmnets.get(rowIndex);
        switch(columnIndex){
            case 0: 
                return a.getDate();
            case 1:
                return a.getStart_time();
            case 2: 
                return a.getEnd_time();
            case 3: 
                return a.getCreatedOn();
            case 4: 
                return a.getTotalPrice();
            case 5: 
                return a.getHairdresser().getFirstname()+ " " + a.getHairdresser().getLastname();
            case 6: 
                return a.getStatus();
             default:
                return "n/a";
        }}
        return null;
    }

    @Override
    public String getColumnName(int column) {
        switch (column){
            case 0:
                return "Date of appointment";
            case 1: 
                return "Start time";
            case 2:
                return "End time";
            case 3: 
                return "Created on";
            case 4:
                return "Total price";
            case 5: 
                return "Hairdresser";
            case 6: 
                return "Appointment status";
            default: return "na";
        }
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(columnIndex>columnClass.length)
            return Object.class;
        else 
            return columnClass[columnIndex];
    }

    public List<Appointment> getAppointmnets() {
        return appointmnets;
    }
    
    public void removeAll(){
        appointmnets.removeAll(appointmnets);
        fireTableDataChanged();
    }
    
    public void addAppointment (Appointment appointment){
        appointmnets.add(appointment);
        fireTableDataChanged();
    }

    public void setAppointmnets(List<Appointment> appointmnets) {
        this.appointmnets = appointmnets;
        fireTableDataChanged();
    }
    
    
}
