package petterslektioner.sockets.server;

public abstract class Command {
    protected User runningUser;
    public Command(User runningUser) {
        this.runningUser = runningUser;
    }
    public abstract boolean requireAdminPrivileges();
    public abstract String getCommand();
    public abstract String getHelpText();
    public abstract String getUsageText();
    public abstract void execute();
}
