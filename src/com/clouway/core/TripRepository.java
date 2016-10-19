package com.clouway.core;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * @author Vasil Mitov <v.mitov.clouway@gmail.com>
 */
public interface TripRepository {
  void register(Trip trip) throws SQLException;

  void delete(String egn) throws SQLException;

  Optional<Trip> find(String egn) throws SQLException;

  List<City> mostVisited() throws SQLException;

  List<Trip> display() throws SQLException;
}
