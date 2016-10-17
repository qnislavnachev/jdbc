package com.clouway;

import com.clouway.adapter.ConnectionProvider;
import com.clouway.adapter.PersistentPersonRepository;
import com.clouway.core.Person;

/**
 * @author Vasil Mitov <v.mitov.clouway@gmail.com>
 */
public class Demo {
  public static void main(String[] args) {
    ConnectionProvider connection= new ConnectionProvider("TASK1");
    PersistentPersonRepository personRepository=new PersistentPersonRepository(connection);
    Person person=new Person("Vasko","1234567890",24,"vasko@vasko.com");
    personRepository.register(person);
  }
}
