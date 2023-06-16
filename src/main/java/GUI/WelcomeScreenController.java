package GUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomeScreenController {
    private GameDataSingleton data;

    @FXML
    private Button number1;
    @FXML
    private Button number2;
    @FXML
    private Button number3;
    @FXML
    private Button number4;

    @FXML
    public void button1(MouseEvent e) {
        GameDataSingleton.getInstance().setNumberOfPlayers(2);
        changeScene(e);
    }
    @FXML
    public void button2(MouseEvent e) {
        //data = new GameDataMonostate(2);
        //changeScene(e);
    }
    @FXML
    public void button3(MouseEvent e) {
        //data = new GameDataMonostate(3);
        //changeScene(e);
    }
    @FXML
    public void button4(MouseEvent e) {
        //data = new GameDataMonostate(4);
        //changeScene(e);
    }

    public void changeScene(MouseEvent e) {
        try {
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.out.println("Error loading the scene");
        }
    }
}
