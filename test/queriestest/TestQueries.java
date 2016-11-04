package queriestest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import task1.Table;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class TestQueries {
    private Table table;
    private Connection connection;

    @Before
    public void setUp() throws Exception {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "iani");
        table = new Table(connection, "students");
        createTable();
    }

    @After
    public void tearDown() throws Exception {
        table.close();
    }

    private void createTable() throws SQLException {
        String tableQuery = "CREATE TABLE IF NOT EXISTS students (\n" +
                "    StudentID   int(5)          NOT NULL,\n" +
                "    Name        varchar(20)     NOT NULL,\n" +
                "    Age         int(5)          NOT NULL,\n" +
                "    Course      int(3)          NOT NULL,\n" +
                "    PRIMARY KEY(StudentID)\n" +
                ");";
        connection.createStatement().executeUpdate(tableQuery);
    }

    private void dropStudentsTable() throws SQLException {
        String query = "drop table students";
        connection.createStatement().executeUpdate(query);
    }

    private ResultSet createResultSet(String query) throws SQLException {
        return connection.createStatement().executeQuery(query);
    }

    @Test
    public void insertInfoToTable() throws Exception {
        table.insert(1, "Iani", 23, 2);
        ResultSet resultSet = createResultSet("select * from students where StudentID=1");
        String name = null;
        while (resultSet.next()) {
            name = resultSet.getString("Name");
        }
        assertThat(name, is("Iani"));
        dropStudentsTable();
    }

    @Test
    public void printTable() throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        table.insert(1, "Iani", 23, 3);
        table.printTable();
        String otuput = out.toString();
        assertTrue(otuput.contains("Iani"));
        System.setOut(System.out);
        out.close();
        dropStudentsTable();
    }

    @Test
    public void updateInfo() throws Exception {
        table.insert(1, "Qnis", 5, 1);
        table.update(1, "Iani", 23, 3);
        ResultSet resultSet = createResultSet("select * from students where StudentID=1");
        String name = null;
        while (resultSet.next()) {
            name = resultSet.getString("Name");
        }
        assertThat(name, is("Iani"));
        dropStudentsTable();
    }

    @Test
    public void selectingStudentsFromCourse() throws Exception {
        table.insert(1, "Iani", 23, 3);
        table.studentsFromCourse(3);
        ResultSet resultSet = createResultSet("select * from students where Course=3");
        String name = null;
        while (resultSet.next()) {
            name = resultSet.getString("Name");
        }
        assertThat(name, is("Iani"));
        dropStudentsTable();
    }

    @Test
    public void deleteInfo() throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        table.insert(1, "Iani", 23, 3);
        table.delete(1);
        table.printTable();
        String output = out.toString();
        assertFalse(output.contains("Iani"));
        System.setOut(System.out);
        out.close();
        dropStudentsTable();
    }

    @Test
    public void addColumn() throws Exception {
        table.addColumn("Location", "varchar(20)", "DEFAULT NULL");
        ResultSet resultSet = createResultSet("select Location from students where StudentID=1");
        String studentLocation = null;
        while (resultSet.next()) {
            studentLocation = resultSet.getString("Location");
        }
        assertThat(studentLocation, is(nullValue()));
        dropStudentsTable();
    }

    @Test
    public void dropTable() throws Exception {
        table.dropTable();
        ResultSet resultSet = createResultSet("show tables");
        String studentTable = null;
        while (resultSet.next()) {
            studentTable = resultSet.getString("Tables_in_db");
        }
        assertThat(studentTable, is(nullValue()));
    }
}