package petterslektioner.sockets.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Server {
    private boolean done = false;
    private ServerSocket serverSocket;
    private Socket clientSocket;

    private List<Socket> clientSockets = Collections.synchronizedList(new ArrayList<>());

    //private Map<Socket, String> clientNames = Collections.synchronizedMap(new HashMap<>());

    //private List<Map> clientSockets = Collections.synchronizedList(new ArrayList<>());
    public static void main(String[] args) {
        new Server().startServer();
    }

    private void startServer(){
        done = false;
        // Make this on a separate thread
        try {
            serverSocket = new ServerSocket(1234);
            Runnable checkMessagesRunnable = new Runnable() {
                @Override
                public void run() {
                    while (!done){
                        try {
                            checkForMessages();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            Thread checkMessagesThread = new Thread(checkMessagesRunnable);
            checkMessagesThread.start();
            // Wait for client to connect
            System.out.println("Waiting for client to connect");
            while (!done) {
                clientSockets.add(serverSocket.accept());
                System.out.println("Client connected");
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            done = true;
        }
    }

    private void stopServer(){
        try {
            done = true;
            clientSocket.close();
            serverSocket.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private void sendMessageToClient(String message, Socket currentClientSocket){
        for (Socket clientSocket : clientSockets
             ) {
            // Send message to client
            try {
                    if (clientSocket != currentClientSocket){
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    out.print(message);
                    out.flush();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private synchronized void checkForMessages() throws IOException {
        // Check for messages from clients
        synchronized (clientSockets){
            for (Socket clientSocket: clientSockets
                 ) {
                InputStream inputStream = clientSocket.getInputStream();
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
                    sendMessageToClient(message, clientSocket);
                }
            }
        }
    }

    private String buildStringFromChars(ArrayList<Character> chars){
        String message = "";
        for (Character character : chars
             ) {
            message += character;
        }
        return message;
    }

}
