package com.clouway.task5.adapter.core;

/**
 * @author Vasil Mitov <v.mitov.clouway@gmail.com>
 */
public class User {
  public final String name;
  public final Integer age;
  public final String userStatus;

  public User(String name, Integer age, String userStatus) {
    this.name = name;
    this.age = age;
    this.userStatus = userStatus;
  }

  @Override
  public String toString() {
    return "User{" +
            "name='" + name + '\'' +
            ", age=" + age +
            ", userStatus='" + userStatus + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    User user = (User) o;

    if (name != null ? !name.equals(user.name) : user.name != null) return false;
    if (age != null ? !age.equals(user.age) : user.age != null) return false;
    return userStatus != null ? userStatus.equals(user.userStatus) : user.userStatus == null;

  }

  @Override
  public int hashCode() {
    int result = name != null ? name.hashCode() : 0;
    result = 31 * result + (age != null ? age.hashCode() : 0);
    result = 31 * result + (userStatus != null ? userStatus.hashCode() : 0);
    return result;
  }
}
