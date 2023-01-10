package es.ieslavereda.chatwhats.Model;

import java.io.Serializable;

public class Message implements Serializable {
    private String texto;

    public Message(String texto){
        this.texto = texto;
    }

    public String getTexto(){
        return texto;
    }
}
