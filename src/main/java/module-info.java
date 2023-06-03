module com.example.calico {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.calico.game to javafx.fxml;
    exports com.example.calico.game;
}