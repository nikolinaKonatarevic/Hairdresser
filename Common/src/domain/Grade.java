/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 *
 * @author Nikolina
 */
public class Grade implements GenericEntity{
    
    private long id; 
    private String name;
    private String description;
    private BigDecimal minSalary;

    public Grade() {
    }

    public Grade(long id, String name, String description, BigDecimal minSalary) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.minSalary = minSalary;
    }

    public long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(BigDecimal minSalary) {
        this.minSalary = minSalary;
    }

    @Override
    public String toString() {
        return "Grade{" + "id=" + id + ", name=" + name + ", description=" + description + ", minSalary=" + minSalary + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Grade other = (Grade) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.minSalary, other.minSalary)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    
    @Override
    public String getTableName() {
        return "grade";
    }

    @Override
    public String getColumnNamesFromInsert() {
        return "(name, description, min_salary)";
    }

    @Override
    public String getInsertValues() {
        StringBuilder sb = new StringBuilder();
        sb.append("'").append(name).append("' ,")
                .append("'").append(description).append("',")
                .append(minSalary);
        return sb.toString();
    }

    @Override
    public void setId(Long id) {
         this.id = id;
    }

    @Override
    public String getSelectValues() {
        return "select * from grade as g";
    }

    @Override
    public GenericEntity getNewObject(ResultSet rs) throws SQLException {
        return new Grade(rs.getLong("grade_id"), rs.getString("name"), rs.getString("description"), rs.getBigDecimal("price"));
    }

    @Override
    public String getDeleteAndUpdateCondition(Object object) {
        return "grade_id =" + ((Grade)object).getId();
    }

    @Override
    public String getUpdateSetValues(Object object) {
        Grade grade = (Grade) object;
        StringBuilder sb=  new StringBuilder();
        sb.append("name = '").append(grade.name).append("',")
                .append("description ='").append(description).append("',")
                .append(" min_salary =").append(minSalary);
        return sb.toString();
                
    }
    
    
    
}
