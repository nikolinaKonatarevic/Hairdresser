/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package components;

import domain.User;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Nikolina
 */
public class TableModelAdmin extends AbstractTableModel{
    String[] columnNames = new String[]{"Firstname", "Lastname", "Username", "Password", "Role"};
    List<User> users; 

    public TableModelAdmin (List<User> users){
        this.users = users; 
    }

    @Override
    public String getColumnName(int column) {
        if (column> columnNames.length){
            return "n/a";
        }
        return columnNames[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
    
    
    @Override
    public int getRowCount() {
        return users.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        User a = users.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> a.getFirstname();
            case 1 -> a.getLastname();
            case 2 -> a.getEmail();
            case 3 -> a.getPassword();
            case 4 -> a.getRole();
            default -> "n/a";
        };
    }
    
    public void addUser (User a){
        if (users.contains(a))
            return;
        users.add(a);
        fireTableRowsInserted(users.size()-1, users.size()-1);
    }
}
