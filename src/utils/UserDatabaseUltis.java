package utils;

import model.User;

import java.util.HashMap;
import java.util.Map;

public class UserDatabaseUltis {
    private static Map<String, User> USERS = new HashMap<>();

    private void intiData() {
        User user1 = new User("admin1", "123456");
        User user2 = new User("admin1", "123456");
        USERS.put(user1.getUsername(), user1);
        USERS.put(user2.getUsername(), user2);
    }

    public boolean checkExistUser(String username, String password) {
        User u = USERS.get(username);
        if (u != null && u.getPassword().equals(password))
            return true;
        return false;
    }
}
