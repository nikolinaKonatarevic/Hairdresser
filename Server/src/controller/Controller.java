/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domain.Appointment;
import domain.AppointmentDTO;
import domain.AppointmentItem;
import domain.Hairdresser;
import domain.ServiceType;
import domain.User;
import operation.AbstractGenericOperation;
import operation.appointment.CreateAppointment;
import operation.appointment.GetAppointments;
import operation.appointment.UpdateAppointment;
import operation.hairdressers.CreateHairdresser;
import operation.hairdressers.GetHairdressers;
import operation.hairdressers.UpdateHairdresser;
import operation.login.Login;
import operation.servicetypes.GetServiceTypes;
import operation.users.CreateUser;
import operation.users.DeleteUser;
import operation.users.GetUsers;
import operation.users.UpdateUser;

/**
 *
 * @author Nikolina
 */
public class Controller {

    private static Controller instance;
    private AbstractGenericOperation operation;

    private Controller() {
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public Object login(User user) throws Exception {
        operation = new Login();
        operation.execute(user);
        return ((Login) operation).getLogin();
    }

    public Object get(Object object) throws Exception {
        if (object instanceof User) {
            return getUsers(object);
        }
        if (object instanceof Hairdresser) {
            return getHairdresser(object);
        }
        if (object instanceof ServiceType) {
            return getServiceTypes(object);
        }
        if (object instanceof Appointment) {
            return getAppointment(object);
        }
        return null;

    }

    public Object create(Object object) throws Exception {
        if (object instanceof User) {
            return createNewUser(object);
        }
        if (object instanceof Appointment) {
            return createAppointment(object);
        }
        if (object instanceof Hairdresser) {
            return createHairdresser(object);
        }

        return null;
    }

    public Object update(Object object) throws Exception {
        if (object instanceof User) {
            return updateUser(object);
        }
        if (object instanceof AppointmentDTO) {
            return updateAppointment(object);
        }
        if (object instanceof Hairdresser) {
            return updateHairdresser(object);
        }

        return null;
    }

    public Object delete(Object object) throws Exception {
        if (object instanceof User) {
            return deleteUser(object);
        } else {
            return null;
        }
    }

    private Object getUsers(Object object) throws Exception {
        operation = new GetUsers();
        operation.execute(object);
        return ((GetUsers) operation).getUsers();

    }

    private Object createNewUser(Object object) throws Exception {
        operation = new CreateUser((User) object);
        operation.execute(object);
        return ((CreateUser) operation).getUser();

    }

    private Object updateUser(Object object) throws Exception {
        operation = new UpdateUser();
        operation.execute(object);
        return ((UpdateUser) operation).getUser();
    }

    private Object deleteUser(Object object) throws Exception {
        operation = new DeleteUser();
        operation.execute(object);
        return ((DeleteUser) operation).isFlag();
    }

    private Object getHairdresser(Object object) throws Exception {
        operation = new GetHairdressers();
        operation.execute(object);
        return ((GetHairdressers) operation).getHairdressers();
    }

    private Object getServiceTypes(Object object) throws Exception {
        operation = new GetServiceTypes();
        operation.execute(object);
        return ((GetServiceTypes) operation).getServices();
    }

    private Object getAppointment(Object object) throws Exception {
        operation = new GetAppointments();
        operation.execute(object);
        return ((GetAppointments) operation).getAppointments();
    }

    private Object createAppointment(Object object) throws Exception {
        operation = new CreateAppointment((Appointment) (object));
        operation.execute(object);
        return ((CreateAppointment) operation).getAppointment();
    }

    private Object updateAppointment(Object object) throws Exception {
        operation = new UpdateAppointment();
        operation.execute(object);
        return ((UpdateAppointment) operation).getAppointment();
    }

    private Object createHairdresser(Object object) throws Exception {
        operation = new CreateHairdresser((Hairdresser) (object));
        operation.execute(object);
        return ((CreateHairdresser) operation).getHairdresser();
    }

    private Object updateHairdresser(Object object) throws Exception {
        operation = new UpdateHairdresser();
        operation.execute(object);
        return ((UpdateHairdresser) operation).getHairdresser();
    }

}
