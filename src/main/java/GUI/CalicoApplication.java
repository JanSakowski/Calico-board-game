package GUI;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * Application class, that opens welcome screen scene
 */
public class CalicoApplication extends Application {
    /**
     * Overridden start method that adjusts basic properties of the game window
     * @param stage the primary stage for this application, onto which
     * the application scene can be set.
     * Applications may create other stages, if needed, but they will not be
     * primary stages.
     * @throws IOException input/output exception
     */
    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Calico");
        stage.setOnCloseRequest(windowEvent -> {
            windowEvent.consume();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Are you sure you want to close the window?");
            alert.setContentText("Any unsaved changes will be lost.");

            ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);

            if (result == ButtonType.OK) {
                stage.close();
            }
        });
        FXMLLoader loader = new FXMLLoader(getClass().getResource("welcomescreen.fxml"));
        URL url = getClass().getResource("/GUI/cats/CiraButton.png");
        Image icon = new Image(url.toString());
        stage.getIcons().add(icon);
        Parent root = loader.load();
        Scene scene = new Scene (root);
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
    }

    /**
     * Static main method
     * @param args args
     */
    public static void main(String[] args) {
        launch();
    }
}
