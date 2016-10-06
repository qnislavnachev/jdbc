package com.clouway.core;

/**
 * @author Vasil Mitov <v.mitov.clouway@gmail.com>
 */
public interface PeopleRepository {
  void add(Person person);

  void delete(Person person);

  void updateField(Person person,String field,Object value);

  String findPeopleStartingWithLetter(String letter);

  String findByEGN(Integer EGN);

  String display(String table);
}
