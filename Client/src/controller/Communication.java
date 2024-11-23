/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import communication.Operation;
import communication.Reciever;
import communication.Request;
import communication.Response;
import communication.Sender;
import domain.Appointment;
import domain.AppointmentDTO;
import domain.AppointmentItem;
import domain.Hairdresser;
import domain.ServiceType;
import domain.User;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nikolina
 */
public class Communication {

    private Socket socket;
    private Sender sender;
    private Reciever reciever;
    private User currentUser;
    private static Communication instance;

    private Communication() {

        try {
            socket = new Socket("localhost", 9000);
            sender = new Sender(socket);
            reciever = new Reciever(socket);
            currentUser = new User();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public static Communication getInstance() {
        if (instance == null) {
            instance = new Communication();
        }
        return instance;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public User login(String email, String password) throws Exception {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        Request request = new Request(Operation.LOGIN, user);
        sender.send(request);
        Response response = (Response) reciever.recieve();
        if (response.getException() == null) {
            return (User) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public List<User> getAllUsers() throws Exception {
        Request request = new Request(Operation.GET, new User());
        sender.send(request);

        Response response = (Response) reciever.recieve();
        //System.out.println("lista " + (List<User>) response.getResult());
        if (response.getException() == null) {
            return (List<User>) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public User createUser(User user) throws Exception {
        Request request = new Request(Operation.CREATE, user);
        sender.send(request);
        Response response = (Response) reciever.recieve();
        if (response.getException() == null) {
            return (User) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public User updateUser(User selectedUser) throws Exception {
        Request request = new Request(Operation.UPDATE, selectedUser);
        sender.send(request);
        Response response = (Response) reciever.recieve();
        if (response.getException() == null) {
            return (User) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public List<Appointment> getAllAppointments() throws Exception {
        Request request = new Request(Operation.GET, new Appointment());
        sender.send(request);
        Response response = (Response) reciever.recieve();
        if (response.getException() != null) {
            throw response.getException();
        } else {
            return (List<Appointment>) response.getResult();
        }
    }

    public List<AppointmentItem> getAllAppointmentItems() throws Exception {
        Request request = new Request(Operation.GET, new AppointmentItem());
        sender.send(request);
        Response response = (Response) reciever.recieve();
        if (response.getException() == null) {
            return (List<AppointmentItem>) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public void deleteUser(User selectedUser) throws Exception {
        Request request = new Request(Operation.DELETE, selectedUser);
        sender.send(request);
        Response response = (Response) reciever.recieve();
        if (response.getException() != null) {
            throw response.getException();
        }
    }

    public List<Hairdresser> getAllHairdressers() throws Exception {
        Request request = new Request(Operation.GET, new Hairdresser());
        sender.send(request);
        Response response = (Response) reciever.recieve();
        if (response.getException() != null) {
            throw response.getException();
        }
        System.out.println(response.getResult());
        return (List<Hairdresser>) response.getResult();
    }

    public List<ServiceType> getAlllServiceTypes() throws Exception {
        Request request = new Request(Operation.GET, new ServiceType());
        sender.send(request);
        Response response = (Response) reciever.recieve();
        if (response.getException() != null) {
            throw response.getException();
        } else {
            return (List<ServiceType>) response.getResult();
        }
    }

    public Appointment createAppointment(Appointment appointment) throws Exception {
        Request request = new Request(Operation.CREATE, appointment);
        sender.send(request);
        Response response = (Response) reciever.recieve();
        if (response.getException() == null) {
            return (Appointment) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public Appointment updateAppointment(AppointmentDTO dto) throws Exception {
            Request request = new Request(Operation.UPDATE, dto);
        sender.send(request);
        Response response = (Response) reciever.recieve();
        if (response.getException() == null) {
            return (Appointment) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public Hairdresser createHairdresser(Hairdresser hairdresser) throws Exception {
          Request request = new Request(Operation.CREATE, hairdresser);
        sender.send(request);
        Response response = (Response) reciever.recieve();
        if (response.getException() == null) {
            return (Hairdresser) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public void deleteHairdresser(Hairdresser hairdresser)throws Exception {
Request request = new Request(Operation.DELETE, hairdresser);
        sender.send(request);
        Response response = (Response) reciever.recieve();
        if (response.getException() != null) {
            throw response.getException();
        }
    }

    public Hairdresser updateHairdresser(Hairdresser hairdresser) throws Exception {
 Request request = new Request(Operation.UPDATE, hairdresser);
        sender.send(request);
        Response response = (Response) reciever.recieve();
        if (response.getException() == null) {
            return (Hairdresser) response.getResult();
        } else {
            throw response.getException();
        }
    }

   
}
//    public List<ReadBook> getReadBooks(Member member) throws Exception {
//        ReadBook rb = new ReadBook();
//        rb.setMember(member);
//        Request request = new Request(Operation.GET, rb);
//        sender.send(request);
//        Response response = (Response) receiver.receive();
//        if (response.getException() == null) {
//            return (List<ReadBook>) response.getResult();
//        } else {
//            throw response.getException();
//        }
//    }
