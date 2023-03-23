package petterslektioner.sockets.server.administration;

import petterslektioner.sockets.server.User;
import petterslektioner.sockets.server.exceptions.UserNotFoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserAdministration {
    private static final List<User> users = Collections.synchronizedList(new ArrayList<>());

    public static List<User> getUsers() {
        synchronized (users){
            return users;
        }
    }

    public static void addUser(User user){
        synchronized (users){
            users.add(user);
        }
    }

    /**
     * @param name the name of the user to find
     * @return the user with the given name, or null if no user with that name exists
     */
    public static User getUserByName(String name){
        synchronized (users){
            for (User user : users) {
                if (user.getName().equalsIgnoreCase(name)){
                    return user;
                }
            }
        }
        return null;
    }

    public synchronized static void deleteUser(User user) throws UserNotFoundException {
        if (user == null) {
            return;
        }
        try {
            synchronized (users){
                users.remove(user);
            }
        }catch (Exception e){
            throw new UserNotFoundException();
        }
    }
}
