package com.clouway.core;

/**
 * @author Vasil Mitov <v.mitov.clouway@gmail.com>
 */
public interface TripRepository {
  void register(Trip trip);

  void delete(Trip trip);

  Trip find(Integer egn);

}
