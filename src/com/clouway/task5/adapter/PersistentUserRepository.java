package com.clouway.task5.adapter;

import com.clouway.task5.core.User;
import com.clouway.task5.core.UserRepository;
import com.clouway.task5.datastore.DataStore;

import java.util.List;

/**
 * @author Vasil Mitov <v.mitov.clouway@gmail.com>
 */
public class PersistentUserRepository implements UserRepository {
  private final DataStore dataStore;

  public PersistentUserRepository(DataStore dataStore) {
    this.dataStore = dataStore;
  }

  @Override
  public void register(User user) {
    String query = "INSERT INTO USER VALUES (?,?,?)";
    dataStore.update(query, user.name, user.age, user.userStatus);
  }

  @Override
  public List<User> getAll() {
    String query = "SELECT * FROM USER";
    return dataStore.fetchRows(query, resultSet -> new User(resultSet.getString(1), resultSet.getInt(2), resultSet.getString(3)));
  }
}
