package com.clouway.main;

import com.clouway.adapter.ConnectionProvider;
import com.clouway.adapter.PersistentPeopleRepository;
import com.clouway.core.Person;

/**
 * @author Vasil Mitov <v.mitov.clouway@gmail.com>
 */
public class Demo {
  public static void main(String[] args) {
    ConnectionProvider connectionProvider = new ConnectionProvider("TASK1");
    PersistentPeopleRepository repository = new PersistentPeopleRepository(connectionProvider);
    System.out.println(repository.display("PEOPLE"));
    System.out.println();
    System.out.println(repository.findPeopleStartingWithLetter("V"));
    System.out.println(repository.findByEGN(1111111113));
  }
}
