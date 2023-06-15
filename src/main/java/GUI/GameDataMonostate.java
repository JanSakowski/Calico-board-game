package GUI;

import gamepackage.Game;

public class GameDataMonostate {
    private static int numberOfPlayers;

    public GameDataMonostate() {}
    public Game game;
    public GameDataMonostate(int n) {
        numberOfPlayers = n;
    }
    public GameDataMonostate(Game g) {
        this.game = g;
    }

    public Game getGame() {
        return game;
    }

    public static int getNumberOfPlayers() {
        return numberOfPlayers;
    }
}
