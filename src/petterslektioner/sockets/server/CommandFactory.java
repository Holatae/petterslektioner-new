package petterslektioner.sockets.server;

import petterslektioner.sockets.server.commands.HelpCommand;
import petterslektioner.sockets.server.commands.KickCommand;
import petterslektioner.sockets.server.commands.UnknownCommand;

public class CommandFactory {
    public static Command getCommand(String command, User user) {
        String[] commandArr = command.split(" ");
        return switch (commandArr[0]) {
//            case "view" -> new ViewCommand(user);
            case "help" -> new HelpCommand(user);
//            case "quit" -> new QuitCommand(user);
            case "kick" -> new KickCommand(user,
                    UserAdministration.getUsers(),
                    UserAdministration.getUserByName(secondArg));
//            case "ban" -> new BanCommand(user);
//            case "unban" -> new UnbanCommand(user);
//            case "banlist" -> new BanlistCommand(user);
//            case "name" -> new NameCommand(user);
//            case "admin" -> new AdminCommand(user);
//            case "unadmin" -> new UnadminCommand(user);
//            case "adminlist" -> new AdminlistCommand(user);
//            case "msg" -> new MsgCommand(user);
//            case "broadcast" -> new BroadcastCommand(user);
            default -> new UnknownCommand(user);
        };
    }
}
