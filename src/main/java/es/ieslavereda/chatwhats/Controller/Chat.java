package es.ieslavereda.chatwhats.Controller;

public class Chat {
    private String mensaje;
    private String historial;

    public Chat(){
        mensaje = "";
        historial = "Bienvenido al chat \n";
    }

    public void writeText(String text){
        historial += text;
        mensaje = text;
    }

    public String getText(){
        return mensaje;
    }

    public String getAllText(){
        return historial;
    }
}
