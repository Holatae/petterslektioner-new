package petterslektioner.sockets.server.commands;

import petterslektioner.sockets.server.User;
import petterslektioner.sockets.server.abstracts.Command;

public class RenameCommand extends Command {
    private final String nameToChangeTo;
    public RenameCommand(User runningUser, String nameToChangeTo) {
        super(runningUser);
        this.nameToChangeTo = nameToChangeTo;
    }

    @Override
    public boolean requireAdminPrivileges() {
        return false;
    }

    @Override
    public String getCommand() {
        return "rename";
    }

    @Override
    public String getHelpText() {
        return "Changes your name\n";
    }

    @Override
    public String getUsageText() {
        return null;
    }

    @Override
    public void execute() {
        runningUser.setName(nameToChangeTo);
    }
}
