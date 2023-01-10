package es.ieslavereda.chatwhats.Controller;

import es.ieslavereda.chatwhats.Model.Message;
import es.ieslavereda.chatwhats.Model.Receiver;

import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class ChannelManager implements Receiver {
    private List<ComunicationManager> comunicationManagerList;
    private Chat c;
    public ChannelManager(){
        c = new Chat();
        comunicationManagerList = new LinkedList<ComunicationManager>();
    }

    public void add(Socket socket){
        ComunicationManager cm = new ComunicationManager(this, socket);
        comunicationManagerList.add(cm);
        System.out.println("Cliente añadido");

        Thread myT = new Thread(cm);
        myT.setDaemon(true);
        myT.start();

        writeAllHistory(cm);
    }

    public void writeAllHistory(ComunicationManager cm){
        cm.send(new Message(c.getAllText()));
    }

    public void writeTextAll(){
        for(ComunicationManager cm : comunicationManagerList){
            cm.send(new Message(c.getText()));
        }
    }

    @Override
    public void receive(Message message) {
        System.out.println(message.getTexto());
        c.writeText(message.getTexto());
        writeTextAll();
    }

    @Override
    public void remove(ComunicationManager communicationManager) {
        comunicationManagerList.remove(communicationManager);
    }
}
