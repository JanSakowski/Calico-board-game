package GUI;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class CalicoApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Calico");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene (root);
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}
