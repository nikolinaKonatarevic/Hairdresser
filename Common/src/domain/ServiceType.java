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
public class ServiceType implements GenericEntity{
    private Long id;
    private String name;
    private String description;
    private double duration;
    private BigDecimal price;

    public ServiceType() {
    }

    public ServiceType(Long id, String name, String description, double duration, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object obj) {
         if(this == obj )
            return true;
        
        if( obj == null)
            return false;
        
        if (getClass()!=obj.getClass())
            return false;
        
        final ServiceType other = (ServiceType) obj;
        
        
        if(!Objects.equals(this.name, other.name))
            return false;
        if(!Objects.equals(this.description, other.description))
            return false;
        if(!Objects.equals(this.duration, other.duration))
            return false;
         if(!Objects.equals(this.price, other.price))
            return false;
        
        return Objects.equals(this.id, other.id);

    }

    
    @Override
    public String toString() {
        return "ServiceType{" + "id=" + id + ", name=" + name + ", description=" + description + ", duration=" + duration + ", price=" + price + '}';
    }

    @Override
    public String getTableName() {
        return "servicetype";
    }

    @Override
    public String getColumnNamesFromInsert() {
        return " service_id, name, description, duration, price";
    }

    @Override
    public String getInsertValues() {
        StringBuilder sb = new StringBuilder();
        sb.append("'")
                .append(name).append("','")
                .append(description).append("',")
                .append(duration).append(",")
                .append(price);
        System.out.println(sb.toString());
        return sb.toString();
    }

    @Override
    public String getSelectValues() {
        return "select  service_id, name, description, duration, price from servicetype";
    }

    @Override
    public GenericEntity getNewObject(ResultSet rs) throws SQLException {
        return new ServiceType(rs.getLong("service_id"), rs.getString("name"), rs.getString("description"), rs.getDouble("duration"), rs.getBigDecimal("price"));
    }

    @Override
    public String getDeleteAndUpdateCondition(Object object) {
        return "service_id ="+ ((ServiceType)object).getId();
    }

    @Override
    public String getUpdateSetValues(Object object) {
        ServiceType s = (ServiceType) object;
        return "name = '"+ s.getName() +"',"+
                "description = '"+ s.getDescription() + "'+" 
               + "duration = "+ s.getDuration()+" , "
                + "price = "+s.getPrice()+" ";
                
    }
    
    
}
