package GUI;

import gamepackage.Game;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class GameEndController implements Initializable {
    @FXML
    GridPane scoreBoard;
    @FXML
    private Label player1;
    @FXML
    private Label player2;
    @FXML
    private Label player3;
    @FXML
    private Label player4;

    Game game = GameDataSingleton.getGame();
    String[] names = GameDataSingleton.getInstance().getPlayerNames();
    int[] scores;
    void start() {
         scores = new int[game.getPlayers().length];
        for (int i = 0; i < scores.length; i++) {
            scores[i] = game.getPlayers()[game.getCurrentPlayer()].getScore();
        }
        fill();
    }
    void fill() {
        if (game.getPlayers().length >= 2){
            player1.setText(1 + " " + names[0] + "~~~~> score: " + scores[0]);
            player2.setText(2 + " " + names[1] + "~~~~> score: " + scores[1]);
        }
        if (game.getPlayers().length >= 3) {
            player3.setText(3 + " " + names[2] + "~~~~> score: " + scores[2]);
        }
        if (game.getPlayers().length == 4) {
            player3.setText(4 + " " + names[3] + "~~~~> score: " + scores[3]);
        }

    }
    @FXML
    void goBackToLobby(MouseEvent e) {

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        start();
    }
}
