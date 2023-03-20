package petterslektioner.sockets.server.commands;

import petterslektioner.sockets.server.ChatControll;
import petterslektioner.sockets.server.Command;
import petterslektioner.sockets.server.User;

import java.io.IOException;
import java.util.List;

public class KickCommand extends Command {


    private final List<User> users;
    private final User userToKick;

    public KickCommand(User runningUser, List<User> users, User userToKick) {
        super(runningUser);
        this.users = users;
        this.userToKick = userToKick;
    }

    @Override
    public boolean requireAdminPrivileges() {
        return true;
    }

    @Override
    public String getCommand() {
        return null;
    }

    @Override
    public String getHelpText() {
        return null;
    }

    @Override
    public String getUsageText() {
        return null;
    }

    @Override
    public void execute() {
        if (!runningUser.isAdmin()) {
            ChatControll.sendMessageToUser(runningUser, "You do not have the required privileges to kick users");
            return;
        }

        users.remove(userToKick);
        try {
            ChatControll.sendMessageToUser(userToKick, "You have been kicked from the server");
            userToKick.getSocket().close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
