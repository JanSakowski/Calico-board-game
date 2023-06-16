package GUI;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomeScreenController {
    private GameDataSingleton data;

    @FXML
    private Button loadGame, newGame, exit;

    @FXML
    public void newGame() throws IOException {
        Stage stage = (Stage) exit.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("lobby.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
    }

    @FXML
    public void exit() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit Confirmation");
        alert.setHeaderText("Do you really want to exit?");
        alert.setContentText("Choose your option.");
        ButtonType buttonOK = ButtonType.YES;
        ButtonType buttonCancel = ButtonType.NO;
        alert.getButtonTypes().setAll(buttonOK, buttonCancel);
        alert.setOnCloseRequest(new EventHandler<DialogEvent>() {
            @Override
            public void handle(DialogEvent event) {
                ButtonType result = alert.getResult();
                if (result == buttonOK) {
                    ((Stage)exit.getScene().getWindow()).close();
                } else {
                    event.consume(); // Consume the event to prevent closing
                }
            }
        });

        alert.showAndWait();
    }
    @FXML
    public void loadGame(MouseEvent e) {

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
