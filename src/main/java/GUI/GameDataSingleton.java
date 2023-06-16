package GUI;

import gamepackage.Game;

public class GameDataSingleton {
    private static final GameDataSingleton instance = new GameDataSingleton();
    public static GameDataSingleton getInstance() {
        return instance;
    }
    private static int numberOfPlayers;
    private static Game game;

    private GameDataSingleton() {}
    public void setNumberOfPlayers(int n) {
        this.numberOfPlayers = n;
    }
    public void setGame(Game g) {
        game = g;
    }

    public static int getNumberOfPlayers() {
        return numberOfPlayers;
    }
    public static Game getGame() { return game; }
}
