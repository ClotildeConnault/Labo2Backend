package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnexionPSQL {

    private static Connection instance;
    private static String DBUrl = "jdbc:postgresql://localhost/labo2";
    private static String user = "postgres";
    private static String pwd = "7upfnk7J";

    private ConnexionPSQL() {
    }

    public static Connection getInstance() throws SQLException {
        if (instance == null) {
            instance = DriverManager.getConnection(DBUrl, user, pwd);
        }
        return instance;
    }
}