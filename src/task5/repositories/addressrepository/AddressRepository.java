package task5.repositories.addressrepository;


import task5.core.Address;

import java.util.List;

public interface AddressRepository {

    void register(Address address);

    List<Address> findAllAddresses();
}