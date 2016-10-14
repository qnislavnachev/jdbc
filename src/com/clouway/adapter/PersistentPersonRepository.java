package com.clouway.adapter;

import com.clouway.core.Person;
import com.clouway.core.PersonRepository;
import com.clouway.core.Provider;
import com.clouway.core.TableManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * @author Vasil Mitov <v.mitov.clouway@gmail.com>
 */
public class PersistentPersonRepository implements PersonRepository {
  private Provider<Connection> provider;

  public PersistentPersonRepository(Provider<Connection> provider) {
    this.provider = provider;
  }

  @Override
  public void register(Person person) {
    Connection connection = provider.get();
    String query = "INSERT INTO PEOPLE VALUES(?,?,?,?)";
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setString(1, person.name);
      preparedStatement.setInt(2, person.egn);
      preparedStatement.setInt(3, person.age);
      preparedStatement.setString(4, person.email);
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

  @Override
  public void delete(Person person) {
    Connection connection = provider.get();
    String query = "DELETE FROM PEOPLE WHERE EGN=(?)";
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setInt(1, person.egn);
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

  @Override
  public Optional<Person> find(Integer EGN) {
    Connection connection = provider.get();
    String query = "SELECT * FROM PEOPLE WHERE EGN=(?)";
    Optional<Person> result = Optional.empty();
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setInt(1, EGN);
      ResultSet rs = preparedStatement.executeQuery(query);
      rs.next();
      String name = rs.getString(1);
      Integer egn = rs.getInt(2);
      Integer age = rs.getInt(3);
      String email = rs.getString(4);
      result = Optional.of(new Person(name, egn, age, email));
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        connection.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return result;
  }

  @Override
  public List<Person> findAll(String letter) {
    Connection connection = provider.get();
    String query = "SELECT * FROM PEOPLE WHERE NAME LIKE '(?)%%%'";
    List<Person> result = new LinkedList<>();
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setString(1, letter);
      ResultSet rs = preparedStatement.executeQuery(query);
      while (rs.next()) {
        String name = rs.getString(1);
        Integer egn = rs.getInt(2);
        Integer age = rs.getInt(3);
        String email = rs.getString(4);
        result.add(new Person(name, egn, age, email));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        connection.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return result;
  }

  @Override
  public void updateAge(Integer egn, Integer newAge) {
    Connection connection = provider.get();
    String query = "UPDATE PEOPLE SET AGE = (?) WHERE EGN= (?)";
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setInt(1, newAge);
      preparedStatement.setInt(2, egn);
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

