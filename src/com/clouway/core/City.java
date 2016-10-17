package com.clouway.core;

/**
 * @author Vasil Mitov <v.mitov.clouway@gmail.com>
 */
public class City {
  public final String name;
  public Integer visited = 0;

  public City(String name,Integer visited) {
    this.name = name;
    this.visited=visited;
  }

  @Override
  public String toString() {
    return "City{" +
            "name='" + name + '\'' +
            ", visited=" + visited +
            '}';
  }
}
