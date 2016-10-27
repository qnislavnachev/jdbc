package com.clouway.task5.adapter;

import com.clouway.task5.adapter.core.Address;
import com.clouway.task5.adapter.core.AddressRepository;
import com.clouway.task5.adapter.datastore.DataStore;

import java.util.List;

/**
 * @author Vasil Mitov <v.mitov.clouway@gmail.com>
 */
public class PersistenAddressRepository implements AddressRepository {

  private final DataStore dataStore;

  public PersistenAddressRepository(DataStore dataStore) {
    this.dataStore = dataStore;
  }

  @Override
  public void register(Address address) {
    String query="INSERT INTO ADDRESS VALUES (?,?,?,?,?,?)";
    dataStore.update(query,address.name,address.city,address.street,address.residentialDistrict,address.blockOfFlats,address.entrance);
  }

  @Override
  public List<Address> getAll() {
    String query="SELECT * FROM ADDRESS";
    return dataStore.fetchRows(query, resultSet -> new Address(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getInt(5),resultSet.getString(6)));
    }
  }




