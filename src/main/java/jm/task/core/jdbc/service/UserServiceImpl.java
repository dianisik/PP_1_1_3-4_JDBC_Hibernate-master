package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;

import static jm.task.core.jdbc.util.Util.getConnection;
import static jm.task.core.jdbc.util.Util.getQuery;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    public void createUsersTable() throws SQLException {
        String query = getQuery("createUsersTable");
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.execute();
        preparedStatement.close();
        connection.close();

    }

    public void dropUsersTable() throws SQLException {
        String query = getQuery("dropUsersTable");
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.execute();
        preparedStatement.close();
        connection.close();
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        String query = getQuery("saveUser");
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, lastName);
        preparedStatement.setByte(3, age);
        preparedStatement.execute();
        preparedStatement.close();
        connection.close();
    }

    public void removeUserById(long id) throws SQLException {
        String query = getQuery("removeUserById");
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setLong(1, id);
        preparedStatement.execute();
        preparedStatement.close();
        connection.close();
    }

    public List<User> getAllUsers() throws SQLException {
        String query = getQuery("getAllUsers");
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        List <User> users = new ArrayList<>();
        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getLong(1));
            user.setName(resultSet.getString(2));
            user.setLastName(resultSet.getString(3));
            user.setAge(resultSet.getByte(4));
            users.add(user);
        }
        return users;
    }

    public void cleanUsersTable() {
        String query = getQuery("cleanUsersTable");
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
