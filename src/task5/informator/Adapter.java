package task5.informator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Adapter {

    public <T> List<T> listAdapter(Connection connection, String query, ResultSetAdapter<T> adapter) {
        List<T> list = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                list.add(adapter.adapt(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}