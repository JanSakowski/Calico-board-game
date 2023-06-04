package network;

import game.Game;

import java.io.*;
import java.net.Socket;

public class Client {

    private Socket socket;

    public Client() throws IOException {
    }

    public void send(String message) throws IOException {
        OutputStream output = socket.getOutputStream();
        DataOutputStream dataOutput = new DataOutputStream(output);
        dataOutput.writeUTF(message);
        dataOutput.flush();
    }

    public String receive() throws IOException {
        InputStream input = socket.getInputStream();
        DataInputStream dataInput = new DataInputStream(input);
        String received = dataInput.readUTF();
        dataInput.close();
        return received;
    }

    public boolean connectTo(String ip, int port) {
        try {
            socket = new Socket(ip, port);
            System.out.println("Connected");
            return true;
        } catch (IOException i) {
            return false;
        }
    }

    public Game getCurrentGame() {
        return null;
    }
}
