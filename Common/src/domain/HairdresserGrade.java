/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.Date;
import java.util.Objects;


/**
 *
 * @author Nikolina
 */
public class HairdresserGrade implements GenericEntity{
    
    private Grade grade;
    private Hairdresser hairdresser;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private BigDecimal salary;

    public HairdresserGrade() {
    }

    public HairdresserGrade(Grade grade, Hairdresser hairdresser, LocalDate dateStart, LocalDate dateEnd, BigDecimal salary) {
        this.grade = grade;
        this.hairdresser = hairdresser;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.salary = salary;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public Hairdresser getHairdresser() {
        return hairdresser;
    }

    public void setHairdresser(Hairdresser hairdresser) {
        this.hairdresser = hairdresser;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
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
        final HairdresserGrade other=  (HairdresserGrade) obj;
        if (!Objects.equals(this.hairdresser, other.hairdresser)) {
            return false;
        }
        if (!Objects.equals(this.grade, other.grade)) {
            return false;
        }
        if (!Objects.equals(this.dateEnd, other.dateEnd)) {
            return false;
        }
        if (!Objects.equals(this.dateStart, other.dateStart)) {
            return false;
        }
         if (!Objects.equals(this.salary, other.salary)) {
            return false;
        }
         return true;
    }

    
    @Override
    public String toString() {
        return "HairdresserGrade{" + "grade=" + grade + ", hairdresser=" + hairdresser + ", dateStart=" + dateStart + ", dateEnd=" + dateEnd + ", salary=" + salary + '}';
    }

    @Override
    public String getTableName() {
        return "hairdresser_grade";
    }

    @Override
    public String getColumnNamesFromInsert() {
        return "(grade_id,hairdresser_Id,date_Start,date_End,salary)";
    }

    @Override
    public String getInsertValues() {
        StringBuilder sb = new StringBuilder();
        sb.append("grade_id = ").append(grade.getId()).append(",")
                .append("hairdresser_Id = ").append(hairdresser.getId()).append(",")
                .append(" date_start = '").append(dateStart).append("',")
                .append("date_End ='").append(dateEnd).append("',")
                .append("salary =").append(salary);
        return sb.toString();
    }

    @Override
    public void setId(Long id) {
        
    }

    @Override
    public String getSelectValues() {
        return "select * from hairdresser_grade as hg inner join "
               +" grade as g on g.grade_id = hg.grade_id "
                + " inner join hairdresser as h on h.hairdresser_id = hg.hairdresser_id";
        
    }

    @Override
    public GenericEntity getNewObject(ResultSet rs) throws SQLException {
        return new HairdresserGrade(new Grade(rs.getLong("g.grade_id"),rs.getString("g.name"), rs.getString("g.description"), rs.getBigDecimal("g.salary")), 
                new Hairdresser(rs.getLong("h.hairdresser_id"), rs.getString("h.firstname"), rs.getString("h.lastname"),rs.getDate("h.date_of_employment").toLocalDate() ,rs.getString(" h.status")),
                rs.getDate("hg.date_Start").toLocalDate(),rs.getDate("hg.date_end").toLocalDate() ,rs.getBigDecimal("hg.salary"));
        
    }

    @Override
    public String getDeleteAndUpdateCondition(Object object) {
        return " grade_id = " + ((HairdresserGrade)object).getGrade().getId() +
                " and hairdresser_id = " + ((HairdresserGrade)object).getHairdresser().getId();
    }

    @Override
    public String getUpdateSetValues(Object object) {
        HairdresserGrade hg = (HairdresserGrade) object;
        StringBuilder sb = new StringBuilder();
        sb.append("date_start = '").append(Date.valueOf(hg.dateStart)).append("',").
                append(" date_end = '").append(Date.valueOf(hg.dateEnd)).append("',")
                .append(" salary =").append(hg.getSalary());
        return sb.toString();
                
    }
    
    
    
}
