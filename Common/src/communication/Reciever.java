/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package communication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 *
 * @author Nikolina
 */
public class Reciever {
    private Socket socket;

    public Reciever(Socket socket) {
        this.socket = socket;
    }

public Object recieve() throws Exception {
    ObjectInputStream in;
    
        try {
            in=new ObjectInputStream(socket.getInputStream());
            return in.readObject();
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new Exception("Error: could not recieve object. "+ ex.getMessage());
        }
}    
    
}
