package queriestest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import task1.Queries;

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
    private Queries queries;
    private Connection connection;
    private ResultSet resultSet;
    private String actual = null;
    private ByteArrayOutputStream out;

    @Before
    public void setUp() throws Exception {
        out = new ByteArrayOutputStream();
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "iani");
        queries = new Queries(connection, "students");
    }

    @After
    public void tearDown() throws Exception {
        queries.close();
    }

    private ResultSet createResultSet(String query) throws SQLException {
        return connection.createStatement().executeQuery(query);
    }

    @Test
    public void printTable() throws Exception {
        System.setOut(new PrintStream(out));
        queries.printTable();
        String otuput = out.toString();
        assertTrue(otuput.contains("Iani"));
        assertTrue(otuput.contains("Maggie"));
        assertTrue(otuput.contains("Kika"));
        System.setOut(System.out);
    }

    @Test
    public void insertInfoToTable() throws Exception {
        queries.insert(3, "Maggie", 18, 2);
        resultSet = createResultSet("select * from students where StudentID=3");
        while (resultSet.next()) {
            actual = resultSet.getString("Name");
        }
        assertThat(actual, is("Maggie"));
    }

    @Test
    public void updateInfo() throws Exception {
        queries.update(1, "Iani", 23, 3);
        resultSet = createResultSet("select * from students where StudentID=1");
        while (resultSet.next()) {
            actual = resultSet.getString("Name");
        }
        assertThat(actual, is("Iani"));
    }

    @Test
    public void selectingStudentsFromCourse() throws Exception {
        queries.selectStudentsFrom(3);
        resultSet = createResultSet("select * from students where Course=3");
        while (resultSet.next()) {
            actual = resultSet.getString("Name");
        }
        assertThat(actual, is("Iani"));
    }

    @Test
    public void deleteInfo() throws Exception {
        System.setOut(new PrintStream(out));
        queries.delete(3);
        queries.printTable();
        String output = out.toString();
        assertFalse(output.contains("Maggie"));
        System.setOut(System.out);
    }

    @Test
    public void addColumn() throws Exception {
        queries.addColumn("Location", "varchar(20)");
        resultSet = createResultSet("select Location from students where StudentID=1");
        while (resultSet.next()) {
            actual = resultSet.getString("Location");
        }
        assertThat(actual, is(nullValue()));
    }

    @Test
    public void dropTable() throws Exception {
        queries.dropTable();
        resultSet = createResultSet("show tables");
        while (resultSet.next()) {
            actual = resultSet.getString("Tables_in_db");
        }
        assertThat(actual, is(nullValue()));
    }
}