package petterslektioner.sockets.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Server {
    private boolean done = false;
    private ServerSocket serverSocket;
    private Socket clientSocket;

    private final List<Socket> firstTimeConnectedSocket = Collections.synchronizedList(new ArrayList<>());

    private final List<User> users = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) {
        new Server().startServer();
    }

    private void startServer() {
        done = false;
        // Make this on a separate thread
        try {
            serverSocket = new ServerSocket(1234);
            Runnable checkMessagesRunnable = new Runnable() {
                @Override
                public void run() {
                    while (!done) {
                        try {
                            checkForMessages();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            Runnable checkForFirstTimeUsers = new Runnable() {
                @Override
                public void run() {
                    while (!done) {
                        try {
                            getNameFromUser();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            User serverUser = new User(null, null);
            serverUser.setAdmin(true);
            Thread checkForFirstTimeUsersThread = new Thread(checkForFirstTimeUsers);
            checkForFirstTimeUsersThread.start();
            Thread checkMessagesThread = new Thread(checkMessagesRunnable);
            checkMessagesThread.start();
            Thread commandThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    Scanner scanner = new Scanner(System.in);
                    while (!done) {
                        String command = scanner.nextLine();
//                        if (command.equals("stop")) {
//                            stopServer();
//                        }
//                        if (command.equals("view")){
//                            synchronized (users){
//                                 ViewCommand viewCommand = new ViewCommand(users);
//                                 viewCommand.execute(serverUser);
//
//                            }
//                        }
//                        if (command.contains("kick")){
//                            for (User user: users
//                                 ) {
//                                if (user.getName().equals(command.split(" ")[1])){
//                                    KickCommand kickCommand = new KickCommand(users, user);
//                                    kickCommand.execute(serverUser);
//                                }
//                            }
//                        }
//                        if (command.equals("help")){
//                            System.out.println("stop - stops the server");
//                            System.out.println("view - shows all users");
//                            System.out.println("help - shows all commands");
//                            System.out.println("kick <username> - kicks the user");
//                        }
                    }
                }
            });
            commandThread.start();
            // Wait for client to connect
            System.out.println("Waiting for client to connect");
            while (!done) {
                firstTimeConnectedSocket.add(serverSocket.accept());
                //clients.add(new HashMap<Socket, String>(){{put(serverSocket.accept(), "Client");}});
                System.out.println("Client connected");
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            done = true;
        }
    }

    private void stopServer() {
        try {
            done = true;
            clientSocket.close();
            serverSocket.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    /**
     * @param sendingUser - The user that sent the message
     * @param message - The message that was sent
     * @param currentClientSocket - The socket of the client that should not get the message
     */
    private void sendMessageToClient(String sendingUser, String message, Socket currentClientSocket) {
        synchronized (users){
            for (User user : UserAdministration.getUsers()
                 ) {
                // Send message to client
                try {
                    if (user.getSocket() != currentClientSocket) {
                        PrintWriter out = new PrintWriter(user.getSocket().getOutputStream(), true);
                        out.print(sendingUser + ": "+ message);
                        out.flush();
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    // Get's the name from the client and adds it to the list of users
    private synchronized void getNameFromUser() throws IOException {
        ArrayList<Socket> socketsToRemove = new ArrayList<>();
        synchronized (firstTimeConnectedSocket) {
            for (Socket clientSocket : firstTimeConnectedSocket
            ) {
                InputStream inputStream = clientSocket.getInputStream();
                int data;
                ArrayList<Character> nameArr = new ArrayList<>();
                while (inputStream.available() > 0) {
                    data = inputStream.read();
                    nameArr.add((char) data);
                }
                if (nameArr.size() > 0) {
                    String name = buildStringFromChars(nameArr);
                    socketsToRemove.add(clientSocket);
                    UserAdministration.addUser(new User(clientSocket, name));
                    //users.add(new User(clientSocket, name));
                    sendMessageToClient(name, " has connected", clientSocket);
                }
            }
            for (Socket socket : socketsToRemove
            ) {
                firstTimeConnectedSocket.remove(socket);
            }
            {

            }
        }
    }

    //Check for messages from clients
    private synchronized void checkForMessages() throws IOException {
        // Check for messages from clients
        for (User user : UserAdministration.getUsers()){
            try {
                InputStream inputStream = user.getSocket().getInputStream();
                int data;
                ArrayList<Character> messageArr = new ArrayList<>();
                while (inputStream.available() > 0) {
                    data = inputStream.read();
                    messageArr.add((char) data);
                }
                if (messageArr.size() > 0) {
                    String message = buildStringFromChars(messageArr);
                    // Get the first character of the message
                    if (message.charAt(0) == '/'){
                        // Remove the first character
                        message = message.substring(1);
                        Command command = CommandFactory.getCommand(message, user);
                        command.execute();
                        return;
                    }
                    System.out.println(user.getName() + ": " + message);
                    sendMessageToClient(user.getName(), message, user.getSocket());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Build string from chars
    private String buildStringFromChars(ArrayList<Character> chars){
        String message = "";
        for (Character character : chars
             ) {
            message += character;
        }
        return message;
    }

}
