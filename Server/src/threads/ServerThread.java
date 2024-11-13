/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threads;

import domain.User;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nikolina
 */
public class ServerThread extends Thread{
    
    private ServerSocket serverSocket;
    private List<HandleClientThread> clients; 

    public ServerThread() throws IOException {
        serverSocket = new ServerSocket(9000);
        clients = new ArrayList<>();
    }

    @Override
    public void run() {
        while(!serverSocket.isClosed()) {
            try {
                System.out.println("Waiting for a client...");
                
                Socket socket = serverSocket.accept();
                HandleClientThread thread = new HandleClientThread(socket, this);
                clients.add(thread);
                thread.start();
            } catch (IOException ex) {
                System.out.println(serverSocket.isClosed());
                if(serverSocket.isClosed())
                    break;
                ex.printStackTrace();
            }
        }
        stopAllThreads();
    }

    private void stopAllThreads() {
        Iterator<HandleClientThread> iterator = clients.iterator();
        while(iterator.hasNext()) {
            HandleClientThread client = iterator.next();
            try {
                client.getSocket().close();
                iterator.remove();
                System.out.println("uslo?");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public List<User> getAllAdmins() {
        List<User> users = new ArrayList<>();
        for (HandleClientThread client : clients){
            users.add(client.getActiveUser());
        }
        return users;
    }
    
    
    
}
