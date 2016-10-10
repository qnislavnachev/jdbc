package com.clouway.core;

import java.sql.Date;

/**
 * @author Vasil Mitov <v.mitov.clouway@gmail.com>
 */
public class Trip {
  public final Integer egn;
  public final Date arrival;
  public final Date departure;
  public final String city;

  public Trip(Integer egn, Date arrival, Date departure, String city) {
    this.egn = egn;
    this.arrival = arrival;
    this.departure = departure;
    this.city = city;
  }
}
