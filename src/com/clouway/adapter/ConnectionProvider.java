package com.clouway.adapter;

import com.clouway.core.Provider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Vasil Mitov <v.mitov.clouway@gmail.com>
 */
public class ConnectionProvider implements Provider<Connection> {
  private final String database;

  public ConnectionProvider(String database) {
    this.database = database;
  }

  @Override
  public Connection get() throws SQLException {
    try {
      Class.forName("com.mysql.jdbc.Driver").newInstance();
      return DriverManager.getConnection("jdbc:mysql://localhost/" + database + "?user=root&password=clouway.com");
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      throw new IllegalStateException("The MySQL JDBC driver wasn't configured");
    }
    throw new SQLException("There was an error with your connection.");
  }
}
