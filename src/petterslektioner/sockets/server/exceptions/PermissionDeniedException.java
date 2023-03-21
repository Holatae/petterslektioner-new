package petterslektioner.sockets.server.exceptions;

import petterslektioner.sockets.server.ChatControl;
import petterslektioner.sockets.server.User;

public class PermissionDeniedException extends Exception {
    public PermissionDeniedException(User runningUser) {
        ChatControl.sendMessageToUser(runningUser, "You don't have permission to run this command");
    }
}
