package GUI;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Polygon;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Stream;

public class GameController {
    @FXML
    private Polygon hex1;

    @FXML
    private Polygon hex2;

    @FXML
    void clickDetected(MouseEvent event) {
        String id = event.getTarget().toString().substring(14,17);
        System.out.println("Coordinates: " + id);
    }
}
