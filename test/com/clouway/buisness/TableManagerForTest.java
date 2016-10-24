package com.clouway.buisness;

import com.clouway.adapter.ConnectionProvider;
import com.clouway.core.Provider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Vasil Mitov <v.mitov.clouway@gmail.com>
 */
public class TableManagerForTest {
  private Provider<Connection> provider;

  public TableManagerForTest(Provider<Connection> provider) {
    this.provider = provider;
  }

  public void display(String table){
    String query="SELECT * FORM "+table;
    Connection connection=provider.get();
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        connection.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public void deleteTable(String table) {
    Connection connection = provider.get();
    String query = "DELETE FROM " + table;
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        connection.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
}
