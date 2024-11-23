/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.components;

import controller.Communication;
import domain.Hairdresser;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Nikolina
 */
public class TableModelHairdresser extends AbstractTableModel{

    private ArrayList<Hairdresser> hairdressers;
    private Class[] columnClass = new Class [] {String.class, String.class, String.class,LocalDate.class,String.class};
    Hairdresser restriction;
    
    public TableModelHairdresser (){
        
        updateHairdresser();
    }
    
    @Override
    public int getRowCount() {
        if(hairdressers==null)
            return 0;
        else return hairdressers.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public String getColumnName(int column) {
        switch (column){
            case 0 : 
                return "Firstname";
            case 1:
                return "Lastname";
            case 2:
                return "JMBG";
            case 3: 
                return "Date of Employment";
            case 4: 
                return "Status";
            default: 
                return "n/a";
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(columnIndex>columnClass.length)
            return Object.class;
        return columnClass[columnIndex];
    }

    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Hairdresser h = hairdressers.get(rowIndex);
        switch(columnIndex){
            case 0: 
                return h.getFirstname();
            case 1: 
                return h.getLastname();
            case 2: 
                return h.getJMBG();
            case 3: 
                return h.getDateOfEmpleyment();
            case 4: 
                return h.getStatus();
            default: 
                return "n/a";
        }
    }

    public void updateHairdresser() {
        try {
            hairdressers = (ArrayList<Hairdresser>) Communication.getInstance().getAllHairdressers();
            
            if(restriction!=null){
                 Iterator<Hairdresser> iterator = hairdressers.iterator();
                // optimize
                while (iterator.hasNext()) {
                    Hairdresser hairdresser = iterator.next();

                    if (restriction.getFirstname() != null && !restriction.getFirstname().isEmpty()
                            && !hairdresser.getFirstname().toLowerCase().contains(restriction.getFirstname().toLowerCase())) {
                        iterator.remove();
                        continue;
                    }

                    if (restriction.getLastname() != null && !restriction.getLastname().isEmpty()
                            && !hairdresser.getLastname().toLowerCase().contains(restriction.getLastname().toLowerCase())) {
                        iterator.remove();
                        continue;
                    }

                    if (restriction.getJMBG()!= null && !restriction.getJMBG().isEmpty()
                            && !hairdresser.getJMBG().toLowerCase().contains(restriction.getJMBG().toLowerCase())) {
                        iterator.remove();
                        continue;
                    }

                    
                }
            }
            fireTableDataChanged();
        } catch (Exception ex) {
            Logger.getLogger(TableModelHairdresser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public ArrayList<Hairdresser> getHairdressers() {
        return hairdressers;
    }

    public void setRestriction(Hairdresser restriction) {
        this.restriction = restriction;
        updateHairdresser();
    }
    
    
}
