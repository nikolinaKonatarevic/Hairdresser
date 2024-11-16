/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.components;

import controller.Communication;
import domain.User;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Nikolina
 */
public class TableModelViewUsers extends AbstractTableModel implements Runnable {

    ArrayList<User> users;
    private Class[] columnClass = new Class[]{Long.class, String.class, String.class, String.class, String.class};
    User restriction = null;

    public TableModelViewUsers() {

        updateTable();
    }

    @Override
    public int getRowCount() {
        if (users == null) {
            return 0;
        } else {
            return users.size();
        }
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Id";
            case 1:
                return "Firstname";
            case 2:
                return "Lastname";
            case 3:
                return "Email";
            case 4:
                return "Role";

            default:
                return "n/a";
        }

    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex > columnClass.length) {
            return Object.class;
        } else {
            return columnClass[columnIndex];
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        User user = users.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return user.getId();
            case 1:
                return user.getFirstname();
            case 2:
                return user.getLastname();
            case 3:
                return user.getEmail();
            case 4:
                return user.getRole();
            default:
                return "n/a";
        }
    }

    @Override
    public void run() {
        while (true)
        try {
            updateTable();
            //System.out.println("updateuje se tabela");
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(TableModelViewUsers.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void updateTable() {

        //ArrayList<User> orgUser;
        try {
            users = (ArrayList<User>) Communication.getInstance().getAllUsers();

            if (restriction != null) {
                //System.out.println(users);

                // Use an iterator to safely remove elements during iteration
                Iterator<User> iterator = users.iterator();

                while (iterator.hasNext()) {
                    User user = iterator.next();

                    if (restriction.getFirstname() != null && !restriction.getFirstname().isEmpty()
                            && !user.getFirstname().toLowerCase().contains(restriction.getFirstname().toLowerCase())) {
                        iterator.remove();
                        continue;
                    }

                    if (restriction.getLastname() != null && !restriction.getLastname().isEmpty()
                            && !user.getLastname().toLowerCase().contains(restriction.getLastname().toLowerCase())) {
                        iterator.remove();
                        continue;
                    }

                    if (restriction.getEmail() != null && !restriction.getEmail().isEmpty()
                            && !user.getEmail().toLowerCase().contains(restriction.getEmail().toLowerCase())) {
                        iterator.remove();
                        continue;
                    }

                    if (restriction.getRole() != null && !restriction.getRole().isEmpty()
                            && !user.getRole().toLowerCase().contains(restriction.getRole().toLowerCase())) {
                        iterator.remove();
                    }
                }
            }

            fireTableDataChanged();
        } catch (Exception ex) {
            Logger.getLogger(TableModelViewUsers.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void setRestriction(User restriction) {
        this.restriction = restriction;
    }

}
