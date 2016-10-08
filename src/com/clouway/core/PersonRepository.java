package com.clouway.core;

/**
 * @author Vasil Mitov <v.mitov.clouway@gmail.com>
 */
public interface PersonRepository {
  void register(Person person);

  void delete(Person person);

  void updateAge(Integer egn, String field, Integer age);

}
