package GUI;

public class GameDataMonostate {
    private static int numberOfPlayers;

    public GameDataMonostate() {}
    public GameDataMonostate(int n) {
        numberOfPlayers = n;
    }

    public static int getNumberOfPlayers() {
        return numberOfPlayers;
    }
}
