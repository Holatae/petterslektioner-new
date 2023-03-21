package petterslektioner.sockets.server.commands;

import petterslektioner.sockets.server.ChatControl;
import petterslektioner.sockets.server.User;
import petterslektioner.sockets.server.abstracts.Command;

public class HelpCommand extends Command {
    private AdminCommand adminCommand;
    private AdminlistCommand adminlistCommand;
    private KickCommand kickCommand;
    private RenameCommand renameCommand;
    private ViewCommand viewCommand;
    public HelpCommand(User runningUser) {
        super(runningUser);
    }

    @Override
    public boolean requireAdminPrivileges() {
        return false;
    }

    @Override
    public String getCommand() {
        return "help";
    }

    @Override
    public String getHelpText() {
        return "Lists all commands\n";
    }

    @Override
    public String getUsageText() {
        return null;
    }

    @Override
    public void execute() {
        final String middleThing = " - ";
        adminCommand = new AdminCommand(null, null);
        adminlistCommand = new AdminlistCommand(null);
        kickCommand = new KickCommand(null, null);
        renameCommand = new RenameCommand(null, null);
        viewCommand = new ViewCommand(null, null);

        ChatControl.sendMessageToUser(runningUser, adminCommand.getCommand() + middleThing + adminCommand.getHelpText());
        ChatControl.sendMessageToUser(runningUser, adminlistCommand.getCommand() + middleThing + adminlistCommand.getHelpText());
        ChatControl.sendMessageToUser(runningUser, kickCommand.getCommand() + middleThing + kickCommand.getHelpText());
        ChatControl.sendMessageToUser(runningUser, renameCommand.getCommand() + middleThing + renameCommand.getHelpText());
        ChatControl.sendMessageToUser(runningUser, viewCommand.getCommand() + middleThing + viewCommand.getHelpText());
    }
}
