package es.ieslavereda.chatwhats.Controller;

import es.ieslavereda.chatwhats.Model.Message;
import es.ieslavereda.chatwhats.Model.Receiver;
import es.ieslavereda.chatwhats.Model.Sender;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ComunicationManager implements Runnable {

    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    private final Receiver receiver;

    public ComunicationManager(Receiver receiver, Socket socket) {

        this.receiver = receiver;

        try {
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ComunicationManager(Receiver receiver) {
        this.receiver = receiver;
    }


    public void send(Message message) {
        new Sender(oos).send(message);
    }

    @Override
    public void run() {
        Message message;
        try {
            while ((message = (Message) ois.readObject()) != null) {
                receiver.receive(message);
            }
        } catch (Exception e) {
            receiver.remove(this);
        }
    }
}
