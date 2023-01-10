module es.ieslavereda.chatwhats {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens es.ieslavereda.chatwhats to javafx.fxml;
    exports es.ieslavereda.chatwhats.Client;
    opens es.ieslavereda.chatwhats.Client to javafx.fxml;
    exports es.ieslavereda.chatwhats;
}