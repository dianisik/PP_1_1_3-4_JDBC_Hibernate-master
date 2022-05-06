package jm.task.core.jdbc.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {

    private static Properties props = new Properties();

    public static String getQuery(String key) {
        if (props.size() > 0) return props.getProperty(key);
        try (InputStream input = Util.class.getClassLoader().getResourceAsStream("db.properties")) {
            props.load(input);
        }
        catch (IOException ex) {
            System.out.println("Не удалось загрузить настройки");
        }
        return props.getProperty(key);
    }


    public static Connection getConnection() throws SQLException {
        if (props.size() == 0) {
            try (InputStream input = Util.class.getClassLoader().getResourceAsStream("db.properties")) {
                props.load(input);


            } catch (IOException ex) {
                System.out.println("Не удалось соединиться с базой данных: " + ex.getMessage());
                return null;
            }
        }
        String username = props.getProperty("username");
        String dbname = props.getProperty("dbname");
        String password = props.getProperty("password");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dbname, username, password);

    }
}

