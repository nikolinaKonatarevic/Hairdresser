/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threads;

import communication.Reciever;
import communication.Request;
import communication.Response;
import communication.Sender;
import controller.Controller;
import domain.User;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import loggedUsers.LoggedUsersSingleton;

/**
 *
 * @author Nikolina
 */
public class HandleClientThread extends Thread {

    private Socket socket;
    private ServerThread server;
    private User activeUser = null;
    Sender sender;
    Reciever reciever;
    Request request;
    Response response;

    public HandleClientThread(Socket socket, ServerThread server) {
        this.socket = socket;
        sender = new Sender(socket);
        reciever = new Reciever(socket);
        this.server = server;
    }

    @Override
    public void run() {
        try {
            while (true) {

                request = (Request) reciever.recieve();
                response = handleRequest(request);
                sender.send(response);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
           
        }
    }

    private Response handleRequest(Request request) {
        
        response = new Response();
        
        try{
        switch(request.getOperation()) {
            case LOGIN: 
                
                User user = (User) request.getArgument();
                System.out.println(user);
                response.setResult(Controller.getInstance().login(user));
                
                if(LoggedUsersSingleton.getInstance().getAllLoggedUsers().contains(response.getResult())) {
                    response.setException(new Exception("This user is already logged in."));
                    return response;
                }
                
                if(response.getResult()!= null) {
                    activeUser = (User) response.getResult();
                    LoggedUsersSingleton.getInstance().addLoggedUser(activeUser);
                }
                System.out.println(activeUser);
                break;
                
            case CREATE: 
                response.setResult(Controller.getInstance().create(request.getArgument()));
                
                break;
                
            case GET:
                response.setResult(Controller.getInstance().get(request.getArgument()));
                break;
        }
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return response; 
        
    }

    public Socket getSocket() {
        return socket;
    }

    public User getActiveUser() {
        return activeUser;
    }

    
}
