/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 *
 * @author Nikolina
 */
public class AppointmentItem implements GenericEntity {

    private long id;
    private Appointment appointment;
    private BigDecimal price;
    private ServiceType serviceType;

    public AppointmentItem() {
    }

    public AppointmentItem(long id, Appointment appointment, BigDecimal price, ServiceType serviceType) {
        this.id = id;
        this.appointment = appointment;
        this.price = price;
        this.serviceType = serviceType;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public String toString() {
        return "AppointmentItem{" + "id=" + id + ", appointment=" + appointment + ", price=" + price + ", serviceType=" + serviceType + '}';
    }

    @Override
    public String getTableName() {
        return "appointmentitem";
    }

    @Override
    public String getColumnNamesFromInsert() {
        return "(item_id,appointment_id, price, service_id)";
    }

    @Override
    public String getInsertValues() {
        StringBuilder sb = new StringBuilder();
        sb.append(appointment.getId()).append(",")
                .append(price).append(",")
                .append(serviceType.getId());
        return sb.toString();
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getSelectValues() {
        return "SELECT *"
                + " FROM appointmentitem AS ai INNER JOIN "
                + "appointment AS a ON ai.appointment_id = a.appointment_id "
                + " INNER JOIN servicetype AS s ON s.service_id = ai.service_id ";
    }

    @Override
    public GenericEntity getNewObject(ResultSet rs) throws SQLException {
       String role = rs.getString("u.role");
        Role r;
        
        if(role.toLowerCase().equals("admin"))
            r = Role.ADMIN;
        else 
            r = Role.CUSTOMER;

        return new AppointmentItem(rs.getLong("ai.id"),
                new Appointment(rs.getLong("a.appointment_id"), rs.getTimestamp("a.date_time_start").toLocalDateTime(), rs.getTimestamp("a.date_time_start").toLocalDateTime(),
                        rs.getBigDecimal("a.total_price"), rs.getString("a.status"),
                        new Hairdresser(rs.getLong("h.hairdresser_id"), rs.getString("h.firstname"),
                                rs.getString("h.lastname"),
                                rs.getDate("h.date_of_employment").toLocalDate(), rs.getString("h.status")),
                        new User(rs.getLong("u.user_id"), rs.getString("u.firstname"),
                                rs.getString("u.lastname"), rs.getString("u.email"),
                                rs.getString("u.password"), r)), rs.getBigDecimal("ai.price"),
                new ServiceType(rs.getLong("s.service_id"), rs.getString("s.name"), rs.getString("s.description"), rs.getDouble("s.duration"), rs.getBigDecimal("s.price")));
    }

    @Override
    public String getDeleteAndUpdateCondition(Object object) {
        return "item_id = " + ((AppointmentItem) object).getId();
    }

    @Override
    public String getUpdateSetValues(Object object) {
        AppointmentItem ai = (AppointmentItem) object;
        return "item_id = " + ai.getId() + ", appointment_id = "+ ai.getAppointment().getId() + ", price =  " + ai.getPrice() + ", service_id = " + ai.getServiceType().getId();
    }

}
