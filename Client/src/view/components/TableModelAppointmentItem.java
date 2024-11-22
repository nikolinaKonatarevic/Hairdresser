/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.components;

import domain.ServiceType;
import java.math.BigDecimal;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Nikolina
 */
public class TableModelAppointmentItem extends AbstractTableModel{

    private ArrayList<ServiceType> services;
    private Class [] columnClass = new Class[]{String.class,String.class,Double.class,BigDecimal.class};

    public TableModelAppointmentItem() {
    }
    
    
    
    @Override
    public int getRowCount() {
        if(services==null){
            return 0;
        }
        else 
            return services.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(services!=null) {
        ServiceType s = services.get(rowIndex);
        switch(columnIndex){
            case 0: 
                return s.getName();
            case 1:
                return s.getDescription();
            case 2: 
                return s.getDuration();
            case 3: 
                return s.getPrice();
            default:
                return "n/a";
        }}
        return null;
    }

    @Override
    public String getColumnName(int column) {
        switch (column){
            case 0:
                return "Name";
            case 1: 
                return "Description";
            case 2:
                return "Duration in hours";
            case 3: 
                return "Price in EUR";
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
    
    public void addService(ServiceType s){
        if(services ==null)
            services = new ArrayList<>();
        if(s!=null)
            services.add(s);
        fireTableDataChanged();
    }

    public ArrayList<ServiceType> getServices() {
        return services;
    }

    public ServiceType removeService(int selectedRow) {
        ServiceType selected = services.get(selectedRow);
       services.remove(selectedRow);
       fireTableDataChanged();
       return selected;
    }
   
    public void removeAllServices(){
        services.removeAll(services);
        fireTableDataChanged();
    }

    public void setServices(ArrayList<ServiceType> services) {
        this.services = services;     
        fireTableDataChanged();
    }
    
    
    
}
