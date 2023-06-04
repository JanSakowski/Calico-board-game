module com.example.calico {
    requires javafx.controls;
    requires javafx.fxml;


    opens GUI to javafx.fxml;
    exports GUI;

    opens game;
    exports game;
}