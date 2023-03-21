package petterslektioner.sockets.server.commands;

import petterslektioner.sockets.server.ChatControl;
import petterslektioner.sockets.server.User;
import petterslektioner.sockets.server.abstracts.Command;
import petterslektioner.sockets.server.exceptions.PermissionDeniedException;

public class AdminCommand extends Command {
    private User userToMakeAdmin;
    public AdminCommand(User runningUser, User userToMakeAdmin) {
        super(runningUser);
        this.userToMakeAdmin = userToMakeAdmin;
    }

    @Override
    public boolean requireAdminPrivileges() {
        return true;
    }

    @Override
    public String getCommand() {
        return "admin";
    }

    @Override
    public String getHelpText() {
        return "Makes a user an admin\n";
    }

    @Override
    public String getUsageText() {
        return null;
    }

    @Override
    public void execute() throws PermissionDeniedException {
        if (!runningUser.isAdmin()){throw new PermissionDeniedException(runningUser);}
        userToMakeAdmin.setAdmin(true);
        ChatControl.sendMessageToUser(runningUser, userToMakeAdmin.getName() + ": Is now an Admin");
    }
}
