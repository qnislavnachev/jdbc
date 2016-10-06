package com.clouway.adapter;

import com.clouway.core.Person;
import com.clouway.core.PeopleRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * @author Vasil Mitov <v.mitov.clouway@gmail.com>
 */
public class PersistentPeopleRepository implements PeopleRepository {
  private ConnectionProvider connectionProvider;

  public PersistentPeopleRepository(ConnectionProvider connectionProvider) {
    this.connectionProvider = connectionProvider;
  }

  @Override
  public void add(Person person) {
    Connection connection = connectionProvider.get();
    String query = "INSERT INTO PEOPLE VALUES(?,?,?,?)";
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setString(1, person.name);
      preparedStatement.setInt(2, person.egn);
      preparedStatement.setInt(3, person.age);
      preparedStatement.setString(4, person.email);
      preparedStatement.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void delete(Person person) {
    Connection connection = connectionProvider.get();
    String query = "DELETE FROM PEOPLE WHERE EGN=(?)";
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setInt(1, person.egn);
      preparedStatement.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void updateField(Person person, String field, Object value) {
    Connection connection = connectionProvider.get();
    String query = "UPDATE PEOPLE SET " + field + " = " + "'" + value + "'" + " WHERE EGN=" + person.egn;
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public String findPeopleStartingWithLetter(String letter) {
    Connection connection = connectionProvider.get();
    String query = "SELECT * FROM PEOPLE WHERE NAME LIKE '" + letter + "%%%%'";
    String result = "";
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      ResultSet resultSet = preparedStatement.executeQuery(query);
      ResultSetMetaData resultSetMetaData = preparedStatement.getMetaData();
      int columntCount = resultSetMetaData.getColumnCount();
      while (resultSet.next()) {
        for (int i = 1; i <= columntCount; i++) {
          result += resultSet.getString(i) + " ";
        }
        result += "\n";
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return result;
  }

    @Override
    public String findByEGN (Integer EGN){
      Connection connection = connectionProvider.get();
      String query = "SELECT NAME,EGN,AGE,EMAIL FROM PEOPLE WHERE EGN=" + EGN;
      String result = "";
      try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        ResultSet resultSet = preparedStatement.executeQuery(query);
        ResultSetMetaData resultSetMetaData = preparedStatement.getMetaData();
        int columnCount = resultSetMetaData.getColumnCount();
        while (resultSet.next()) {
          for (int i = 1; i <= columnCount; i++) {
            result += resultSet.getString(i) + " ";
          }
        }

      } catch (SQLException e) {
        e.printStackTrace();
      }
      return result;
    }

    @Override
    public String display (String table){
      Connection connection = connectionProvider.get();
      String query = "SELECT * FROM PEOPLE";
      String result = "";
      try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        ResultSet resultSet = preparedStatement.executeQuery(query);
        ResultSetMetaData resultSetMetaData = preparedStatement.getMetaData();
        int columntCount = resultSetMetaData.getColumnCount();
        while (resultSet.next()) {
          for (int i = 1; i <= columntCount; i++) {
            result += resultSet.getString(i) + " ";
          }
          result += "\n";
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
      return result;
    }
  }

