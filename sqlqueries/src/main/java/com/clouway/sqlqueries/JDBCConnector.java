package com.clouway.sqlqueries;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by clouway on 25.10.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class JDBCConnector {
    private final String JDBC_DRIVER;
    private final String DB_URL;
    private final String USER;
    private final String PASSWORD;

    public JDBCConnector(String jDBCDriver, String databaseURL, String user, String password) {
        this.JDBC_DRIVER = jDBCDriver;
        this.DB_URL = databaseURL;
        this.USER = user;
        this.PASSWORD = password;
    }

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        Connection connection = DriverManager.getConnection(DB_URL,USER,PASSWORD);
        return connection;
    }
}
