package es.ieslavereda.chatwhats.Model;

import es.ieslavereda.chatwhats.Controller.ComunicationManager;

public interface Receiver {

    void receive(Message message);

    void remove(ComunicationManager communicationManager);

}
