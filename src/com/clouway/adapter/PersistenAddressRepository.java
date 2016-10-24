package com.clouway.adapter;

import com.clouway.core.Address;
import com.clouway.core.AddressRepository;
import com.clouway.datastore.DataStore;
import com.clouway.datastore.RowFetcher;

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
    String query="INSERT INTO ADDRESS (?,?,?)";
    dataStore.update(query,address.name,address.city,address.street,address.residentialDistrict,address.blockOfFlats,address.entrance);
  }

  @Override
  public List<Address> getAll() {
    String query="SELECT * FROM ADDRESS";
    return dataStore.fetchRows(query, resultSet -> new Address(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getInt(5),resultSet.getString(6)));
    }
  }




