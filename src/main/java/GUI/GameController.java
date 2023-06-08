package GUI;


import game.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.StrokeType;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    private Game game;
    @FXML
    private AnchorPane hexboard;
    @FXML
    private ImageView cat0, cat1, cat2;
    @FXML
    private GridPane onHand, cat0Patterns, cat1Patterns, cat2Patterns, table, colorButtons;

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

    //smaller for one, normal otherwise
    private Polygon makeNewHexagon(int size) {
        Polygon hexagon;
        if (size == 1) {
            hexagon = new Polygon(
                    -26.5, 24.5,
                    0.0, 8.0,
                    0.0, -22.0,
                    -26.5, -38.5,
                    -53.0, -22.0,
                    -53.0, 8.0
            );

        } else {
            hexagon = new Polygon(
                    -53.0, 49.0,
                    0.0, 16.0,
                    0.0, -44.0,
                    -53.0, -77.0,
                    -106.0, -44.0,
                    -106.0, 16.0
            );
        }
        hexagon.setFill(Color.valueOf("#daa662"));
        hexagon.setStroke(Color.BLACK);
        hexagon.setStrokeType(StrokeType.INSIDE);
        return hexagon;
    }

    private String getImagePath(Tile tile) {
        if (tile instanceof ProjectTile)
            return getProjectImagePath((ProjectTile) tile);
        else
            return getRegularImagePath((RegularTile) tile);
    }

    private String getProjectImagePath(ProjectTile pTile) {
        StringBuilder url = new StringBuilder();
        url.append("/GUI");
        switch (pTile.getType()) {
            case AAABBC -> url.append("/goaltiles/AAABBC.png");
            case AAABBB -> url.append("/goaltiles/AAABBB.png");
            case AABBCD -> url.append("/goaltiles/AABBCD.png");
            case ABCDEF -> url.append("/goaltiles/ABCDEF.png");
            case AAAABB -> url.append("/goaltiles/AAAABB.png");
            case AABBCC -> url.append("/goaltiles/AABBCC.png");
        }
        return url.toString();
    }

    private String getRegularImagePath(RegularTile rTile) {
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
                    String path = getRegularImagePath(currentField.getRegularTile());
                    changeFill(hexagon, path);
                }
            }
        }
    }

    private void changeFill(Polygon hexagon, String path) {
        URL url = getClass().getResource(path);
        Image img = new Image(url.toString());
        hexagon.setFill(new ImagePattern(img));
    }

    private String getCatsTilesPath(int whichCat, int whichTile) {
        int i = 0;
        StringBuilder path = new StringBuilder();
        path.append("/GUI/patterns/");
        for (Cat cat : game.getCatBoards().keySet()) {
            if (i == whichCat) {
                CatBoard catBoard = game.getCatBoards().get(cat);
                switch (catBoard.getPreferredPatterns()[whichTile]) {
                    case FERN -> path.append("fern5.png");
                    case CROSSES -> path.append("crosses3.png");
                    case PLANTS -> path.append("plants1.png");
                    case STRIPES -> path.append("stripes2.png");
                    case DOTS -> path.append("dots6.png");
                    case FLOWERS -> path.append("flowers4.png");
                }
            }
            i++;
        }
        return path.toString();
    }

    @FXML
    private void showCats() {
        //cat0
        for (int i = 0; i < 2; i++) {
            String path = getCatsTilesPath(0, i);
            Polygon patternTile = makeNewHexagon(1);
            changeFill(patternTile, path);
            cat0Patterns.add(patternTile, i, 0);
        }
        //cat1
        for (int i = 0; i < 2; i++) {
            String path = getCatsTilesPath(1, i);
            Polygon patternTile = makeNewHexagon(1);
            changeFill(patternTile, path);
            cat1Patterns.add(patternTile, i, 0);
        }
        //cat2
        for (int i = 0; i < 2; i++) {
            String path = getCatsTilesPath(2, i);
            Polygon patternTile = makeNewHexagon(1);
            changeFill(patternTile, path);
            cat2Patterns.add(patternTile, i, 0);
        }
    }

    private void setButtonsBackground(Button button, String path) {
        URL url = getClass().getResource(path);
        Image img = new Image(url.toString());
        BackgroundImage backgroundImage = new BackgroundImage(
                img,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(60, 60, false, false, false, false)
        );
        Background background = new Background(backgroundImage);
        button.setBackground(background);
    }

    @FXML
    private void showButtons() {
        Button button = (Button) colorButtons.getChildren().get(0);
        setButtonsBackground(button, "/GUI/yellow/yellow.png");
        button = (Button) colorButtons.getChildren().get(1);
        setButtonsBackground(button, "/GUI/pink/pink.png");
        button = (Button) colorButtons.getChildren().get(2);
        setButtonsBackground(button, "/GUI/purple/purple.png");
        button = (Button) colorButtons.getChildren().get(3);
        setButtonsBackground(button, "/GUI/green/green.png");
        button = (Button) colorButtons.getChildren().get(4);
        setButtonsBackground(button, "/GUI/lightblue/lightBlue.png");
        button = (Button) colorButtons.getChildren().get(5);
        setButtonsBackground(button, "/GUI/darkblue/darkBlue.png");
        button = (Button) colorButtons.getChildren().get(6);
        setButtonsBackground(button, "/GUI/rainbow.png");
    }

    @FXML
    void clickDetected(MouseEvent event) {
        URL url = getClass().getResource("/GUI/goaltiles/AAABBB.png");
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
        showCats();
        showPlayersBoard((game.getPlayers())[0]);
        showButtons();
    }
}
