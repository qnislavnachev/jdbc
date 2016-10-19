package com.clouway.core;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * @author Vasil Mitov <v.mitov.clouway@gmail.com>
 */
public interface PersonRepository {
  void register(Person person) throws SQLException;

  void delete(String egn) throws SQLException;

  Optional<Person> find(String egn) throws SQLException;

  List<Person> findAllStartingWith(String letter) throws SQLException;

  List<Person> display() throws SQLException;
}
