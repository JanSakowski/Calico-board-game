package GUI;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * A controller for the lobby window. You can set the number of players and their names.
 */
public class LobbyController implements Initializable {
    @FXML
    private Label p1, p2, p3, p4;
    @FXML
    private TextField p1Name, p2Name, p3Name, p4Name;
    @FXML
    private ChoiceBox<String> numberOfPlayers;
    private String currentMode;
    private String[] names = new String[4];
    int numberChosen = 0;
    @FXML
    private void go() throws IOException {
        if (!numberOfPlayers.equals("")) {
            GameDataSingleton.getInstance().setNumberOfPlayers(numberChosen);
            GameDataSingleton.getInstance().setPlayerNames(names);
            Stage stage = (Stage) numberOfPlayers.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(true);
            stage.show();
        }
    }
    @FXML
    private void goBack() throws IOException {
        Stage stage = (Stage) numberOfPlayers.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("welcomescreen.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene (root);
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        numberOfPlayers.getItems().addAll("2","3","4");
        numberOfPlayers.setValue("");
        numberOfPlayers.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                currentMode = numberOfPlayers.getSelectionModel().getSelectedItem();
                switch (currentMode){
                    case "2" ->{
                        p3.setVisible(false);
                        p3Name.setVisible(false);
                        p4.setVisible(false);
                        p4Name.setVisible(false);

                        names[0] = p1Name.getText();
                        names[1] = p2Name.getText();
                        numberChosen = 2;
                    }
                    case "3" ->{
                        p3.setVisible(true);
                        p3Name.setVisible(true);
                        p4.setVisible(false);
                        p4Name.setVisible(false);

                        names[0] = p1Name.getText();
                        names[1] = p2Name.getText();
                        names[2] = p3Name.getText();
                        numberChosen = 3;
                    }
                    case "4" ->{
                        p3.setVisible(true);
                        p3Name.setVisible(true);
                        p4.setVisible(true);
                        p4Name.setVisible(true);

                        names[0] = p1Name.getText();
                        names[1] = p2Name.getText();
                        names[2] = p3Name.getText();
                        names[3] = p4Name.getText();
                        numberChosen = 4;
                    }
                }
            }
        });
    }
}
