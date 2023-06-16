package GUI;

import gamepackage.Game;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class GameEndController implements Initializable {
    Game game;
    @FXML
    GridPane scoresBoard;
    @FXML
    TextField p1;
    @FXML
    TextField p2;
    @FXML
    TextField p3;
    @FXML
    TextField p4;


    void initialize() {
        p1.setText("Halo prosze");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        game = GameDataMonostate.getGame();
        initialize();
    }
}
