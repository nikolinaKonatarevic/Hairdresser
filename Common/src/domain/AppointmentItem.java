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
import java.util.Objects;

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
        final AppointmentItem other = (AppointmentItem) obj;
        if (!Objects.equals(this.serviceType, other.serviceType)) {
            return false;
        }
        return true;
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
        return "(appointment_id, price, service_id)";
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
                + " INNER JOIN hairdresser AS h ON a.hairdresser_id = h.hairdresser_id "
                + " INNER JOIN servicetype AS s ON s.service_id = ai.service_id "
                + "INNER JOIN USER AS u ON u.user_id = a.user_id";
    }

    @Override
    public GenericEntity getNewObject(ResultSet rs) throws SQLException {
         String statusA = rs.getString("a.status");
        AppointmentStatusEnum a =null;
        
        if(statusA.toLowerCase().equals("scheduled"))
            a= AppointmentStatusEnum.SCHEDULED;
        if(statusA.toLowerCase().equals("finished"))
            a= AppointmentStatusEnum.FINISHED;
        if(statusA.toLowerCase().equals("canceled"))
            a= AppointmentStatusEnum.CANCELED;
       String role = rs.getString("u.role");
        RoleEnum r;
        
        if(role.toLowerCase().equals("admin"))
            r = RoleEnum.ADMIN;
        else 
            r = RoleEnum.CUSTOMER;
        
         String status = rs.getString("status");
        HairdresserStatusEnum s = null;
        if(status.toLowerCase().equals("active"))
            s = HairdresserStatusEnum.ACTIVE;
        if(status.toLowerCase().equals("vacation"))
            s = HairdresserStatusEnum.VACATION;
        if(status.toLowerCase().equals("sick_leave"))
            s = HairdresserStatusEnum.SICK_LEAVE;

        return new AppointmentItem(rs.getLong("ai.item_id"),
                new Appointment(rs.getLong("a.appointment_id"), rs.getDate("a.date").toLocalDate(), rs.getInt("a.start_time"),rs.getInt("a.end_time"),rs.getTimestamp("a.created_on").toLocalDateTime(),
                        rs.getBigDecimal("a.total_price"), a,
                        new Hairdresser(rs.getLong("h.hairdresser_id"), rs.getString("h.firstname"),
                                rs.getString("h.lastname"), rs.getString("h.JMBG"),
                                rs.getDate("h.date_of_employment").toLocalDate(), s),
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
