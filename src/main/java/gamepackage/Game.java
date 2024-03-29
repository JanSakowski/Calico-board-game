package gamepackage;

import java.io.*;
import java.util.*;

public class Game implements Serializable {
    private final Player[] players;
    private final Stack<RegularTile> regularTilesLeft;
    private final EnumMap<Cat, CatBoard> catBoards;
    private final ArrayList<RegularTile> tilesOnTable;
    private int currentPlayer;
    private boolean firstTurn;

    /**
     * Players playing the game
     */
    public Player[] getPlayers() {
        return players;
    }

    /**
     * Shuffled tiles left
     */
    public Stack<RegularTile> getRegularTilesLeft() {
        return regularTilesLeft;
    }

    /**
     * game.Cat boards in game
     */
    public EnumMap<Cat, CatBoard> getCatBoards() {
        return catBoards;
    }

    /**
     * Current tiles on tablle
     */
    public ArrayList<RegularTile> getTilesOnTable() {
        return tilesOnTable;
    }

    /**
     * Index of the player whose turn it is
     */
    public int getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * If the game is in it's first stage where players put their project tiles
     */
    public boolean isFirstTurn() {
        return firstTurn;
    }

    /**
     * Starts game with a given number of players, initializes the boards, cat boards, shuffles regular tiles
     *
     * @param numberOfPlayers number of players
     */
    public Game(int numberOfPlayers) {
        Random random = new Random();
        regularTilesLeft = RegularTile.getAllTilesShuffled(random);
        catBoards = CatBoard.GetRandom(random);
        players = new Player[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++)
            players[i] = new Player(i, Board.getRandom(random), ProjectTile.getPlayersRandom(random));
        firstTurn = true;
        tilesOnTable = new ArrayList<>();

        currentPlayer = 0;
    }

    /**
     * Updates state of the game
     *
     * @param message Each message starts with a player index, followed by ; and then:
     *                "project;a;b;c" - places project tiles - index a on the left, index b in the middle, index c on
     *                the right
     *                "give;a" - gives a tile to player where a is an index from tilesOnTable
     *                "put_tile;a;x;y" - puts a tile on players board where a is an index from player's tiles, x and y
     *                is the position of the tile
     *                "put_color;x;y" - puts color button at a given position
     *                "put_cat;name;x;y" - puts given cat at a given position
     *                "put_rainbow;x;y" - puts rainbow button at a given position
     *                "end" - ends players turn
     * @throws IllegalArgumentException when message is incorrect
     */
    public void updateState(String message) throws IllegalArgumentException {
        String[] split = message.split(";");
        if (split.length < 2)
            throw new IllegalArgumentException("Expected message type");
        int message_index = Integer.parseInt(split[0]);
        if (message_index != currentPlayer)
            return;
        if (split[1].equals("project")) {
            if (!firstTurn)
                throw new IllegalArgumentException("Cannot set up project tiles after the game has begun");
            if (split.length != 5)
                throw new IllegalArgumentException("Expected integers for left index, center index and right index");
            int left = Integer.parseInt(split[2]);
            int center = Integer.parseInt(split[3]);
            int right = Integer.parseInt(split[4]);

            if (players[currentPlayer].setProjectTiles(left, center, right)) {
                players[currentPlayer].addTile(regularTilesLeft.pop());
                players[currentPlayer].addTile(regularTilesLeft.pop());
                currentPlayer++;
                if (currentPlayer == players.length) {
                    currentPlayer = 0;
                    firstTurn = false;
                    for (int i = 0; i < 3; i++)
                        tilesOnTable.add(regularTilesLeft.pop());
                }
            }
        } else {
            if (firstTurn)
                throw new IllegalArgumentException("Expected project tiles set up message during the first turn");
            switch (split[1]) {
                case "give" -> {
                    if (split.length != 3)
                        throw new IllegalArgumentException("Expected integer for index");
                    int index = Integer.parseInt(split[2]);
                    if (index < 0 || index > 3) {
                        System.out.println("index problem");
                        return;
                    }
                    if (players[currentPlayer].addTile(tilesOnTable.get(index))) {
                        tilesOnTable.remove(index);
                        tilesOnTable.add(regularTilesLeft.pop());
                    }
                }
                case "put_tile" -> {
                    if (split.length != 5)
                        throw new IllegalArgumentException("Expected integers for index, x and y");
                    int index = Integer.parseInt(split[2]);
                    int x = Integer.parseInt(split[3]);
                    int y = Integer.parseInt(split[4]);
                    players[currentPlayer].putTile(index, x, y);
                }
                case "put_color" -> {
                    if (split.length != 4)
                        throw new IllegalArgumentException("Expected integers for x and y");
                    int x = Integer.parseInt(split[2]);
                    int y = Integer.parseInt(split[3]);
                    boolean flag = players[currentPlayer].putColorButton(x, y);
                }
                case "put_cat" -> {
                    if (split.length != 5)
                        throw new IllegalArgumentException("Expected string for cat and integers for x and y");
                    Cat cat = Cat.valueOf(split[2]);
                    if (!catBoards.containsKey(cat))
                        throw new IllegalArgumentException("Expected cat used in game");
                    int x = Integer.parseInt(split[3]);
                    int y = Integer.parseInt(split[4]);
                    players[currentPlayer].putCatButton(catBoards.get(cat), x, y);
                }
                case "put_rainbow" -> {
                    if (split.length != 4)
                        throw new IllegalArgumentException("Expected integers for x and y");
                    int x = Integer.parseInt(split[2]);
                    int y = Integer.parseInt(split[3]);
                    players[currentPlayer].putRainbowButton(x, y);
                }
                case "end" -> {
                    currentPlayer++;
                    if (currentPlayer == players.length)
                        currentPlayer = 0;
                }
                default -> throw new IllegalArgumentException("Incorrect message type");
            }
        }
    }

    /**
     * Saves a game to file
     *
     * @param path path of the file
     */
    public void save(String path) {
        try (FileOutputStream fileOut = new FileOutputStream(path);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(this);
            System.out.println("Zapisano");
        } catch (IOException e) {
            System.out.println("Wystąpił błąd podczas zapisu");
            e.printStackTrace();
        }
    }

    /**
     * Loads a game from file
     *
     * @param path path of the file
     * @return loaded game
     */
    public static Game loadState(String path) {
        try (FileInputStream fileIn = new FileInputStream(path);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            Object object = objectIn.readObject();
            return (Game) object;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Wystąpił błąd podczas ładowania");
            e.printStackTrace();
            return null;
        }
    }

    public void saveToJSON(String filePath) {
        /*
        Keywords used in JSON file, their explanation and format (all in Strings):
        NuPlayers       -> Number of players                        NuPlayers:int
        CurrentPlayer   -> self-explanatory                         CurrentPlayer:int
        /no keyword/    -> What field [x,y] contains                int;int;int:Pattern;Color;Color;Cat                                   (player number;position x;position y:Pattern of tile; Color of tile; Color (or NULL) of button; Cat (or NULL) of button)
        TileOnTable'i   -> tile on table at time of save            TileOnTable'i:Pattern;Color
        CatBoard'i      -> cat board used in game                   CatBoard'i:Cat;Pattern;Pattern
        notUsedTile'i   -> self-explanatory                         notUsedTile'i:Pattern;Color
        PlayerHand'x-n  -> Tiles Player 'x has on their hand        PlayerHand'x-n:Pattern;Color /  PlayerHand'x-n:Pattern;Color;Pattern;Color        (depends on -n)
        IfFirstTurn     -> self-explanatory                         IfFirstTurn;boolean
        ...
        'i -> int used simply for hash map not to overwrite previous input
        -n -> number of tiles on hand
         */
        Map<String, String> boardState = new HashMap<>();
        boardState.put("NuPlayers", String.valueOf(players.length));
        boardState.put("CurrentPlayer", String.valueOf(currentPlayer));
        if (firstTurn) {
            boardState.put("IfFirstTurn", "True");
        } else {
            boardState.put("IfFirstTurn", "False");
        }
        int w = 1;
        if (catBoards.containsKey(Cat.ALMOND)) {
            boardState.put("catBoard" + String.valueOf(w), "ALMOND" + ";" + catBoards.get(Cat.ALMOND).getPreferredPatterns()[0].toString() + ";" + catBoards.get(Cat.ALMOND).getPreferredPatterns()[1].toString());
            w++;
        }
        if (catBoards.containsKey(Cat.CIRA)) {
            boardState.put("catBoard" + String.valueOf(w), "CIRA" + ";" + catBoards.get(Cat.CIRA).getPreferredPatterns()[0].toString() + ";" + catBoards.get(Cat.CIRA).getPreferredPatterns()[1].toString());
            w++;
        }
        if (catBoards.containsKey(Cat.CALLIE)) {
            boardState.put("catBoard" + String.valueOf(w), "CALLIE" + ";" + catBoards.get(Cat.CALLIE).getPreferredPatterns()[0].toString() + ";" + catBoards.get(Cat.CALLIE).getPreferredPatterns()[1].toString());
            w++;
        }
        if (catBoards.containsKey(Cat.COCONUT)) {
            boardState.put("catBoard" + String.valueOf(w), "COCONUT" + ";" + catBoards.get(Cat.COCONUT).getPreferredPatterns()[0].toString() + ";" + catBoards.get(Cat.COCONUT).getPreferredPatterns()[1].toString());
            w++;
        }
        if (catBoards.containsKey(Cat.GWENIVERSE)) {
            boardState.put("catBoard" + String.valueOf(w), "GWENIVERSE" + ";" + catBoards.get(Cat.GWENIVERSE).getPreferredPatterns()[0].toString() + ";" + catBoards.get(Cat.GWENIVERSE).getPreferredPatterns()[1].toString());
            w++;
        }
        if (catBoards.containsKey(Cat.LEO)) {
            boardState.put("catBoard" + String.valueOf(w), "LEO" + ";" + catBoards.get(Cat.LEO).getPreferredPatterns()[0].toString() + ";" + catBoards.get(Cat.LEO).getPreferredPatterns()[1].toString());
            w++;
        }
        if (catBoards.containsKey(Cat.RUMI)) {
            boardState.put("catBoard" + String.valueOf(w), "RUMI" + ";" + catBoards.get(Cat.RUMI).getPreferredPatterns()[0].toString() + ";" + catBoards.get(Cat.RUMI).getPreferredPatterns()[1].toString());
            w++;
        }
        if (catBoards.containsKey(Cat.TECOLOTE)) {
            boardState.put("catBoard" + String.valueOf(w), "TECOLOTE" + ";" + catBoards.get(Cat.TECOLOTE).getPreferredPatterns()[0].toString() + ";" + catBoards.get(Cat.TECOLOTE).getPreferredPatterns()[1].toString());
            w++;
        }
        if (catBoards.containsKey(Cat.TIBBIT)) {
            boardState.put("catBoard" + String.valueOf(w), "TIBBIT" + ";" + catBoards.get(Cat.TIBBIT).getPreferredPatterns()[0].toString() + ";" + catBoards.get(Cat.TIBBIT).getPreferredPatterns()[1].toString());
            w++;
        }
        if (catBoards.containsKey(Cat.MILLIE)) {
            boardState.put("catBoard" + String.valueOf(w), "MILLIE" + ";" + catBoards.get(Cat.MILLIE).getPreferredPatterns()[0].toString() + ";" + catBoards.get(Cat.MILLIE).getPreferredPatterns()[1].toString());
        }
        // boardState.put("TilesNotUsed", String.valueOf(regularTilesLeft.size()));
        for (int i = 0; i < regularTilesLeft.size(); i++) {
            boardState.put("notUsedTile" + String.valueOf(i), regularTilesLeft.get(i).getPattern().toString() + ";" + regularTilesLeft.get(i).getColor().toString());
        }
        //boardState.put("TilesOnTable",String.valueOf(tilesOnTable.size()));
        for (int i = 0; i < tilesOnTable.size(); i++) {
            boardState.put("TileOnTable" + String.valueOf(i), tilesOnTable.get(i).getPattern().toString() + ";" + tilesOnTable.get(i).getColor().toString());
        }
        for (int i = 0; i < players.length; i++) {
            //boardState.put("Player", String.valueOf(i));
            if (players[i].getTilesOnHand().size() == 1) {
                boardState.put("PlayerHand" + ";" + String.valueOf(i) + ";" + String.valueOf(players[i].getTilesOnHand().size()), players[i].getTilesOnHand().get(0).getPattern().toString() + ";" + players[i].getTilesOnHand().get(0).getColor().toString());
            } else {
                boardState.put("PlayerHand" + ";" + String.valueOf(i) + ";" + String.valueOf(players[i].getTilesOnHand().size()), players[i].getTilesOnHand().get(0).getPattern().toString() + ";" + players[i].getTilesOnHand().get(0).getColor().toString() + ";" + players[i].getTilesOnHand().get(1).getPattern().toString() + ";" + players[i].getTilesOnHand().get(1).getColor().toString());
            }
            for (int z = 0; z < 7; z++) {
                for (int y = 0; y < 7; y++) {
                    String position = String.valueOf(i) + ";" + String.valueOf(z) + ";" + String.valueOf(y);
                    //4,2 2,3 3,4
                    if (z == 2 && y == 4) {
                        if (players[i].getBoard().getField(2, 4).hasProjectTile()) {
                            boardState.put(position, players[i].getBoard().getField(z, y).getProjectTile().getType().toString());
                        } else {
                            boardState.put(position, "null");
                        }
                    } else if (z == 3 && y == 2) {
                        if (players[i].getBoard().getField(3, 2).hasProjectTile()) {
                            boardState.put(position, players[i].getBoard().getField(z, y).getProjectTile().getType().toString());
                        } else {
                            boardState.put(position, "null");
                        }
                    } else if (z == 4 && y == 3) {
                        if (players[i].getBoard().getField(4, 3).hasProjectTile()) {
                            boardState.put(position, players[i].getBoard().getField(z, y).getProjectTile().getType().toString());
                        } else {
                            boardState.put(position, "null");
                        }
                    } else if (players[i].getBoard().getField(z, y).hasRegularTile()) {
                        if (players[i].getBoard().getField(z, y).hasColorButton() && players[i].getBoard().getField(z, y).hasCatButton()) {
                            boardState.put(position, players[i].getBoard().getField(z, y).getRegularTile().getPattern().toString() + ";" + players[i].getBoard().getField(z, y).getRegularTile().getColor().toString() + ";" + players[i].getBoard().getField(z, y).getColorButton().getColor().toString() + ";" + players[i].getBoard().getField(z, y).getCatButton().getCat().toString());
                        } else if (players[i].getBoard().getField(z, y).hasColorButton()) {
                            boardState.put(position, players[i].getBoard().getField(z, y).getRegularTile().getPattern().toString() + ";" + players[i].getBoard().getField(z, y).getRegularTile().getColor().toString() + ";" + players[i].getBoard().getField(z, y).getColorButton().getColor().toString() + ";" + "null");
                        } else if (players[i].getBoard().getField(z, y).hasCatButton()) {
                            boardState.put(position, players[i].getBoard().getField(z, y).getRegularTile().getPattern().toString() + ";" + players[i].getBoard().getField(z, y).getRegularTile().getColor().toString() + ";" + "null" + ";" + players[i].getBoard().getField(z, y).getCatButton().getCat().toString());
                        } else {
                            boardState.put(position, players[i].getBoard().getField(z, y).getRegularTile().getPattern().toString() + ";" + players[i].getBoard().getField(z, y).getRegularTile().getColor().toString() + ";" + "null" + ";" + "null");
                        }
                    } else {
                        boardState.put(position, "null;null;null;null;");
                    }
                }
            }
            StringBuilder jsonBuilder = new StringBuilder();
            jsonBuilder.append("{");
            for (Map.Entry<String, String> entry : boardState.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                jsonBuilder.append("\"").append(key).append("\":\"").append(value).append("\",");
            }
            // Remove the trailing comma
            if (jsonBuilder.length() > 1) {
                jsonBuilder.setLength(jsonBuilder.length() - 1);
            }
            jsonBuilder.append("}");

            // Write JSON string to file
            try {
                FileWriter fileWriter = new FileWriter(filePath);
                fileWriter.write(jsonBuilder.toString());
                fileWriter.flush();
                fileWriter.close();
                System.out.println("JSON file saved successfully!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void loadGameStateFromJSON(String path) {
        StringBuilder jsonBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Parse JSON string and convert to HashMap
        String jsonString = jsonBuilder.toString();
        HashMap<String, String> loadedData = new HashMap<>();
        if (jsonString.startsWith("{") && jsonString.endsWith("}")) {
            jsonString = jsonString.substring(1, jsonString.length() - 1);
            String[] keyValuePairs = jsonString.split(",");
            for (String pair : keyValuePairs) {
                String[] keyValue = pair.split(":");
                if (keyValue.length == 2) {
                    String key = keyValue[0].replaceAll("\"", "").trim();
                    String value = keyValue[1].replaceAll("\"", "").trim();
                    loadedData.put(key, value);
                }
            }
        }
        int numberOfPlayers = 4;
        tilesOnTable.clear();
        catBoards.clear();
        regularTilesLeft.clear();

        RegularTile[][] onHandTiles = new RegularTile[4][3];
        Board[] playerBoards = new Board[4];

        for (int i = 0; i < 4; i++) {
            playerBoards[i] = Board.getRandom(new Random());
        }
        ProjectTile[][] PlayersProjectTiles = new ProjectTile[4][4];
        for (Map.Entry<String, String> element : loadedData.entrySet()) {
            switch (element.getKey().substring(0, 2)) {
                case "Nu" -> numberOfPlayers = Integer.parseInt(element.getValue());
                case "Cu" -> currentPlayer = Integer.parseInt(element.getValue());
                case "Ti" -> {
                    String[] helperTableTile = element.getValue().split(";");
                    tilesOnTable.add(new RegularTile(Color.valueOf(helperTableTile[1]), TilePattern.valueOf(helperTableTile[0])));
                }
                case "ca" -> {
                    String[] helperCatBoard = element.getValue().split(";");
                    TilePattern[] helperCatPatterns = {TilePattern.valueOf(helperCatBoard[1]), TilePattern.valueOf(helperCatBoard[2])};
                    catBoards.put(Cat.valueOf(helperCatBoard[0]), new CatBoard(Cat.valueOf(helperCatBoard[0]), helperCatPatterns));
                }
                case "no" -> {
                    String[] helperNotUsed = element.getValue().split(";");
                    regularTilesLeft.add(new RegularTile(Color.valueOf(helperNotUsed[1]), TilePattern.valueOf(helperNotUsed[0])));
                }
                case "Pl" -> {
                    String[] helperHandSize = element.getKey().split(";");
                    String[] helperHandTiles = element.getValue().split(";");
                    if (Integer.parseInt(helperHandSize[2]) == 2) {
                        onHandTiles[Integer.parseInt(helperHandSize[1])][0] = new RegularTile(Color.GREEN, TilePattern.FERN);   //not null = 2; null =1
                        onHandTiles[Integer.parseInt(helperHandSize[1])][1] = new RegularTile(Color.valueOf(helperHandTiles[1]), TilePattern.valueOf(helperHandTiles[0]));
                        onHandTiles[Integer.parseInt(helperHandSize[1])][2] = new RegularTile(Color.valueOf(helperHandTiles[3]), TilePattern.valueOf(helperHandTiles[2]));
                    } else {
                        onHandTiles[Integer.parseInt(helperHandSize[1])][0] = null;
                        onHandTiles[Integer.parseInt(helperHandSize[1])][1] = new RegularTile(Color.valueOf(helperHandTiles[1]), TilePattern.valueOf(helperHandTiles[0]));
                    }
                }
                case "If" -> firstTurn = Objects.equals(element.getValue(), "True");
                case "1;", "0;", "3;", "2;" -> {
                    String[] helperPosition = element.getKey().split(";");
                    String[] helperTile = element.getValue().split(";");
                    if (!Objects.equals(helperTile[0], "null")) {
                        if (Integer.parseInt(helperPosition[1]) == 2 && Integer.parseInt(helperPosition[2]) == 4) {
                            PlayersProjectTiles[Integer.parseInt(helperPosition[0])][0] = new ProjectTile(ProjectTileType.valueOf(helperTile[0]));

                        } else if (Integer.parseInt(helperPosition[1]) == 3 && Integer.parseInt(helperPosition[2]) == 2) {
                            PlayersProjectTiles[Integer.parseInt(helperPosition[0])][1] = new ProjectTile(ProjectTileType.valueOf(helperTile[0]));

                        } else if (Integer.parseInt(helperPosition[1]) == 4 && Integer.parseInt(helperPosition[2]) == 3) {
                            PlayersProjectTiles[Integer.parseInt(helperPosition[0])][2] = new ProjectTile(ProjectTileType.valueOf(helperTile[0]));
                        } else {
                            playerBoards[Integer.parseInt(helperPosition[0])].putTileJSON(new RegularTile(Color.valueOf(helperTile[1]), TilePattern.valueOf(helperTile[0])), Integer.parseInt(helperPosition[1]), Integer.parseInt(helperPosition[2]));
                            if (!Objects.equals(helperTile[2], "null")) {
                                playerBoards[Integer.parseInt(helperPosition[0])].putColorButtonJSON(Color.valueOf(helperTile[2]), Integer.parseInt(helperPosition[1]), Integer.parseInt(helperPosition[2]));
                            }
                            if (!Objects.equals(helperTile[3], "null")) {
                                playerBoards[Integer.parseInt(helperPosition[0])].putCatButtonJSON(new CatButton(Cat.valueOf(helperTile[3])), Integer.parseInt(helperPosition[1]), Integer.parseInt(helperPosition[2]));
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < numberOfPlayers; i++) {
            PlayersProjectTiles[i][3] = null;
            players[i] = new Player(i, playerBoards[i], PlayersProjectTiles[i]);
            players[i].setProjectTiles(0, 1, 2);
            players[i].addTile(onHandTiles[i][1]);
            if (onHandTiles[i][0] != null) {
                players[i].addTile(onHandTiles[i][2]);
            }
        }

    }

    public static void main(String[] args) {
        Game game = new Game(4);

        //game.loadGameStateFromJSON("test.json");
        /*game.updateState("0;project;0;1;2");
        game.updateState("1;project;0;1;2");
        game.updateState("0;put_tile;0;1;1");
        game.updateState("0;give;2");
        game.updateState("0;end");*/
        //game.save("test.json");

    }
}
