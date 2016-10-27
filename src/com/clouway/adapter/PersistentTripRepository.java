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
import java.util.Optional;

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
      preparedStatement.setString(1, trip.egn);
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
  public Optional<Trip> find(String egn) {
    Connection connection = provider.get();
    String query = "SELECT * FROM TRIP WHERE EGN ="+egn;
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      ResultSet resultSet = preparedStatement.executeQuery(query);
      while (resultSet.next()) {
        String EGN = resultSet.getString(1);
        Date arrival = resultSet.getDate(2);
        Date departure = resultSet.getDate(3);
        String city = resultSet.getString(4);
        return Optional.of(new Trip(EGN, arrival, departure, city));
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
    return Optional.empty();
  }

  @Override
  public List<City> mostVisited()  {
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
    return result;
  }
}
