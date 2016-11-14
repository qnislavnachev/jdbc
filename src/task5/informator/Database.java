package task5.informator;

import task5.core.Address;
import task5.core.Contact;
import task5.core.User;

import java.util.List;

public interface Database {

    void registerUser(User user);

    void registerContact(Contact contact);

    void registerAddress(Address address);

    List<User> findAllUsers();

    List<Contact> findAllContacts();

    List<Address> findAllAddresses();
}
