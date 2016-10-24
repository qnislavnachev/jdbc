package com.clouway.core;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * @author Vasil Mitov <v.mitov.clouway@gmail.com>
 */
public interface TripRepository {
  void register(Trip trip);

  void delete(String egn);

  Optional<Trip> find(String egn);

  List<City> mostVisited();

  List<Trip> display();
}
