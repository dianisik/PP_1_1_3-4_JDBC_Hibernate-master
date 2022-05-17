package jm.task.core.jdbc;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

import static jm.task.core.jdbc.util.Util.getConnection;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("James", "Harrison", (byte) 25);
        userService.saveUser("Nick", "Murray", (byte) 20);
        userService.saveUser("Naomi", "Osaka", (byte) 22);
        userService.saveUser("Petra", "Kvitova", (byte) 28);

        for (User user : userService.getAllUsers()){
            System.out.println(user.getName() + " " + user.getLastName());
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }


}
