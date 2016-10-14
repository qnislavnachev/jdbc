package com.clouway.core;

/**
 * @author Vasil Mitov <v.mitov.clouway@gmail.com>
 */
public class Person {
  public final String name;
  public final Integer egn;
  public final Integer age;
  public final String email;

  public Person(String name, Integer egn, Integer age, String email) {
    this.name = name;
    this.egn = egn;
    this.age = age;
    this.email = email;
  }

  @Override
  public String toString() {
    return "Person{" +
            "name='" + name + '\'' +
            ", egn=" + egn +
            ", age=" + age +
            ", email='" + email + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Person person = (Person) o;

    if (name != null ? !name.equals(person.name) : person.name != null) return false;
    if (egn != null ? !egn.equals(person.egn) : person.egn != null) return false;
    if (age != null ? !age.equals(person.age) : person.age != null) return false;
    return email != null ? email.equals(person.email) : person.email == null;

  }

  @Override
  public int hashCode() {
    int result = name != null ? name.hashCode() : 0;
    result = 31 * result + (egn != null ? egn.hashCode() : 0);
    result = 31 * result + (age != null ? age.hashCode() : 0);
    result = 31 * result + (email != null ? email.hashCode() : 0);
    return result;
  }
}
