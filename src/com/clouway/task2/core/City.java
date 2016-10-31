package com.clouway.task2.core;

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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    City city = (City) o;

    if (name != null ? !name.equals(city.name) : city.name != null) return false;
    return visited != null ? visited.equals(city.visited) : city.visited == null;

  }

  @Override
  public int hashCode() {
    int result = name != null ? name.hashCode() : 0;
    result = 31 * result + (visited != null ? visited.hashCode() : 0);
    return result;
  }
}
