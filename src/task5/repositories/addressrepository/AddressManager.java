package task5.repositories.addressrepository;

import task5.core.Address;
import task5.repositories.adapter.Adapter;
import task5.repositories.adapter.ResultSetAdapter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AddressManager implements AddressRepository {
    private Connection connection;
    private Adapter adapter;

    public AddressManager(Connection connection) {
        this.connection = connection;
        adapter = new Adapter();
    }

    @Override
    public void register(Address address) {
        String query = "insert into Addresses values (?)";
        insertAddress(query, address);
    }

    @Override
    public List<Address> findAllAddresses() {
        String query = "select * from Addresses";
        return adapter.listAdapter(connection, query, new ResultSetAdapter<Address>() {
            @Override
            public Address adapt(ResultSet rs) throws SQLException {
                return new Address(rs.getString("Address"));
            }
        });
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