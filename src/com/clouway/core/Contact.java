package com.clouway.core;

/**
 * @author Vasil Mitov <v.mitov.clouway@gmail.com>
 */
public class Contact {
  public final String name;
  public final String phone;
  public final String email;

  public Contact(String name, String phone, String email) {
    this.name = name;
    this.phone = phone;
    this.email = email;
  }

  @Override
  public String toString() {
    return "Contact{" +
            "name='" + name + '\'' +
            ", phone='" + phone + '\'' +
            ", email='" + email + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Contact contact = (Contact) o;

    if (name != null ? !name.equals(contact.name) : contact.name != null) return false;
    if (phone != null ? !phone.equals(contact.phone) : contact.phone != null) return false;
    return email != null ? email.equals(contact.email) : contact.email == null;

  }

  @Override
  public int hashCode() {
    int result = name != null ? name.hashCode() : 0;
    result = 31 * result + (phone != null ? phone.hashCode() : 0);
    result = 31 * result + (email != null ? email.hashCode() : 0);
    return result;
  }
}
