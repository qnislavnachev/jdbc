package task5.repositories.contactrepository;

import task5.core.Contact;

import java.util.List;

public interface ContactRepository {

    void register(Contact contact);

    List<Contact> findAllContacts();
}