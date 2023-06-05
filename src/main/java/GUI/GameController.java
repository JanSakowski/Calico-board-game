package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    @FXML
    public Button napis;
    @FXML
    public Button circleButton;
    @FXML
    private void circlePls(ActionEvent e) {
        System.out.println("working");
    }
    @FXML
    private void napis(ActionEvent e) {
        System.out.println("chwdp");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        napis.setPickOnBounds(false);
        circleButton.setPickOnBounds(false);
    }
}
