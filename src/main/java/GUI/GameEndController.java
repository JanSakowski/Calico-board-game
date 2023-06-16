package GUI;

import gamepackage.Game;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Generating the ending panel with scores by reading data from the singleton
 */
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
    // Creating a game instance fom the singleton, in order to read the players' scores and names and print the out to the labels
    Game game = GameDataSingleton.getGame();
    String[] names = GameDataSingleton.getInstance().getPlayerNames();
    int[] scores;
    // Setting up the score board
    void start() {
         scores = new int[game.getPlayers().length];
        for (int i = 0; i < scores.length; i++) {
            scores[i] = game.getPlayers()[game.getCurrentPlayer()].getScore();
        }
        fill();
    }
    // Filling the score board with text
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

        Stage stage = (Stage) player3.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("welcomescreen.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        Scene scene = new Scene (root);
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        start();
    }
}
