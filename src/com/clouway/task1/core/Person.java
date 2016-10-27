package com.clouway.task1.core;

/**
 * @author Vasil Mitov <v.mitov.clouway@gmail.com>
 */
public class Person {
  public final String name;
  public final String egn;
  public final Integer age;
  public final String email;

  public Person(String name, String egn, Integer age, String email) {
    this.name = name;
    this.egn = egn;
    this.age = age;
    this.email = email;
  }
}
