/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 *
 * @author Nikolina
 */
public class User implements GenericEntity{
    
    private long id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String role;

    public User() {
    }

    public User(long id, String firstname, String lastname, String email, String password, String role) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email + ", password=" + password + ", role=" + role + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj )
            return true;
        
        if( obj == null)
            return false;
        
        if (getClass()!=obj.getClass())
            return false;
        
        final User other  = (User) obj;
        
        if(!Objects.equals(this.firstname, other.firstname))
            return false;
        if(!Objects.equals(this.email, other.email))
            return false;
        if(!Objects.equals(this.password, other.password))
            return false;
        
        return Objects.equals(this.id, other.id);
        
    }

    
    @Override
    public String getTableName() {
        return "user";
    }

    @Override
    public String getColumnNamesFromInsert() {
        return " (firstname, lastname, email, password, role) ";
    }

    @Override
    public String getInsertValues() {
        StringBuilder sb= new StringBuilder();
        sb.append("'")
                .append(firstname).append("','")
                .append(lastname).append("','")
                .append(email).append("','")
                .append(password).append("','")
                .append(role).append("'");
        System.out.println(sb.toString());
        return sb.toString();
        
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getSelectValues() {
        return "select user_id,firstname, lastname, email, password, role from user";
    }

    @Override
    public GenericEntity getNewObject(ResultSet rs) throws SQLException {
        return new User(rs.getLong("user_id"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("email"), rs.getString("password"), rs.getString("role"));
    }

    @Override
    public String getDeleteAndUpdateCondition(Object object) {
        return "user_id = "+ ((User)object).getId();
    }

    @Override
    public String getUpdateSetValues(Object object) {
        User u = (User) object;
        return "firstname = '"+ u.getFirstname()+"',"
                +"lastname ='"+u.getLastname()+"',"
                +"email='"+ u.getEmail()+"',"
                +"password = '"+ u.getPassword()+"',"
                + "role ="+u.getRole()+"'";
                
    }
    
}
