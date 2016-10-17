package com.clouway.core;

import java.util.List;

/**
 * @author Vasil Mitov <v.mitov.clouway@gmail.com>
 */
public interface PersonRepository {
  void register(Person person);

  void delete(Person person);

  Person find(String egn);

  List<Person> findAll(String letter);

  List<Person> display();
}
