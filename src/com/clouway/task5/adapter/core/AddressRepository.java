package com.clouway.task5.adapter.core;

import java.util.List;

/**
 * @author Vasil Mitov <v.mitov.clouway@gmail.com>
 */
public interface AddressRepository {
  void register(Address address);

  List<Address> getAll();
}
