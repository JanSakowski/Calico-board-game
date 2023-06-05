package GUI;
import game.CustomButton;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class CalicoApplication extends Application {
    private CustomButton myButton;

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Calico");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene (root);
        String css = this.getClass().getResource("demo.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        //stage.setResizable(false);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}
