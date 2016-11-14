package task5.informator;

import task5.core.Address;
import task5.core.Contact;
import task5.core.User;

import java.sql.*;
import java.util.List;

public class Manager implements Database {
    private Connection connection;
    private Adapter adapter;

    public Manager(Connection connection) {
        this.connection = connection;
        adapter = new Adapter();
    }

    @Override
    public void registerUser(User user) {
        String query = "insert into Users values (?, ?, ?)";
        insertUser(query, user);
    }

    @Override
    public void registerContact(Contact contact) {
        String query = "insert into Contacts values (?, ?)";
        insertContact(query, contact);
    }

    @Override
    public void registerAddress(Address address) {
        String query = "insert into Addresses values (?)";
        insertAddress(query, address);
    }

    @Override
    public List<User> findAllUsers() {
        String query = "select * from Users";
        return adapter.listAdapter(connection, query, new ResultSetAdapter<User>() {
            @Override
            public User adapt(ResultSet rs) throws SQLException {
                return new User(rs.getInt(1), rs.getString(2), rs.getInt(3));
            }
        });
    }

    @Override
    public List<Contact> findAllContacts() {
        String query = "select * from Contacts";
        return adapter.listAdapter(connection, query, new ResultSetAdapter<Contact>() {
            @Override
            public Contact adapt(ResultSet rs) throws SQLException {
                return new Contact(rs.getInt(1), rs.getString(2));
            }
        });
    }

    @Override
    public List<Address> findAllAddresses() {
        String query = "select * from Addresses";
        return adapter.listAdapter(connection, query, new ResultSetAdapter<Address>() {
            @Override
            public Address adapt(ResultSet resultSetAdapter) throws SQLException {
                return new Address(resultSetAdapter.getString(1));
            }
        });
    }

    private void insertUser(String query, User user) {
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, user.id);
            statement.setString(2, user.name);
            statement.setInt(3, user.age);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    private void insertAddress(String query, Address address) {
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, address.addressName);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}