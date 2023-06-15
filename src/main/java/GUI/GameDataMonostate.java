package GUI;

import gamepackage.Game;

public class GameDataMonostate {
    private static int numberOfPlayers;
    private static Game game;

    public GameDataMonostate() {}
    public GameDataMonostate(int n) {
        numberOfPlayers = n;
    }
    public GameDataMonostate(Game g) {
        game = g;
    }

    public static int getNumberOfPlayers() {
        return numberOfPlayers;
    }
    public static Game getGame() { return game; }
}
