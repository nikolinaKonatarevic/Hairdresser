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

}
