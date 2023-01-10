package es.ieslavereda.chatwhats.Model;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class Sender implements Runnable{
    private final ObjectOutputStream oos;
    private Message message;

    public Sender(ObjectOutputStream oos) {
        this.oos = oos;
    }

    public void send(Message message) {
        this.message = message;
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            oos.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
