package petterslektioner.sockets.server.abstracts;

import petterslektioner.sockets.server.User;
import petterslektioner.sockets.server.exceptions.PermissionDeniedException;

public abstract class Command {
    protected User runningUser;
    public Command(User runningUser) {
        this.runningUser = runningUser;
    }
    public abstract boolean requireAdminPrivileges();
    public abstract String getCommand();
    public abstract String getHelpText();
    public abstract String getUsageText();
    public abstract void execute() throws PermissionDeniedException;
}
