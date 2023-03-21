package petterslektioner.sockets.server;

import petterslektioner.sockets.server.abstracts.Command;
import petterslektioner.sockets.server.administration.UserAdministration;
import petterslektioner.sockets.server.commands.*;

public class CommandFactory {
    public static Command getCommand(String command, User user) {
        String[] commandArr = command.split(" ");
        try{
            return switch (commandArr[0]) {
                case "view" -> new ViewCommand(user, UserAdministration.getUsers());
                case "help" -> new HelpCommand(user);
                case "kick" -> new KickCommand(user,
                        UserAdministration.getUserByName(commandArr[1]));
                case "rename" -> new RenameCommand(user, commandArr[1]);
                case "admin" -> new AdminCommand(user, UserAdministration.getUserByName(commandArr[1]));
                case "admin-list" -> new AdminlistCommand(user);
                default -> new UnknownCommand(user);
            };
        }catch (Exception e){
            return new UnknownCommand(user);
        }

    }
}
