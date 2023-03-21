package petterslektioner.sockets.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {
    private final String HOST = "127.0.0.1";
    private final int PORT = 1234;

    private InputStream inputStream;
    public static void main(String[] args) {
        new Client().startClient();
    }

    private void startClient() {
        try {
            Socket socket = new Socket(HOST, PORT);
            System.out.println("Connected to server");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        checkForMessages(socket);
                    }
                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Enter name: ");
                    String name = scanner.nextLine();
                    sendNameToServer(name, socket);
                    System.out.println("Enter message: ");
                    while (true){
                        String message = scanner.nextLine();
                        sendMessageToServer(message, socket);
                    }
                }
            }).start();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private void checkForMessages(Socket socket){
        // Check for messages from server
        try {
            InputStream inputStream = socket.getInputStream();
            while (true) {
                int data;
                ArrayList<Character> messageArr = new ArrayList<>();
                while (inputStream.available() > 0) {
                    data = inputStream.read();
                    messageArr.add((char) data);
                    //String message = messageArr.stream().map(Object::toString).collect(Collectors.joining());
                }
                if (messageArr.size() > 0)
                {
                    String message = buildStringFromChars(messageArr);
                    System.out.println(message);
                }
                }
            } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String buildStringFromChars(ArrayList<Character> chars) {
        StringBuilder message = new StringBuilder();
        for (Character character : chars
        ) {
            message.append(character);
        }
        return message.toString();
    }

    private void sendMessageToServer(String message, Socket socket){
        // Send message to server
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.print(message);
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendNameToServer(String name, Socket socket){
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.print(name);
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
