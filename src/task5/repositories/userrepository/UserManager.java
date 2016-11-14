package task5.repositories.userrepository;

import task5.core.User;
import task5.repositories.adapter.Adapter;
import task5.repositories.adapter.ResultSetAdapter;

import java.sql.*;
import java.util.List;

public class UserManager implements UserRepository {
    private Connection connection;
    private Adapter adapter;

    public UserManager(Connection connection) {
        this.connection = connection;
        adapter = new Adapter();
    }

    @Override
    public void registerUser(User user) {
        String query = "insert into Users values (?, ?, ?)";
        insertUser(query, user);
    }

    @Override
    public List<User> findAllUsers() {
        String query = "select * from Users";
        return adapter.listAdapter(connection, query, new ResultSetAdapter<User>() {
            @Override
            public User adapt(ResultSet rs) throws SQLException {
                return new User(rs.getInt("UserID"), rs.getString("Name"), rs.getInt("Age"));
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
}