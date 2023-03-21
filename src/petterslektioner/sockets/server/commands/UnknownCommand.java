package petterslektioner.sockets.server.commands;

import petterslektioner.sockets.server.ChatControl;
import petterslektioner.sockets.server.User;
import petterslektioner.sockets.server.abstracts.Command;

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
        ChatControl.sendMessageToUser(runningUser, "Unknown command");

    }
}
