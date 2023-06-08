package GUI;


import game.Field;
import game.Game;
import game.Player;
import game.RegularTile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.SwipeEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Stream;

public class GameController implements Initializable {
    private Game game;
    @FXML
    private AnchorPane hexboard;

    private Polygon getHexagon(int x, int y) {
        for (Node node : hexboard.getChildren()) {
            if (node instanceof Polygon) {
                String coordinates = node.toString().substring(14, 17);
                String[] cords = coordinates.split("_");
                if (Integer.parseInt(cords[0]) == x && Integer.parseInt(cords[1]) == y) return (Polygon) node;
            }
        }
        return null;
    }
    private String getImagePath (RegularTile rTile){
        StringBuilder url = new StringBuilder();
        url.append("/GUI");
        switch (rTile.getColor()) {
            case GREEN -> url.append("/green/green");
            case PURPLE -> url.append("/purple/purple");
            case MAGENTA -> url.append("/pink/pink");
            case DARK_BLUE -> url.append("/darkblue/darkBlue");
            case LIGHT_BLUE -> url.append("/lightblue/lightBlue");
            case YELLOW -> url.append("/yellow/yellow");
        }
        switch (rTile.getPattern()) {
            case FERN -> url.append("5.png");
            case CROSSES -> url.append("3.png");
            case STRIPES -> url.append("2.png");
            case FLOWERS -> url.append("4.png");
            case PLANTS -> url.append("1.png");
            case DOTS -> url.append("6.png");
        }
        return url.toString();
    }
    @FXML
    void showPlayersBoard(Player player) {
        //Field currentField;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                Field currentField = player.getBoard().getField(i, j);
                if (currentField.hasRegularTile()) {
                    Polygon hexagon = getHexagon(i, j);
                    String path = getImagePath(currentField.getRegularTile());
                    URL url = getClass().getResource(path);
                    Image img = new Image(url.toString());
                    hexagon.setFill(new ImagePattern(img));
                }
            }
        }
    }

    @FXML
    void clickDetected(MouseEvent event) {
        URL url = getClass().getResource("/GUI/goaltiles/GoalTile1.png");
        Image img = new Image(url.toString());
        String id = event.getTarget().toString().substring(14, 17);
        if (event.getTarget() instanceof Polygon) {
            ((Polygon) event.getTarget()).setFill(new ImagePattern(img));
        }
        System.out.println("Coordinates: " + id);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        game = new Game(2);
        showPlayersBoard((game.getPlayers())[0]);
    }
}
