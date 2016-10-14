package com.clouway.core;

import java.util.List;
import java.util.Optional;

/**
 * @author Vasil Mitov <v.mitov.clouway@gmail.com>
 */
public interface PersonRepository {
  void register(Person person);

  void delete(Person person);

  Optional<Person> find(Integer egn);

  List<Person> findAll(String letter);

  void updateAge(Integer egn, Integer newAge);

}
