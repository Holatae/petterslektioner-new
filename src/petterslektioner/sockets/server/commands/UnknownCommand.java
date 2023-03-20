package petterslektioner.sockets.server.commands;

import petterslektioner.sockets.server.ChatControll;
import petterslektioner.sockets.server.Command;
import petterslektioner.sockets.server.User;

public class UnknownCommand extends Command {
    public UnknownCommand(User user) {
        super(user);
    }

    @Override
    public boolean requireAdminPrivileges() {
        return false;
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
        ChatControll.sendMessageToUser(runningUser, "Unknown command");

    }
}
