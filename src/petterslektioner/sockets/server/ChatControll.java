package petterslektioner.sockets.server;

import java.io.IOException;
import java.io.PrintWriter;

public class ChatControll {
    public synchronized static void sendMessageToUser(User user, String message) {
        PrintWriter out = null;
        try {
            out = new PrintWriter(user.getSocket().getOutputStream(), true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        out.print(message);
        out.flush();

    }
}
