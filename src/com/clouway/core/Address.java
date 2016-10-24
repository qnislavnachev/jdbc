package com.clouway.core;

/**
 * @author Vasil Mitov <v.mitov.clouway@gmail.com>
 */
public class Address {
  public final String name;
  public final String city;
  public final String street;
  public final String residentialDistrict;
  public final Integer blockOfFlats;
  public final String entrance;

  public Address(String name, String city, String street, String residentialDistrict, Integer blockOfFlats, String entrance) {
    this.name = name;
    this.city = city;
    this.street = street;
    this.residentialDistrict = residentialDistrict;
    this.blockOfFlats = blockOfFlats;
    this.entrance = entrance;
  }

  @Override
  public String toString() {
    return "Address{" +
            "name='" + name + '\'' +
            ", city='" + city + '\'' +
            ", street='" + street + '\'' +
            ", residentialDistrict='" + residentialDistrict + '\'' +
            ", blockOfFlats=" + blockOfFlats +
            ", entrance='" + entrance + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Address address = (Address) o;

    if (name != null ? !name.equals(address.name) : address.name != null) return false;
    if (city != null ? !city.equals(address.city) : address.city != null) return false;
    if (street != null ? !street.equals(address.street) : address.street != null) return false;
    if (residentialDistrict != null ? !residentialDistrict.equals(address.residentialDistrict) : address.residentialDistrict != null)
      return false;
    if (blockOfFlats != null ? !blockOfFlats.equals(address.blockOfFlats) : address.blockOfFlats != null) return false;
    return entrance != null ? entrance.equals(address.entrance) : address.entrance == null;

  }

  @Override
  public int hashCode() {
    int result = name != null ? name.hashCode() : 0;
    result = 31 * result + (city != null ? city.hashCode() : 0);
    result = 31 * result + (street != null ? street.hashCode() : 0);
    result = 31 * result + (residentialDistrict != null ? residentialDistrict.hashCode() : 0);
    result = 31 * result + (blockOfFlats != null ? blockOfFlats.hashCode() : 0);
    result = 31 * result + (entrance != null ? entrance.hashCode() : 0);
    return result;
  }
}
