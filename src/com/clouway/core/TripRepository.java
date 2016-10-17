package com.clouway.core;

import java.util.List;

/**
 * @author Vasil Mitov <v.mitov.clouway@gmail.com>
 */
public interface TripRepository {
  void register(Trip trip);

  void delete(Trip trip);

  Trip find(String egn);

  List<City> mostVisited();

  List<Trip> display();
}
