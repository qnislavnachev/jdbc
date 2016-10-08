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
  public Connection get() {
    try {
      Class.forName("com.mysql.jdbc.Driver").newInstance();
      return DriverManager.getConnection("jdbc:mysql://localhost/" + database + "?user=root&password=clouway.com");
    } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }
    return null;
  }
}
