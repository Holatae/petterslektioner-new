package petterslektioner.sockets.server.commands;

import petterslektioner.sockets.server.Command;
import petterslektioner.sockets.server.User;

import java.util.List;

public class ViewCommand extends Command {
    private final List<User> users;

    @Override
    public boolean requireAdminPrivileges() {
        return true;
    }

    @Override
    public String getCommand() {
        return "view";
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
            System.out.println("You do not have the required privileges to view users");
            return;
        }

        for (User user : users) {
            System.out.println(user.getName() + " " + user.getSocket().getInetAddress());
        }
    }

    public ViewCommand(User user, List<User> users) {
        super(user);
        this.users = users;
    }
}
