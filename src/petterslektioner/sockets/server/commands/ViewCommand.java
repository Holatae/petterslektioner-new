package petterslektioner.sockets.server.commands;

import petterslektioner.sockets.server.User;
import petterslektioner.sockets.server.abstracts.Command;
import petterslektioner.sockets.server.exceptions.PermissionDeniedException;

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
        return "Lists all users\n";
    }

    @Override
    public String getUsageText() {
        return null;
    }

    @Override
    public void execute() throws PermissionDeniedException {
        if (!runningUser.isAdmin()) {
            throw new PermissionDeniedException(runningUser);
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
