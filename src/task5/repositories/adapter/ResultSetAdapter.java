package task5.repositories.adapter;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultSetAdapter<T> {
    T adapt(ResultSet resultSet) throws SQLException;
}