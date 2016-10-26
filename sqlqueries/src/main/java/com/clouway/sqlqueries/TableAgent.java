package com.clouway.sqlqueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by clouway on 25.10.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class TableAgent {
    private final Connection dbConnection;

    public TableAgent(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public String getTableToString(String table) throws SQLException {
        PreparedStatement statement = dbConnection.prepareStatement("SELECT * FROM " + table);
        ResultSet resultSet = statement.executeQuery();
        StringBuilder stringBuilder = new StringBuilder();
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String userName = resultSet.getString(2);
            String password = resultSet.getString(3);
            String email = resultSet.getString(4);
            stringBuilder.append("\nID: ").append(id).append("\nUserName:").append(userName).append("\nPassword: ").append(password).append("\nEmail: ").append(email).append("\n");
        }
        return stringBuilder.toString();
    }

    public String getColumn(String column) throws SQLException {
        PreparedStatement statement = dbConnection.prepareStatement("SELECT " + column + " FROM users");
        ResultSet resultSet = statement.executeQuery();
        StringBuilder stringBuilder = new StringBuilder();
        while (resultSet.next()) {
            String result = resultSet.getString(1);
            stringBuilder.append("\n").append(column).append(": ").append(result);
        }
        return stringBuilder.toString();
    }

    public void updateColumn(String table, String column, String content, String columnForWhere, String newContent) throws SQLException {
        PreparedStatement statement = dbConnection.prepareStatement("UPDATE " + table + " SET " + column + " =" + newContent + " WHERE " + columnForWhere + " = " + content);
        statement.execute();
    }

    public void deleteContent(String table, String column, String content) throws SQLException {
        PreparedStatement statement = dbConnection.prepareStatement("DELETE FROM " + table + " WHERE " + column + " = " + content);
        statement.execute();
    }

    public void insetContent(String table, String columns, String values) throws SQLException {
        PreparedStatement statement = dbConnection.prepareStatement("INSERT INTO " + table + " ( " + columns +" ) " + "VALUES "+ " ( " + values + " ) ");
        statement.execute();
    }

    public void dropTable(String table) throws SQLException {
        PreparedStatement statement = dbConnection.prepareStatement("DROP TABLE " + table );
        statement.execute();
    }

    public void addColumn(String table,String column,String dataType) throws SQLException {
        PreparedStatement statement = dbConnection.prepareStatement("ALTER TABLE " + table + " ADD " + column + " "+ dataType);
        statement.execute();
    }

    public String getColumnByPattern(String table, String columns, String columnName, String pattern) throws SQLException {
        PreparedStatement statement = dbConnection.prepareStatement("SELECT "+ columns + " FROM "+ table + " WHERE " + columnName + " LIKE " + pattern  );
        StringBuilder stringBuilder = new StringBuilder();
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            String result = resultSet.getString(1);
            stringBuilder.append("\n").append(columnName).append(": ").append(result);
        }
        return stringBuilder.toString();
    }
}
