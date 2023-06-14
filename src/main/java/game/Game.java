package game;

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
        System.out.println(numberOfPlayers);
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
                //==========
                System.out.println("projects set to: " + left + center + right);
                //====================
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
                        System.out.println("index is: " + index);
                        tilesOnTable.remove(index);
                        tilesOnTable.add(regularTilesLeft.pop());
                        System.out.println("On table are:");
                        for (Tile t:
                             getTilesOnTable()) {
                            System.out.print(t + " -> ");
                        }
                    }

                    //temporary
                    System.out.println("chosen table tile successfully. Array state: " + tilesOnTable.size());
                }
                case "put_tile" -> {
                    if (split.length != 5)
                        throw new IllegalArgumentException("Expected integers for index, x and y");
                    int index = Integer.parseInt(split[2]);
                    int x = Integer.parseInt(split[3]);
                    int y = Integer.parseInt(split[4]);
                    players[currentPlayer].putTile(index, x, y);
                    //temporary
                    System.out.println("tile put successfully");
                }
                case "put_color" -> {
                    if (split.length != 4)
                        throw new IllegalArgumentException("Expected integers for x and y");
                    int x = Integer.parseInt(split[2]);
                    int y = Integer.parseInt(split[3]);
                    players[currentPlayer].putColorButton(x, y);
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
                    System.out.println("Ending tour");
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
    /**
    TEMPORARY
    **/
    public void changeActivityOfCurrentPlayer(){
        players[currentPlayer].changeActivity();
    }
    public static void main(String[] args) {
        Game game = new Game(2);
        game.updateState("0;project;0;1;2");
        game.updateState("1;project;0;1;2");
        game.updateState("0;put_tile;0;1;1");
        game.updateState("0;give;2");
        game.updateState("0;end");
    }
}
