package task1;

import java.sql.*;

public class Table {
    private Statement statement;
    private Connection connection ;
    private String table;


    public Table(Connection connection, String table) throws SQLException {
        this.table = table;
        this.connection = connection;
        statement = connection.createStatement();
    }

    public void printTable() throws SQLException {
        String query = "select * from " + table;
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            System.out.println(resultSet.getInt("StudentID") + " " +
                    resultSet.getString("Name") + " " +
                    resultSet.getInt("Age") + " " +
                    resultSet.getInt("Course"));
        }
    }

    public void update(int ID, String newName, int newAge, int newCourse) throws SQLException {
        String query = "update " + table + " set Name='" + newName + "'" +
                ", Age=" + newAge + ", Course=" + newCourse +
                " where StudentID=" + ID;
        statement.executeUpdate(query);
    }

    public void delete(int ID) throws SQLException {
        String query = "delete from " + table + " where StudentID=" + ID;
        statement.executeUpdate(query);
    }

    public void insert(int ID, String name, int age, int course) throws SQLException {
        String query = "insert into " + table + " (StudentID, Name, Age, Course)" +
                " values (" + ID + ", '" + name + "', " + age + ", " + course + ")";
        statement.executeUpdate(query);
    }

    public void dropTable() throws SQLException {
        String query = "drop table " + table;
        statement.executeUpdate(query);
    }

    public void addColumn(String columnName, String dataType, String value) throws SQLException {
        String query = "alter table " + table + " add " + columnName + " " + dataType + " " + value;
        statement.executeUpdate(query);
    }

    public void studentsFromCourse(int course) throws SQLException {
        String query = "select * from " + table + " where Course like '" + course + "'";
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            System.out.println(resultSet.getString("Name"));
        }
    }

    public void close() throws SQLException {
        statement.close();
        connection.close();
    }
}