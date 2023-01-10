package es.ieslavereda.chatwhats.Client;

import es.ieslavereda.chatwhats.Controller.ComunicationManager;
import es.ieslavereda.chatwhats.Model.Message;
import es.ieslavereda.chatwhats.Model.Receiver;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientController implements Receiver {
    public static ComunicationManager cm;
    @FXML
    public TextArea area;
    @FXML
    public TextField tf;
    @FXML
    public TextField nombre;
    public String miNombre;
    @FXML
    protected void startServer() {
        String hostName = "127.0.0.1";
        int portNumber = 8000;
        miNombre = nombre.getText();
        try {
            Socket client = new Socket(hostName, portNumber);

            cm = new ComunicationManager(this, client);
            Thread t = new Thread(cm);
            t.setDaemon(true);
            t.start();

        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                    hostName);
            System.exit(1);
        }
    }

    @FXML
    public void send(){
        cm.send(new Message(miNombre + " : " + tf.getText() + "\n"));
        tf.setText("");
    }

    @Override
    public void receive(Message message) {
        String texto = message.getTexto();
        if(texto.contains(miNombre)){
            area.appendText("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+message.getTexto());
        }else{
            area.appendText(message.getTexto());
        }

    }

    @Override
    public void remove(ComunicationManager communicationManager) {
        this.cm = null;
    }
}