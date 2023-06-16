package GUI;

import gamepackage.Game;

public class GameDataSingleton {
    private static final GameDataSingleton instance = new GameDataSingleton();
    public static GameDataSingleton getInstance() {
        return instance;
    }
    private static int numberOfPlayers;
    private static Game game;
    private static String[] playerNames = new String[4];

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
