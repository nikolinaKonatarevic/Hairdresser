/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Nikolina
 */
public class Appointment implements GenericEntity {

    private long id;
    private LocalDate date;
    private int start_time;
    private int end_time;
    private LocalDateTime createdOn;
    private BigDecimal totalPrice;
    private AppointmentStatusEnum status;
    private Hairdresser hairdresser;
    private User user;
    private List<AppointmentItem> items;

    public Appointment() {
    }

    public Appointment(long id, LocalDate date, int start_time, int end_time, LocalDateTime createdOn, BigDecimal totalPrice, AppointmentStatusEnum status, Hairdresser hairdresser, User user) {
        this.id = id;
        this.date = date;
        this.start_time = start_time;
        this.end_time = end_time;
        this.createdOn = createdOn;
        this.totalPrice = totalPrice;
        this.status = status;
        this.hairdresser = hairdresser;
        this.user = user;
        items = new ArrayList<>();
       
    }

    

    public long getId() {
        return id;
    }



    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Hairdresser getHairdresser() {
        return hairdresser;
    }

    public void setHairdresser(Hairdresser hairdresser) {
        this.hairdresser = hairdresser;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        final Appointment other = (Appointment) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.start_time, other.start_time)) {
            return false;
        }
        if (!Objects.equals(this.end_time, other.end_time)) {
            return false;
        }
        if (!Objects.equals(this.createdOn, other.createdOn)) {
            return false;
        }
        if (!Objects.equals(this.hairdresser, other.hairdresser)) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.totalPrice, other.totalPrice)) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        return Objects.equals(this.items, this.items);

    }

    @Override
    public String toString() {
        return "Appointment{" + "id=" + id + ", date=" + date + ", start_time=" + start_time + ", end_time=" + end_time + ", createdOn=" + createdOn + ", totalPrice=" + totalPrice + ", status=" + status + ", hairdresser=" + hairdresser + ", user=" + user + ", items=" + items + '}';
    }

    

    @Override
    public String getTableName() {
        return "appointment";
    }

    @Override
    public String getColumnNamesFromInsert() {
        return " ( date, start_time, end_time, created_on, total_price, hairdresser_id, user_id,status)";
    }

    @Override
    public String getInsertValues() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("'").append(date).append("', ")
                .append(start_time).append(",")
                .append(end_time).append(",'")
                .append(createdOn).append("', ")
                .append(totalPrice).append(",")
                .append(hairdresser.getId()).append(",")
                .append(user.getId()).append(",'")
                .append(status).append("'");
        return sb.toString();
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getSelectValues() {
        return "SELECT *"
                + " FROM appointment AS app INNER JOIN "
                + "hairdresser AS h ON h.hairdresser_id = app.hairdresser_id INNER JOIN "
                + " user AS u ON u.user_id = app.user_id ";
    }

    @Override
    public GenericEntity getNewObject(ResultSet rs) throws SQLException {

        String role = rs.getString("u.role");
        //System.out.println(role);
        RoleEnum r;

        if (role.toLowerCase().equals("admin")) {
            r = RoleEnum.ADMIN;
        } else {
            r = RoleEnum.CUSTOMER;
        }
        
        String statusA = rs.getString("status");
        AppointmentStatusEnum a =null;
        if(statusA.toLowerCase().equals("scheduled"))
            a= AppointmentStatusEnum.SCHEDULED;
        if(statusA.toLowerCase().equals("finished"))
            a= AppointmentStatusEnum.FINISHED;
        if(statusA.toLowerCase().equals("canceled"))
            a= AppointmentStatusEnum.CANCELED;
        
        String statusH = rs.getString("h.status");

        HairdresserStatusEnum s = null;
        if (statusH.toLowerCase().equals("active")) {
            s = HairdresserStatusEnum.ACTIVE;
        }
        if (statusH.toLowerCase().equals("vacation")) {
            s = HairdresserStatusEnum.VACATION;
        }
        if (statusH.toLowerCase().equals("sick_leave")) {
            s = HairdresserStatusEnum.SICK_LEAVE;
        }

        return new Appointment(rs.getLong("appointment_id"), rs.getDate("date").toLocalDate(),
                rs.getInt("start_time"), rs.getInt("end_time"), rs.getTimestamp("created_on").toLocalDateTime(),
                rs.getBigDecimal("total_price"), a,
                new Hairdresser(rs.getLong("h.hairdresser_id"), rs.getString("h.firstname"),
                        rs.getString("h.lastname"),
                        rs.getDate("h.date_of_employment").toLocalDate(), s),
                new User(rs.getLong("u.user_id"), rs.getString("u.firstname"),
                        rs.getString("u.lastname"), rs.getString("u.email"),
                        rs.getString("u.password"), r));
    }

    @Override
    public String getDeleteAndUpdateCondition(Object object) {
        return "appointment_id=" + ((Appointment) object).getId();
    }

    @Override
    public String getUpdateSetValues(Object object) {
        Appointment appointment = (Appointment) object;
        return " date = '" + Date.valueOf(appointment.getDate()) + "', start_time = "+ appointment.getStart_time()+ ", end_time = " + appointment.getEnd_time() + ", status = '" + appointment.getStatus() + "', hairdresser_id =" + appointment.getHairdresser().getId() + " ";
    }

    public AppointmentStatusEnum getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatusEnum status) {
        this.status = status;
    }

    public List<AppointmentItem> getItems() {
        return items;
    }

    public void setItems(List<AppointmentItem> items) {
        this.items = items;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getStart_time() {
        return start_time;
    }

    public void setStart_time(int start_time) {
        this.start_time = start_time;
    }

    public int getEnd_time() {
        return end_time;
    }

    public void setEnd_time(int end_time) {
        this.end_time = end_time;
    }

}
