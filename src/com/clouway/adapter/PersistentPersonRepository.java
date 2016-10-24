package com.clouway.adapter;

import com.clouway.core.Person;
import com.clouway.core.PersonRepository;
import com.clouway.core.Provider;

import java.sql.Connection;
import java.sql.Date;
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
      preparedStatement.setString(2, person.egn);
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
  public void delete(String egn) {
    Connection connection = provider.get();
    String query = "DELETE FROM PEOPLE WHERE EGN=(?)";
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setString(1, egn);
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
  public Optional<Person> find(String EGN) {
    Connection connection = provider.get();
    String query = "SELECT * FROM PEOPLE WHERE EGN=" + EGN;
    Optional result = Optional.empty();
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      ResultSet rs = preparedStatement.executeQuery();
      while (rs.next()) {
        String name = rs.getString(1);
        String egn = rs.getString(2);
        Integer age = rs.getInt(3);
        String email = rs.getString(4);
        result = Optional.of(new Person(name, egn, age, email));
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
  public List<Person> findAllStartingWith(String letter) {
    Connection connection = provider.get();
    String query = "SELECT * FROM PEOPLE WHERE NAME LIKE ?";
    List<Person> result = new LinkedList<>();
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setString(1, letter + "%");
      ResultSet rs = preparedStatement.executeQuery();
      while (rs.next()) {
        String name = rs.getString(1);
        String egn = rs.getString(2);
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
  public List<Person> findAllStayingAtTheSameTime(String city, Date date) {
    Connection connection = provider.get();
    String query = "SELECT * FROM PEOPLE INNER JOIN TRIP ON PEOPLE.EGN=TRIP.EGN WHERE TRIP.CITY=? AND TRIP.ARRIVAL < ? AND ? > TRIP.ARRIVAL";
    List<Person> result = new LinkedList<>();
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setString(1,city);
      preparedStatement.setDate(2,date);
      preparedStatement.setDate(3,date);
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        result.add(new Person(resultSet.getString(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getString(4)));
      }
      return result;

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return result;
  }
}

