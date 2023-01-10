package es.ieslavereda.chatwhats.Server;

import es.ieslavereda.chatwhats.Controller.ChannelManager;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {

    public static void main(String[] args) {
        int portNumber = 8000;
        boolean listening = true;

        ChannelManager cm = new ChannelManager();
        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            System.out.println("Server creado y escuchando");
            while (listening) {
                cm.add(serverSocket.accept());
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + portNumber);
            System.exit(-1);
        }
    }
}