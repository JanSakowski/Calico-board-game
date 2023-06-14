package GUI;


import game.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
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
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.StrokeType;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    private Game game;
    @FXML
    private AnchorPane hexboard;
    @FXML
    private ImageView cat0, cat1, cat2;
    @FXML
    private GridPane onHand, cat0Patterns, cat1Patterns, cat2Patterns, table, colorButtons;
    @FXML
    private Polygon hex2_4, hex3_2, hex4_3;
    private Polygon chosenProjectTile = null;
    private Polygon chosenRegularTile = null;
    private int spectatedPlayer = 0;
    @FXML
    BorderPane border;
    @FXML
    private Button endTurn, pink, yellow, rainbow, lblue, dblue, green, purple;
    private int[] pickedProjectTiles;
    @FXML
    private Button chosenButton;
    boolean hasMoved = false, tookFromTable = false;

    //To keep track of the indexes taken from the table

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
    private void showTable() {
        if (!game.isFirstTurn()) {
            table.getChildren().clear();
            for (int i = 0; i < 3; i++) {
                Polygon polygon = makeNewHexagon(1);
                changeFill(polygon, getImagePath(game.getTilesOnTable().get(i)));
                polygon.setOnMouseClicked(this::tableTileOnClick);
                table.add(polygon, 0, i);
            }
        }
    }

    @FXML
    private void showHand(Player player) {
        if (game.isFirstTurn()) {
            if (player == game.getPlayers()[game.getCurrentPlayer()]) {
                for (int i = 0; i < 4; i++) {
                    Polygon polygon = makeNewHexagon(1);
                    changeFill(polygon, getImagePath(player.getProjectTiles()[i]));
                    polygon.setOnMouseClicked(this::handTileOnClick);
                    onHand.add(polygon, i, 0);
                }
            } else {
                onHand.getChildren().clear();
            }
        } else {
            onHand.getChildren().clear();
            if (player == game.getPlayers()[game.getCurrentPlayer()]) {
                Polygon polygon = makeNewHexagon(1);
                changeFill(polygon, getImagePath(player.getTilesOnHand().get(0)));
                polygon.setOnMouseClicked(this::handTileOnClick);
                onHand.add(polygon, 1, 0);
                if (player.getTilesOnHand().size() > 1) {
                    polygon = makeNewHexagon(1);
                    changeFill(polygon, getImagePath(player.getTilesOnHand().get(1)));
                    polygon.setOnMouseClicked(this::handTileOnClick);
                    onHand.add(polygon, 2, 0);
                }
            } else {
                Polygon polygon = makeNewHexagon(1);
                polygon.setFill(Paint.valueOf("#d7c9b7"));
                //polygon.setOnMouseClicked(this::regularTileOnClick);
                onHand.add(polygon, 1, 0);
                if (player.getTilesOnHand().size() > 1) {
                    polygon = makeNewHexagon(1);
                    polygon.setFill(Paint.valueOf("#d7c9b7"));
                    //polygon.setOnMouseClicked(this::regularTileOnClick);
                    onHand.add(polygon, 2, 0);
                }
            }
        }
    }

    @FXML
    void projectTileOnClick(MouseEvent event) {
        if (event.getTarget() instanceof Polygon) {
            chosenProjectTile = ((Polygon) event.getTarget());
        }
    }

    @FXML
    void handTileOnClick(MouseEvent event) {
        if (game.isFirstTurn()) {
            if (event.getTarget() instanceof Polygon) {
                chosenProjectTile = ((Polygon) event.getTarget());
            }
        } else {
            if (event.getTarget() instanceof Polygon) {
                chosenRegularTile = ((Polygon) event.getTarget());
            }
        }
    }
    @FXML
    void getTileFromTable(Polygon polygon){
        table.getChildren().remove(polygon);
        if (onHand.getColumnIndex((Polygon)(onHand.getChildren().get(0)))==2) {
            onHand.add(polygon,1,0);
        }
        else onHand.add(polygon,2,0);
    }
    @FXML
    void tableTileOnClick(MouseEvent event) {
        Player player = game.getPlayers()[game.getCurrentPlayer()];
        if (event.getTarget() instanceof Polygon) {
            if (spectatedPlayer == game.getCurrentPlayer()
                    && player.getTilesOnHand().size() == 1
                    && isTableFull() && hasMoved) {
                StringBuilder message = new StringBuilder();
                message.append(game.getCurrentPlayer());
                message.append(";give;");
                message.append(table.getRowIndex((Node)event.getTarget()));
                game.updateState(message.toString());
                tookFromTable = true;
                getTileFromTable((Polygon) event.getTarget());
            }

        }
    }


    @FXML
    void buttonOnClick(MouseEvent event) {
        if (event.getTarget() instanceof Button) {
            chosenButton = ((Button) event.getTarget());
            // Deselecting other tiles
            if (chosenRegularTile != null) chosenRegularTile = null;
        }
    }

    @FXML
    void showPlayersBoard(Player player) {
        showButtons(player);
        spectatedPlayer = Arrays.asList(game.getPlayers()).indexOf(player);
        //Field currentField;
        showHand(player);
        showTable();
        //Deleting the existing ImageView-s with buttons
        for (Node n :
                hexboard.getChildren()) {
            if (n instanceof ImageView)
                hexboard.getChildren().remove(n);
        }
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                Polygon hexagon = getHexagon(i, j);
                Field currentField = player.getBoard().getField(i, j);
                if (currentField.hasRegularTile()) {
                    String path = getImagePath(currentField.getRegularTile());
                    changeFill(hexagon, path);
                } else if (currentField.hasProjectTile()) {
                    String path = getImagePath(currentField.getProjectTile());
                    changeFill(hexagon, path);
                } else {
                    if (hexagon == hex2_4 || hexagon == hex3_2 || hexagon == hex4_3) {
                        hexagon.setFill(Paint.valueOf("#ffcc8e"));
                    } else hexagon.setFill(Paint.valueOf("#d7c9b7"));
                }
                if (currentField.hasButton()) {
                    addColorButton(hexagon, currentField.getRegularTile().getColor());
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

    private String getCatPath(Cat cat) {
        StringBuilder path = new StringBuilder();
        path.append("/GUI/cats/");
        switch (cat) {
            case CALLIE -> path.append("Callie.png");
            case ALMOND -> path.append("Almond.png");
            case LEO -> path.append("Leo.png");
            case TECOLOTE -> path.append("Tecolote.png");
            case CIRA -> path.append("Cira.png");
            case RUMI -> path.append("Rumi.png");
            case MILLIE -> path.append("Millie.png");
            case TIBBIT -> path.append("Tibbit.png");
            case COCONUT -> path.append("Coconut.png");
            case GWENIVERSE -> path.append("Gwen.png");
        }
        return path.toString();
    }

    private void putProjectTile(Polygon projectTile, Polygon projectTileDestination) {
        projectTileDestination.setFill(projectTile.getFill());
        onHand.getChildren().remove(projectTile);
    }

    private void putRegularTile(Polygon regularTile, Polygon regularTileDestination) {
        if (regularTileDestination.getFill().getClass() != ImagePattern.class) {
            regularTileDestination.setFill(regularTile.getFill());
            onHand.getChildren().remove(regularTile);
        } else {
            System.out.println("No");
        }
    }


    @FXML
    private void showCats() {
        //cats
        int it = 0;
        Cat[] cats = new Cat[3];
        for (Cat cat : game.getCatBoards().keySet()) {
            cats[it] = cat;
            it++;
        }
        //cat0
        URL url = getClass().getResource(getCatPath(cats[0]));
        Image img = new Image(url.toString());
        cat0.setImage(img);
        for (int i = 0; i < 2; i++) {
            String path = getCatsTilesPath(0, i);
            Polygon patternTile = makeNewHexagon(1);
            changeFill(patternTile, path);
            cat0Patterns.add(patternTile, i, 0);
        }
        //cat1
        url = getClass().getResource(getCatPath(cats[1]));
        img = new Image(url.toString());
        cat1.setImage(img);
        for (int i = 0; i < 2; i++) {
            String path = getCatsTilesPath(1, i);
            Polygon patternTile = makeNewHexagon(1);
            changeFill(patternTile, path);
            cat1Patterns.add(patternTile, i, 0);
        }
        //cat2
        url = getClass().getResource(getCatPath(cats[2]));
        img = new Image(url.toString());
        cat2.setImage(img);
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
    private void initializeButtons() {
        setButtonsBackground(yellow, "/GUI/yellow/yellow.png");
        setButtonsBackground(pink, "/GUI/pink/pink.png");
        setButtonsBackground(purple, "/GUI/purple/purple.png");
        setButtonsBackground(green, "/GUI/green/green.png");
        setButtonsBackground(lblue, "/GUI/lightblue/lightBlue.png");
        setButtonsBackground(dblue, "/GUI/darkblue/darkBlue.png");
        setButtonsBackground(rainbow, "/GUI/rainbow.png");
    }

    @FXML
    private void showButtons(Player player) {
        colorButtons.getChildren().clear();
        if (player == game.getPlayers()[game.getCurrentPlayer()]) {
            colorButtons.add(yellow, 0, 0);
            colorButtons.add(pink, 0, 1);
            colorButtons.add(purple, 0, 2);
            colorButtons.add(green, 1, 0);
            colorButtons.add(dblue, 1, 1);
            colorButtons.add(lblue, 1, 2);
            colorButtons.add(rainbow, 0, 3);
        }
    }

    @FXML
    private boolean isTableFull() {
        return table.getChildren().size() >= 3;
    }

    // TODO ZARAZ POPRAWIAM
    StringBuilder putTileMessage = new StringBuilder();
    StringBuilder giveMessage = new StringBuilder();


    @FXML
    void clickDetected(MouseEvent event) {
        if (game.isFirstTurn()) {
            if (chosenProjectTile != null) {
                if (event.getTarget() instanceof Polygon) {
                    if (event.getTarget().equals(hex2_4) && pickedProjectTiles[0] == -1) {
                        putProjectTile(chosenProjectTile, hex2_4);
                        pickedProjectTiles[0] = onHand.getColumnIndex(chosenProjectTile);
                        chosenProjectTile = null;
                    }
                    if (event.getTarget().equals(hex4_3) && pickedProjectTiles[2] == -1) {
                        putProjectTile(chosenProjectTile, hex4_3);
                        pickedProjectTiles[2] = onHand.getColumnIndex(chosenProjectTile);
                        chosenProjectTile = null;
                    }
                    if (event.getTarget().equals(hex3_2) && pickedProjectTiles[1] == -1) {
                        putProjectTile(chosenProjectTile, hex3_2);
                        pickedProjectTiles[1] = onHand.getColumnIndex(chosenProjectTile);
                        chosenProjectTile = null;
                    }
                }
            }
        } else {
            Polygon chosenField = (Polygon) event.getTarget();
            if (chosenRegularTile != null
                    && onHand.getChildren().size() == 2 && !hasMoved) {
                int index = onHand.getChildren().indexOf(chosenRegularTile);
                int[] coordinates = getCoordinates(chosenField);
                if (game.getPlayers()[game.getCurrentPlayer()].putTile(index, coordinates[0], coordinates[1])) {
                    System.out.println("Placing the tile");
                    putRegularTile(chosenRegularTile, chosenField);
                }
                hasMoved = true;

                // To prevent left-over value from disturbing the program
                chosenRegularTile = null;
            }

            // Setting up the button
            if (chosenButton != null) {
                System.out.println("Choosing button");
                int[] coordinates = getCoordinates(chosenField);

                // Putting the button on the tile
                if (game.getPlayers()[game.getCurrentPlayer()].putColorButton(coordinates[0], coordinates[1])) {
                    System.out.println("Button chosen. Placing");
                    addColorButton(chosenField, game.getPlayers()[game.getCurrentPlayer()].getBoard().getField(coordinates[0], coordinates[1]).getRegularTile().getColor());
                    //chosenField.setFill(chosenButton.getTextFill());
                    // To prevent left-over value from disturbing the program
                    chosenButton = null;
                }

            }
        }
    }

    /**
     * Method for adding color buttons. Used when showing player's board and placing the buttons
     *
     * @param polygon
     */
    public void addColorButton(Polygon polygon, game.Color color) {
        StringBuilder imagePath = new StringBuilder();
        imagePath.append("/GUI/");
        System.out.println("Before Switch");
        switch(color) {
            case GREEN -> imagePath.append("green/green");
            case YELLOW -> imagePath.append("yellow/yellow");
            case PURPLE -> imagePath.append("purple/purple");
            case MAGENTA -> imagePath.append("pink/pink");
            case LIGHT_BLUE -> imagePath.append("lightblue/lightblue");
            case DARK_BLUE -> imagePath.append("darkblue/darkblue");
        }
        imagePath.append(".png");
        System.out.println("After Switch " + imagePath.toString());
        Image buttonImage = new Image(imagePath.toString());
        ImageView buttonImageView = new ImageView(buttonImage);

        buttonImageView.xProperty().bind(polygon.layoutXProperty().add(50));
        buttonImageView.yProperty().bind(polygon.layoutYProperty().add(25));

        buttonImageView.setFitWidth(50);
        buttonImageView.setFitHeight(50);


        // Adding the button to the AnchorPane
        hexboard.getChildren().add(buttonImageView);
    }

    public int[] getCoordinates(Polygon p) {
        int[] result = new int[2];
        String polygon = p.toString();
        //Temporary
        result[0] = Integer.parseInt(p.toString().substring(14, 15));
        result[1] = Integer.parseInt(p.toString().substring(16, 17));
        System.out.println("Chosen field: " + result[0] + " " + result[1]);
        return result;
    }


    @FXML
    public void endingTurn(ActionEvent e) {
        if (game.isFirstTurn()) {
            if (pickedProjectTiles[0] == 0 && pickedProjectTiles[1] == 0 && pickedProjectTiles[2] == 0)
                return;
            StringBuilder message = new StringBuilder();
            message.append(game.getCurrentPlayer());
            message.append(";project;");
            message.append(pickedProjectTiles[0]);
            message.append(";");
            message.append(pickedProjectTiles[1]);
            message.append(";");
            message.append(pickedProjectTiles[2]);
            game.updateState(message.toString());
            pickedProjectTiles = new int[3];
            for (int i = 0; i < 3; i++) pickedProjectTiles[i] = -1;
            onHand.getChildren().clear();
            showPlayersBoard(game.getPlayers()[game.getCurrentPlayer()]);
        } else {
            StringBuilder message = new StringBuilder();
            message.append(game.getCurrentPlayer());
            message.append(";end");
            System.out.println("endmessage");
            System.out.println(table.getChildren());
            if (!isTableFull()) {
                System.out.println("table not full");
                //        game.updateState(putTileMessage.toString());
                //        game.updateState(giveMessage.toString());
                game.updateState(message.toString());
                showPlayersBoard(game.getPlayers()[game.getCurrentPlayer()]);
                putTileMessage.setLength(0);
                giveMessage.setLength(0);
                chosenRegularTile = null;
                hasMoved = false;
            }

        }

    }

    private void changePlayerLeft() {
        spectatedPlayer = Integer.remainderUnsigned(spectatedPlayer - 1, game.getPlayers().length);
        showPlayersBoard(game.getPlayers()[spectatedPlayer]);
    }

    private void changePlayerRight() {
        spectatedPlayer = Integer.remainderUnsigned(spectatedPlayer + 1, game.getPlayers().length);
        showPlayersBoard(game.getPlayers()[spectatedPlayer]);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        GameDataMonostate data = new GameDataMonostate();
        game = new Game(2);
        //game = new Game(data.getNumberOfPlayers());
        showCats();
        initializeButtons();
        showPlayersBoard(game.getPlayers()[game.getCurrentPlayer()]);
        pickedProjectTiles = new int[3];
        spectatedPlayer = game.getCurrentPlayer();
        for (int i = 0; i < 3; i++) pickedProjectTiles[i] = -1;
        game.updateState("0;project;0;1;2");
        game.updateState("1;project;0;1;3");
        Platform.runLater(() -> border.getScene().setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case D -> changePlayerRight();
                case A -> changePlayerLeft();
            }
        }));
    }
}
