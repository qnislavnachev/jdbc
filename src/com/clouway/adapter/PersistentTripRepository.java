package com.clouway.adapter;

import com.clouway.core.City;
import com.clouway.core.Provider;
import com.clouway.core.Trip;
import com.clouway.core.TripRepository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Vasil Mitov <v.mitov.clouway@gmail.com>
 */
public class PersistentTripRepository implements TripRepository {
  private Provider<Connection> provider;

  public PersistentTripRepository(Provider<Connection> provider) {
    this.provider = provider;
  }

  @Override
  public void register(Trip trip) {
    Connection connection = provider.get();
    String query = "INSERT INTO TRIP VALUES(?,?,?,?)";
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setInt(1, trip.egn);
      preparedStatement.setDate(2, trip.arrival);
      preparedStatement.setDate(3, trip.departure);
      preparedStatement.setString(4, trip.city);
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
  public void delete(Trip trip) {
    Connection connection = provider.get();
    String query = "DELETE FROM TRIP WHERE EGN= ?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setInt(1, trip.egn);
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
  public Trip find(Integer egn) {
    Connection connection = provider.get();
    String query = "SELECT * FROM TRIP WHERE NAME= ? ";
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setString(1, "Vasko");
      ResultSet resultSet = preparedStatement.executeQuery(query);
      Integer EGN = resultSet.getInt(1);
      Date arrival = resultSet.getDate(2);
      Date departure = resultSet.getDate(3);
      String city = resultSet.getString(4);
      return new Trip(EGN, arrival, departure, city);
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        connection.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return null;
  }

  @Override
  public List<City> mostVisited() {
    Connection connection = provider.get();
    String query = "SELECT CITY, COUNT(*) FROM TRIP GROUP BY CITY ORDER BY CITY ASC";
    List<City> result = new LinkedList<>();
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      ResultSet rs = preparedStatement.executeQuery(query);
      while (rs.next()){
        String name=rs.getString(1);
        Integer count=rs.getInt(2);
        result.add(new City(name,count));
      }
      return result;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    finally {
      try {
        connection.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return null;
  }

  @Override
  public List<Trip> display() {
    Connection connection = provider.get();
    String query = "SELECT * FROM TRIP";
    List<Trip> result = new LinkedList<>();
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      ResultSet rs = preparedStatement.executeQuery(query);
      while (rs.next()) {
        Integer egn = rs.getInt(1);
        Date arrival = rs.getDate(2);
        Date departure = rs.getDate(3);
        String city = rs.getString(4);
        result.add(new Trip(egn, arrival, departure, city));
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
  public void deleteTableContents() {
    Connection connection = provider.get();
    String query = "DELETE FROM TRIP";
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
