package task5.repositories.contactrepository;


import task5.core.Contact;
import task5.repositories.adapter.Adapter;
import task5.repositories.adapter.ResultSetAdapter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ContactManager implements ContactRepository {
    private Connection connection;
    private Adapter adapter;

    public ContactManager(Connection connection) {
        this.connection = connection;
        adapter = new Adapter();
    }

    @Override
    public void register(Contact contact) {
        String query = "insert into Contacts values(?,?)";
        insertContact(query, contact);
    }

    @Override
    public List<Contact> findAllContacts() {
        String query = "select * from Contacts";
        return adapter.listAdapter(connection, query, new ResultSetAdapter<Contact>() {
            @Override
            public Contact adapt(ResultSet rs) throws SQLException {
                return new Contact(rs.getInt("ContactID"), rs.getString("ContactName"));
            }
        });
    }

    private void insertContact(String query, Contact contact) {
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, contact.id);
            statement.setString(2, contact.name);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}