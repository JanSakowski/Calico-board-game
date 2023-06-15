package GUI;

import gamepackage.Game;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class GameEndApplication extends Application {
    Label secondLabel;
    StackPane secondaryLayout;
    Scene secondScene;
    Game game = GameDataMonostate.getGame();
    @FXML
    private GridPane scoreTable;
    @FXML
    private TextField p1;
    @FXML
    private TextField p2;
    @FXML
    private TextField p3;
    int[] scores = new int[game.getPlayers().length];

    @Override
    public void start(Stage stage) throws Exception {
        secondLabel = new Label("Here is the summary of your points:");

        secondaryLayout = new StackPane();
        secondaryLayout.getChildren().add(secondLabel);

        secondScene = new Scene(secondaryLayout, 230, 100);
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

        for (int i = 0; i < game.getPlayers().length; i++) {
            scores[i] = game.getPlayers()[i].getScore();
        }

        for (int i = 0; i < scores.length; i++) {
            System.out.println(scoreTable.getChildren().get(i));
        }
    }
}
