package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class GameEndApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Label secondLabel = new Label("Here is the summary of your points:");

        StackPane secondaryLayout = new StackPane();
        secondaryLayout.getChildren().add(secondLabel);

        Scene secondScene = new Scene(secondaryLayout, 230, 100);
        stage.setTitle("Game summary");
        stage.setScene(secondScene);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("endgame.fxml"));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
        } catch ( IOException ioe ) {
            ioe.printStackTrace();
        }
        stage.initModality(Modality.WINDOW_MODAL);

        stage.show();
    }
}
