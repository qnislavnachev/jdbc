package com.clouway.task5.adapter;

import com.clouway.task5.core.Contact;
import com.clouway.task5.core.ContactRepository;
import com.clouway.task5.datastore.DataStore;

import java.util.List;

/**
 * @author Vasil Mitov <v.mitov.clouway@gmail.com>
 */
public class PersistentContactRepository implements ContactRepository {

  private final DataStore dataStore;

  public PersistentContactRepository(DataStore dataStore) {
    this.dataStore = dataStore;
  }

  @Override
  public void register(Contact contact) {
    String query="INSERT INTO CONTACT VALUES (?,?,?)";
    dataStore.update(query,contact.name,contact.phone,contact.email);
  }

  @Override
  public List<Contact> getAll() {
    String query="SELECT * FROM CONTACT";
    return dataStore.fetchRows(query,resultSet -> new Contact(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3)));
  }
}
