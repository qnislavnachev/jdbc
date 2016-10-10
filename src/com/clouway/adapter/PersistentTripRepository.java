package com.clouway.adapter;

import com.clouway.core.Provider;
import com.clouway.core.Trip;
import com.clouway.core.TripRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
    String query = "DELETE FROM TRIP WHERE EGN=(?)";
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
    Connection connection=provider.get();
    String query="SELECT * FROM TRIP WHERE EGN=(?)";
    try(PreparedStatement preparedStatement=connection.prepareStatement(query)) {
    preparedStatement.setInt(1,egn);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
