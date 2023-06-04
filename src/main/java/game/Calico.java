package game;

import network.Server;

public class Calico {
    private Server server;
    private Game game;
    private boolean isNetwork;

    private void setServer(Server server) {
        this.server = server;
    }

    private void setGame(Game game) {
        this.game = game;
    }

    public void setIsNetwork(boolean isNetwork) {
        this.isNetwork = isNetwork;
    }

    public Calico(Game game) {
    }
}
