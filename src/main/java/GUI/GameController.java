package GUI;


import gamepackage.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.StrokeType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.SortedMap;

/**
 * GameController class is responsible for all the GUI logic within the game scene;
 * Controller class for game.fxml file
 */
public class GameController implements Initializable {
    /**
     * Game instance with the current game content
     */
    private Game game;

    @FXML
    private Label playerInfo, actionInfo;
    @FXML
    private AnchorPane hexboard;
    @FXML
    private ImageView cat0, cat1, cat2;
    @FXML
    private GridPane onHand, cat0Patterns, cat1Patterns, cat2Patterns, table, colorButtons;
    @FXML
    private Polygon hex2_4, hex3_2, hex4_3;
    @FXML
    private Button chosenButton;
    @FXML
    private Button endTurn, pink, yellow, rainbow, lblue, dblue, green, purple;
    private Polygon chosenProjectTile = null;
    private Polygon chosenRegularTile = null;
    private int spectatedPlayer = 0;
    /**
     * The Names of currently playing players.
     */
    String[] names = {"a", "b", "c", "d"};

    private int[] pickedProjectTiles;

    private gamepackage.Color chosenButtonColor;
    private CatBoard chosenCat;
     // Storing the location of rainbow buttons - has to by this way due to a inherent Game class characteristics
    private Polygon[] rainbowButtons;
    /**
     * The boolean indicating whether the player put tile on the quile
     */
    boolean hasMoved = false,
    /**
     * The boolean indicating whether the player took tile from the table
     */
    tookFromTable = false;

    /**
     * Returns the hexagon from gameboard, that has given coordinates
     * @param x x coordinate
     * @param y y coordinate
     * @return wanted hexagon from gameboard
     */
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

    /**
     * Makes and returns new hexagon Polygon;
     * big hexagon for 1 size option, smaller otherwise
     * @param size the size of wanted hexagon
     * @return created hexagon
     */
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

    /**
     * Returns path for background image for given tile
     * @param tile given tile
     * @return image path for given tile
     */
    private String getImagePath(Tile tile) {
        if (tile instanceof ProjectTile)
            return getProjectImagePath((ProjectTile) tile);
        else
            return getRegularImagePath((RegularTile) tile);
    }

    /**
     * Returns path for background image for given project tile
     * @param pTile given project tile
     * @return image path for given project tile
     */
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

    /**
     * Returns path for background image for given regular tile
     * @param rTile given regular tile
     * @return image path for given regular tile
     */
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

    /**
     * Shows tiles, that are currently on the table
     */
    @FXML
    private void showTable() {
        if (!tookFromTable){
            if (!game.isFirstTurn()) {
                table.getChildren().clear();
                for (int i = 0; i < 3; i++) {
                    Polygon polygon = makeNewHexagon(1);
                    changeFill(polygon, getImagePath(game.getTilesOnTable().get(i)));
                    polygon.setOnMouseClicked(this::tableTileOnClick);
                    table.add(polygon, 0, i);
                    table.setHalignment(polygon, HPos.CENTER);
                    table.setValignment(polygon, VPos.CENTER);
                }
            }
        }
    }

    /**
     * Shows tiles that are currently on player's hand
     * @param player player that hand is being shown for
     */
    @FXML
    private void showHand(Player player) {
        //project tiles
        if (game.isFirstTurn()) {
            if (player == game.getPlayers()[game.getCurrentPlayer()]) {
                for (int i = 0; i < 4; i++) {
                    Polygon polygon = makeNewHexagon(1);
                    changeFill(polygon, getImagePath(player.getProjectTiles()[i]));
                    polygon.setOnMouseClicked(this::handTileOnClick);
                    returnOnHand(polygon, i);
                }
            } else {
                onHand.getChildren().clear();
            }

        } else {
            //tiles of current player
            onHand.getChildren().clear();
            if (player == game.getPlayers()[game.getCurrentPlayer()]) {
                Polygon polygon = makeNewHexagon(1);
                changeFill(polygon, getImagePath(player.getTilesOnHand().get(0)));
                polygon.setOnMouseClicked(this::handTileOnClick);
                returnOnHand(polygon, 1);
                if (player.getTilesOnHand().size() > 1) {
                    polygon = makeNewHexagon(1);
                    changeFill(polygon, getImagePath(player.getTilesOnHand().get(1)));
                    polygon.setOnMouseClicked(this::handTileOnClick);
                    returnOnHand(polygon, 2);
                }
            } else {
                //tiles spectated player
                Polygon polygon = makeNewHexagon(1);
                polygon.setFill(Paint.valueOf("#d7c9b7"));
                //polygon.setOnMouseClicked(this::regularTileOnClick);
                returnOnHand(polygon, 1);
                if (player.getTilesOnHand().size() > 1) {
                    polygon = makeNewHexagon(1);
                    polygon.setFill(Paint.valueOf("#d7c9b7"));
                    //polygon.setOnMouseClicked(this::regularTileOnClick);
                    returnOnHand(polygon, 2);
                }
            }
        }

    }

    /**
     * Saves the game to a chosen file and returns to the welcome screen
     */
    @FXML
    void save() {
        /*String path;
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File selectedFile = fileChooser.showSaveDialog((Stage)cat0.getScene().getWindow());
        if (selectedFile != null) {
            path = selectedFile.getPath();

        }
        Stage stage = (Stage) hexboard.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("welcomescreen.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene (root);
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();*/
    }

    /**
     * Used for making the project tile performing action on click
     *
     * @param event click event
     */
    @FXML
    void projectTileOnClick(MouseEvent event) {
        if (event.getTarget() instanceof Polygon) {
            chosenProjectTile = ((Polygon) event.getTarget());
        }
    }

    /**
     * Used for making the tile on hand performing action on click
     *
     * @param event click event
     */
    @FXML
    void handTileOnClick(MouseEvent event) {
        chosenButton = null;
        chosenCat = null;
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
    /**
     * Used for making the tile on the table performing action on click
     *
     * @param event click event
     */
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
                message.append(table.getRowIndex((Node) event.getTarget()));
                game.updateState(message.toString());
                tookFromTable = true;
                getTileFromTable((Polygon) event.getTarget());
                actionInfo.setText("Put a button on your quilt or end turn");
            }

        }
    }

    /**
     * Gets chosen by player tile from the table
     *
     * @param polygon chosen polygon from the table
     */
    @FXML
    void getTileFromTable(Polygon polygon) {
        table.getChildren().remove(polygon);
        if (onHand.getColumnIndex((Polygon) onHand.getChildren().get(0)) == 2) {
            onHand.add(polygon, 1, 0);
        } else onHand.add(polygon, 2, 0);
    }




    /**
     * When activated, sets the current chosen color to one from gamepackage.Color, based on the button clicked
     *
     * @param event the event
     */
    @FXML
    void colorButtonTrigger(MouseEvent event) {
        if (event.getTarget() instanceof Button && tookFromTable == true) {
            chosenCat = null;
            chosenRegularTile = null;
            chosenButton = ((Button) event.getTarget());

            String fxid = chosenButton.getId();
            switch (fxid) {
                case "yellow" -> chosenButtonColor = gamepackage.Color.YELLOW;
                case "dblue" -> chosenButtonColor = gamepackage.Color.DARK_BLUE;
                case "lblue" -> chosenButtonColor = gamepackage.Color.LIGHT_BLUE;
                case "green" -> chosenButtonColor = gamepackage.Color.GREEN;
                case "purple" -> chosenButtonColor = gamepackage.Color.PURPLE;
                case "pink" -> chosenButtonColor = gamepackage.Color.MAGENTA;
                case "rainbow" -> chosenButtonColor = gamepackage.Color.RAINBOW;
            }
        }
    }

    /**
     * When activated, sets the current chosen cat as waiting to be placed
     *
     * @param event the event
     */
    @FXML
    void catButtonTrigger(MouseEvent event) {
        if (event.getTarget() instanceof ImageView && chosenCat == null && tookFromTable == true) { // So you can't choose another one
            // Clearing other chosen tiles
            chosenRegularTile = null;
            chosenButton = null;
            chosenButtonColor = null;
            int catIndex = Integer.parseInt(event.getTarget().toString().substring(16, 17));
            int it = 0;
            Cat[] cats = new Cat[3];
            for (Cat cat : game.getCatBoards().keySet()) {
                cats[it] = cat;
                it++;
            }
            chosenCat = game.getCatBoards().get(cats[catIndex]);
        }
    }

    /**
     * Sets the label content, responsible for displaying info about player
     * @param player currently shown player
     */

    private void setPlayerInfo(Player player) {
        StringBuilder label = new StringBuilder();
        if (player == game.getPlayers()[game.getCurrentPlayer()]) {
            label.append("Your board");
        } else {
            label.append("You're spectating ");
            label.append(names[spectatedPlayer]);
            label.append("'s board");
        }
        playerInfo.setText(label.toString());
    }

    /**
     * Shows given player's board
     *
     * @param player given player
     */
    @FXML
    void showPlayersBoard(Player player) {
        if (game.isFirstTurn()) {
            actionInfo.setText("Put project tiles on your quilt");
        } else actionInfo.setText("Put a tile on your quilt");
        if (hasMoved) actionInfo.setText("Choose a tile from the table");
        if (hasMoved && tookFromTable) actionInfo.setText("Put a button on your quilt or end turn");
        showButtons(player);
        spectatedPlayer = Arrays.asList(game.getPlayers()).indexOf(player);
        setPlayerInfo(player);
        showHand(player);
        showTable();
        //Deleting the existing ImageView-s with buttons
        int childrenLength = hexboard.getChildren().size();
        for (int i = childrenLength - 1; i >= 0; i--) {
            if (hexboard.getChildren().get(i) instanceof ImageView) {
                hexboard.getChildren().remove(i);
            }
        }
        if (game.getPlayers()[game.getCurrentPlayer()].getBoard().isFull()) {
            gameEnd();
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
                if (currentField.hasCatButton()) {
                    addCatButton(hexagon, currentField.getCatButton().getCat());
                } else if (currentField.hasColorButton() && currentField.getColorButton().getColor() != gamepackage.Color.RAINBOW) {
                    addColorButton(hexagon, currentField.getColorButton().getColor());
                } else if (rainbowButtons[game.getCurrentPlayer()] == hexagon) {
                    addRainbowButton(hexagon);
                }
            }
        }
    }

    /**
     * Ends the game and displays GameEnd scene
     */
    public void gameEnd() {
        int[] scores = new int[game.getPlayers().length];
        for (int i = 0; i < game.getPlayers().length; i++) {
            scores[i] = game.getPlayers()[i].getScore();
        }


        GameDataSingleton.getInstance().setGame(game);

        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Game over!");
        a.setHeaderText("The last player has finished their quilt!");
        a.setContentText("Let's sum up your points!");
        a.showAndWait();


        try {
            Stage stage = (Stage) hexboard.getScene().getWindow();
            stage.setTitle("Summary");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("scoreboard.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);

        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.out.println("Error loading the scene");
        }
    }

    /**
     * Changes fill of given hexagon to image with given path
     * @param hexagon given hexagon
     * @param path given image path
     */
    private void changeFill(Polygon hexagon, String path) {
        URL url = getClass().getResource(path);
        Image img = new Image(url.toString());
        hexagon.setFill(new ImagePattern(img));
    }

    /**
     * Returns pattern tile path for given cat and given number;
     * every cat has two pattern tiles so whichTile is either 1 or 2
     * @param whichCat given cat
     * @param whichTile given number
     * @return image path depending on given variables
     */
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

    /**
     * Returns image path for given cat
     * @param cat given cat
     * @return image path for that cat
     */
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

    /**
     * Puts project tile on the table;
     * used when clicked place for project tile is empty
     * @param projectTile project tile to put on the table
     * @param projectTileDestination place for project tile
     */
    private void putProjectTile(Polygon projectTile, Polygon projectTileDestination) {
        projectTileDestination.setFill(projectTile.getFill());
        onHand.getChildren().remove(projectTile);
    }

    /**
     * Switches two project tiles;
     * used when place for project tile isn't empty and player has picked another project tile to put
     * @param projectTile project tile to put on the table
     * @param projectTileDestination place for project tile
     * @return project tile that is being switched
     */
    private Polygon switchProjectTiles(Polygon projectTile, Polygon projectTileDestination) {
        Polygon polygon = makeNewHexagon(1);
        polygon.setFill(projectTileDestination.getFill());
        putProjectTile(projectTile, projectTileDestination);
        polygon.setOnMouseClicked(this::projectTileOnClick);
        return polygon;
    }

    /**
     * puts regular tile on the board
     * @param regularTile tile to put on the board
     * @param regularTileDestination place to put tile on
     */
    private void putRegularTile(Polygon regularTile, Polygon regularTileDestination) {
        if (regularTileDestination.getFill().getClass() != ImagePattern.class) {
            regularTileDestination.setFill(regularTile.getFill());
            onHand.getChildren().remove(regularTile);
        } else {
            System.out.println("No");
        }
    }

    /**
     * Displays cats and cats' pattern tiles
     */
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
            cat0Patterns.setHalignment(patternTile, HPos.CENTER);
            cat0Patterns.setValignment(patternTile, VPos.CENTER);
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
            cat1Patterns.setHalignment(patternTile, HPos.CENTER);
            cat1Patterns.setValignment(patternTile, VPos.CENTER);
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
            cat2Patterns.setHalignment(patternTile, HPos.CENTER);
            cat2Patterns.setValignment(patternTile, VPos.CENTER);
        }
    }

    /**
     * Makes FX button look like colour button
     * @param button FX button
     * @param path image path to wanted background image
     */
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

    /**
     * Initializes buttons' appearance
     */
    @FXML
    private void initializeButtons() {
        colorButtons.setAlignment(Pos.CENTER);
        setButtonsBackground(yellow, "/GUI/yellow/yellow.png");
        setButtonsBackground(pink, "/GUI/pink/pink.png");
        setButtonsBackground(purple, "/GUI/purple/purple.png");
        setButtonsBackground(green, "/GUI/green/green.png");
        setButtonsBackground(lblue, "/GUI/lightblue/lightBlue.png");
        setButtonsBackground(dblue, "/GUI/darkblue/darkBlue.png");
        setButtonsBackground(rainbow, "/GUI/rainbow.png");
    }

    /**
     * Shows buttons, if given player is currentPlayer
     * @param player given player
     */
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
        } else actionInfo.setText("");
    }

    /**
     * Checks whether the table is full
     * @return true, if table is full, false otherwise
     */
    @FXML
    private boolean isTableFull() {
        return table.getChildren().size() >= 3;
    }


    /**
     * Used for returning put project tile back on hand
     * @param polygon project tile
     * @param column place on hand, it should be in
     */
    private void returnOnHand(Polygon polygon, int column) {
        onHand.add(polygon, column, 0);
        onHand.setHalignment(polygon, HPos.CENTER);
        onHand.setValignment(polygon, VPos.CENTER);
    }

    /**
     * Makes tiles on the board responsive to clicking
     *
     * @param event click event
     */
    @FXML
    void clickDetected(MouseEvent event) {
        //projectTiles actions
        if (game.isFirstTurn()) {
            Polygon picked = null;
            int pickedIndex = 0;
            if (event.getTarget() instanceof Polygon) {
                Polygon target = ((Polygon) event.getTarget());
                if (!(target.equals(hex2_4) || target.equals(hex4_3) || target.equals(hex3_2))) return;
                switch (target.getId()) {
                    case "hex2_4" -> {
                        picked = hex2_4;
                        pickedIndex = 0;
                    }
                    case "hex4_3" -> {
                        picked = hex4_3;
                        pickedIndex = 2;
                    }
                    case "hex3_2" -> {
                        picked = hex3_2;
                        pickedIndex = 1;
                    }
                }
                if (chosenProjectTile != null) {
                    //put a tile
                    if (pickedProjectTiles[pickedIndex] == -1) {
                        putProjectTile(chosenProjectTile, picked);
                        pickedProjectTiles[pickedIndex] = onHand.getColumnIndex(chosenProjectTile);
                        chosenProjectTile = null;
                    } else {
                        //switch project tiles
                        int returnIndex = pickedProjectTiles[pickedIndex];
                        pickedProjectTiles[pickedIndex] = onHand.getColumnIndex(chosenProjectTile);
                        returnOnHand(switchProjectTiles(chosenProjectTile, picked), returnIndex);
                        chosenProjectTile = null;
                    }
                } else {
                    //pop project tile from table
                    if (pickedProjectTiles[pickedIndex] == -1) return;
                    Polygon polygon = makeNewHexagon(1);
                    polygon.setFill(picked.getFill());
                    picked.setFill(Paint.valueOf("#ffcc8e"));
                    returnOnHand(polygon, pickedProjectTiles[pickedIndex]);
                    pickedProjectTiles[pickedIndex] = -1;
                    polygon.setOnMouseClicked(this::handTileOnClick);
                    chosenProjectTile = null;
                    actionInfo.setText("Put project tiles on your quilt");
                }
            }
            if (!(pickedProjectTiles[0] == -1 || pickedProjectTiles[1] == -1 || pickedProjectTiles[2] == -1))
                actionInfo.setText("End turn");
        } else {
            //regularTiles actions
            Polygon chosenField = (Polygon) event.getTarget();
            int[] coordinates = getCoordinates(chosenField);
            if (chosenRegularTile != null
                    && onHand.getChildren().size() == 2 && !hasMoved) {
                int index = onHand.getChildren().indexOf(chosenRegularTile);
                if (game.getPlayers()[game.getCurrentPlayer()].putTile(index, coordinates[0], coordinates[1])) {
                    putRegularTile(chosenRegularTile, chosenField);
                    hasMoved = true;
                }
                actionInfo.setText("Choose a tile from the table");
                // To prevent left-over value from disturbing the program
                chosenRegularTile = null;
            }

            if (game.getPlayers()[game.getCurrentPlayer()].getBoard().getField(coordinates[0], coordinates[1]).hasTile()){
                if (chosenButtonColor == gamepackage.Color.RAINBOW) {
                    if (game.getPlayers()[game.getCurrentPlayer()].getBoard().putRainbowButton(coordinates[0], coordinates[1])) {
                        rainbowButtons[game.getCurrentPlayer()] = chosenField;
                        addRainbowButton(chosenField);
                        chosenButton = null;
                        chosenButtonColor = null;
                    }
                }

                // When a button is chosen and the designated field contains a tile
                if (chosenButton != null && game.getPlayers()[game.getCurrentPlayer()].getBoard().getField(coordinates[0], coordinates[1]).getRegularTile().getColor() != null) {
                    // When the chosen color matches the color of field's tile
                    if (chosenButtonColor == game.getPlayers()[game.getCurrentPlayer()].getBoard().getField(coordinates[0], coordinates[1]).getRegularTile().getColor()) {
                        if (game.getPlayers()[game.getCurrentPlayer()].putColorButton(coordinates[0], coordinates[1])) {
                            addColorButton(chosenField, chosenButtonColor);
                            // To prevent left-over value from disturbing the program
                            chosenButton = null;
                            chosenButtonColor = null;
                        }
                    } else {
                        System.out.println("Wrong color");
                        chosenButtonColor = null;
                        chosenButton = null;
                    }
                } else {
                    chosenButtonColor = null;
                    chosenButton = null;
                }

                // setting up the cat button
                if (chosenCat != null) {
                    if (game.getPlayers()[game.getCurrentPlayer()].putCatButton(chosenCat, coordinates[0], coordinates[1])) {
                        addCatButton(chosenField, chosenCat.getCat());
                        chosenCat = null;
                    } else {
                        chosenCat = null;
                    }
                }
            }
        }
    }

    /**
     * Method for adding color buttons. Used when showing player's board and placing the buttons
     *
     * @param polygon the polygon
     * @param color   the color
     */
    public void addColorButton(Polygon polygon, gamepackage.Color color) {
        if (rainbowButtons[game.getCurrentPlayer()] != polygon){
            StringBuilder imagePath = new StringBuilder();
            imagePath.append("/GUI/");
            switch (color) {
                case GREEN -> imagePath.append("green/green");
                case YELLOW -> imagePath.append("yellow/yellow");
                case PURPLE -> imagePath.append("purple/purple");
                case MAGENTA -> imagePath.append("pink/pink");
                case LIGHT_BLUE -> imagePath.append("lightblue/lightblue");
                case DARK_BLUE -> imagePath.append("darkblue/darkblue");
            }
            imagePath.append(".png");
            URL url = getClass().getResource(imagePath.toString());
            Image buttonImage = new Image(url.toString());

            ImageView buttonImageView = new ImageView(buttonImage);

            buttonImageView.xProperty().bind(polygon.layoutXProperty().add(-90));
            buttonImageView.yProperty().bind(polygon.layoutYProperty().add(-50));
            chosenButton = null;
            // Adding the button to the AnchorPane
            hexboard.getChildren().add(buttonImageView);
        }
    }

    public void addRainbowButton(Polygon polygon) {
        URL url = getClass().getResource("/GUI/rainbow.png");
        Image buttonImage = new Image(url.toString());
        ImageView buttonImageView = new ImageView(buttonImage);
        buttonImageView.xProperty().bind(polygon.layoutXProperty().add(-90));
        buttonImageView.yProperty().bind(polygon.layoutYProperty().add(-50));
        chosenButton = null;
        // Adding the button to the AnchorPane
        hexboard.getChildren().add(buttonImageView);
    }

    /**
     * Adds cat button on given polygon
     *
     * @param targetPolygon the target polygon
     * @param cat           the cat
     */
    public void addCatButton(Polygon targetPolygon, Cat cat) {
        StringBuilder imagePath = new StringBuilder();
        imagePath.append("/GUI/cats/");
        switch (cat) {
            case MILLIE -> imagePath.append("Millie");
            case TIBBIT -> imagePath.append("Tibbit");
            case COCONUT -> imagePath.append("Coconut");
            case CIRA -> imagePath.append("Cira");
            case GWENIVERSE -> imagePath.append("Gwen");
            case CALLIE -> imagePath.append("Callie");
            case RUMI -> imagePath.append("Rumi");
            case TECOLOTE -> imagePath.append("Tecolote");
            case ALMOND -> imagePath.append("Almond");
            case LEO -> imagePath.append("Leo");
        }
        imagePath.append("Button.png");
        URL url = getClass().getResource(imagePath.toString());
        Image buttonImage = new Image(url.toString());
        ImageView buttonImageView = new ImageView(buttonImage);

        buttonImageView.xProperty().bind(targetPolygon.layoutXProperty().add(-190));
        buttonImageView.yProperty().bind(targetPolygon.layoutYProperty().add(-80));
        buttonImageView.setScaleX(0.4);
        buttonImageView.setScaleY(0.4);
        chosenCat = null;
        hexboard.getChildren().add(buttonImageView);
    }

    /**
     * Gets coordinates of given polygon
     *
     * @param p given polygon
     * @return int array with coordinates
     */
    public int[] getCoordinates(Polygon p) {
        int[] result = new int[2];
        String polygon = p.toString();
        //Temporary
        result[0] = Integer.parseInt(p.toString().substring(14, 15));
        result[1] = Integer.parseInt(p.toString().substring(16, 17));
        return result;
    }


    /**
     * Makes endTurn button responsive to click
     *
     * @param e the click event
     */
    @FXML
    public void endingTurn(ActionEvent e) {
        if (spectatedPlayer == game.getCurrentPlayer()){
            if (game.isFirstTurn()) {
                if (pickedProjectTiles[0] == -1 || pickedProjectTiles[1] == -1 || pickedProjectTiles[2] == -1)
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
                tookFromTable = false;
                StringBuilder message = new StringBuilder();
                message.append(game.getCurrentPlayer());
                message.append(";end");
                if (!isTableFull()) {
                    game.updateState(message.toString());
                    hasMoved = false;
                    tookFromTable = false;
                    showPlayersBoard(game.getPlayers()[game.getCurrentPlayer()]);

                    chosenRegularTile = null;

                    chosenCat = null;
                }

            }
        }
    }

    /**
     * Show board of the player "on the left"
     */
    @FXML
    private void changePlayerLeft() {
        if (!game.isFirstTurn()) {
            spectatedPlayer = Integer.remainderUnsigned(spectatedPlayer - 1, game.getPlayers().length);
            showPlayersBoard(game.getPlayers()[spectatedPlayer]);
        }
    }
    /**
     * Show board of the player "on the right"
     */
    @FXML
    private void changePlayerRight() {
        if (!game.isFirstTurn()) {
            spectatedPlayer = Integer.remainderUnsigned(spectatedPlayer + 1, game.getPlayers().length);
            showPlayersBoard(game.getPlayers()[spectatedPlayer]);
        }
    }

    /**
     * Goes back to current player's board
     */
    @FXML
    private void showCurrentPlayerBoard() {
        if (!game.isFirstTurn()) {
            spectatedPlayer = game.getCurrentPlayer();
            showPlayersBoard(game.getPlayers()[spectatedPlayer]);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int players = GameDataSingleton.getInstance().getNumberOfPlayers();
        game = new Game(players);
        for (int i = 0; i < players; i++) {
            names[i] = GameDataSingleton.getInstance().getPlayerNames()[i];
            System.out.println(names[i]);
        }
        //game = new Game(data.getNumberOfPlayers());
        showCats();
        initializeButtons();
        rainbowButtons = new Polygon[game.getPlayers().length];
        showPlayersBoard(game.getPlayers()[game.getCurrentPlayer()]);
        pickedProjectTiles = new int[3];
        spectatedPlayer = game.getCurrentPlayer();
        for (int i = 0; i < 3; i++) pickedProjectTiles[i] = -1;
        //game.updateState("0;project;0;1;2");
        //game.updateState("1;project;0;1;3");
        Platform.runLater(() -> cat0.getScene().setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case D -> changePlayerRight();
                case A -> changePlayerLeft();
            }
        }));
    }
}