package GUI;

import gamepackage.Game;

/**
 * Singleton type class responsible for storing date, that is passed
 * between two scenes
 */
public class GameDataSingleton {
    /**
     * Static instance of singleton class.
     */
    private static final GameDataSingleton instance = new GameDataSingleton();
    public static GameDataSingleton getInstance() {
        return instance;
    }

    /**
     * Stores information about number of players
     */
    private static int numberOfPlayers;
    /**
     * Stores information about current game
     */
    private static Game game;
    /**
     * Stores information about players' names
     */
    private static String[] playerNames = new String[4];

    /**
     * Default constructor
     */
    private GameDataSingleton() {}
    public void setPlayerNames(String[] table) {
        for (int i = 0; i < numberOfPlayers; i++) {
            playerNames[i] = table[i];
        }
    }
    public void setNumberOfPlayers(int n) {
        playerNames = new String[n];
        this.numberOfPlayers = n;
    }
    public void setGame(Game g) {
        game = g;
    }

    public static int getNumberOfPlayers() {
        return numberOfPlayers;
    }
    public static Game getGame() { return game; }
    public static String[] getPlayerNames() {
        return playerNames;
    }
}
