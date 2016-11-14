package task5.informator;

import task5.core.Address;
import task5.core.Contact;
import task5.core.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Manager implements Database {
    private Connection connection;

    public Manager(Connection connection) {
        this.connection = connection;
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
        return getAdaptedList(query, Objects.USER);
    }

    @Override
    public List<Contact> findAllContacts() {
        String query = "select * from Contacts";
        return getAdaptedList(query, Objects.CONTACT);
    }

    @Override
    public List<Address> findAllAddresses() {
        String query = "select * from Addresses";
        return getAdaptedList(query, Objects.ADDRESS);
    }

    private List getAdaptedList(String query, Enum e) {
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            if (e.equals(Objects.USER)) {
                List<User> list = new ArrayList<>();
                while (rs.next()) {
                    list.add(new User(rs.getInt(1), rs.getString(2), rs.getInt(3)));
                }
                return list;
            }
            if (e.equals(Objects.CONTACT)) {
                List<Contact> list = new ArrayList<>();
                while (rs.next()) {
                    list.add(new Contact(rs.getInt(1), rs.getString(2)));
                }
                return list;
            }
            if (e.equals(Objects.ADDRESS)) {
                List<Address> list = new ArrayList<>();
                while (rs.next()) {
                    list.add(new Address(rs.getString(2)));
                }
                return list;
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return null;
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