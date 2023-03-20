package petterslektioner.sockets.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserAdministration {
    private static final List<User> users = Collections.synchronizedList(new ArrayList<>());

    public static List<User> getUsers() {
        return users;
    }

    public static void addUser(User user){
        synchronized (users){
            users.add(user);
        }
    }

    public static User getUserByName(String name){
        synchronized (users){
            for (User user : users) {
                if (user.getName().equals(name)){
                    return user;
                }
            }
        }
        return null;
    }
}
