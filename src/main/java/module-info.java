module com.example.kierki.client {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.kierki.client to javafx.fxml;
    exports com.example.kierki.client;
    exports com.example.kierki.server;
    opens com.example.kierki.server to javafx.fxml;

}