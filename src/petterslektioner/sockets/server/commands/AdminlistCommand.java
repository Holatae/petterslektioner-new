package petterslektioner.sockets.server.commands;

import petterslektioner.sockets.server.ChatControl;
import petterslektioner.sockets.server.User;
import petterslektioner.sockets.server.abstracts.Command;
import petterslektioner.sockets.server.administration.UserAdministration;

public class AdminlistCommand extends Command {
    public AdminlistCommand(User runningsUser) {
        super(runningsUser);
    }

    @Override
    public boolean requireAdminPrivileges() {
        return false;
    }

    @Override
    public String getCommand() {
        return "adminlist";
    }

    @Override
    public String getHelpText() {
        return "Lists all admins\n";
    }

    @Override
    public String getUsageText() {
        return null;
    }

    @Override
    public void execute() {
        for (User user: UserAdministration.getUsers()
             ) {
            if (user.isAdmin()){
                ChatControl.sendMessageToUser(user, user.getName());
            }
        }
    }
}
