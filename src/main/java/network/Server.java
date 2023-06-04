package network;

import game.Game;
import game.Player;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private ArrayList<Client> clientList;
    private Game currentGame;
    private Player player;
    private String ipAddress;
    private ServerSocket serverSocket;


    public Server() throws IOException {

    }

    /**
     * receives message from a client
     * @return received message
     * @throws IOException
     */
    public String receive() throws IOException {
        Socket socket = serverSocket.accept();
        InputStream input = socket.getInputStream();
        DataInputStream dataInput = new DataInputStream(input);
        String received = dataInput.readUTF();
        dataInput.close();
        return received;
    }

    /**
     * sends message to a client
     * @param message
     * @throws IOException
     */
    public void send(String message) throws IOException {
        Socket socket = serverSocket.accept();
        OutputStream output = socket.getOutputStream();
        DataOutputStream dataOutput = new DataOutputStream(output);
        dataOutput.writeUTF(message);
        dataOutput.flush();
        dataOutput.close();
    }


    private Game getCurrentGame() {
        return currentGame;
    }

    private String getIpAddress() {
        return ipAddress;
    }

    private void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
