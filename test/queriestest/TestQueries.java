package queriestest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import task1.Student;
import task1.StudentsRepository;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class TestQueries {
    private StudentsRepository students;
    private Connection connection;

    @Before
    public void setUp() throws Exception {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "iani");
        students = new StudentsRepository(connection);
        createTable();
    }

    @After
    public void tearDown() throws Exception {
        students.close();
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
    public void insertInfoToStudents() throws Exception {
        Student iani = new Student(1, "Iani", 23, 2);
        students.register(iani);
        ResultSet resultSet = createResultSet("select * from students where StudentID=1");
        String name = null;
        while (resultSet.next()) {
            name = resultSet.getString("Name");
        }
        assertThat(name, is("Iani"));
        dropStudentsTable();
    }

    @Test
    public void findStudents() throws Exception {
        Student iani = new Student(1, "Iani", 23, 2);
        students.register(iani);
        List<Student> studentList = students.findStudents();
        assertThat(studentList.get(0), is(iani));
        dropStudentsTable();
    }

    @Test
    public void updateInfo() throws Exception {
        Student iani = new Student(1, "Iani", 23, 2);
        students.register(iani);
        students.update(iani, "Age=2");
        ResultSet resultSet = createResultSet("select * from students where StudentID=1");
        int age = 0;
        while (resultSet.next()) {
            age = resultSet.getInt("Age");
        }
        assertThat(age, is(2));
        dropStudentsTable();
    }

    @Test
    public void selectingStudentsFromCourse() throws Exception {
        Student iani = new Student(1, "Iani", 23, 2);
        students.register(iani);
        students.studentsFromCourse(2);
        ResultSet resultSet = createResultSet("select * from students where Course=2");
        String name = null;
        while (resultSet.next()) {
            name = resultSet.getString("Name");
        }
        assertThat(name, is("Iani"));
        dropStudentsTable();
    }

    @Test
    public void deleteInfo() throws Exception {
        Student iani = new Student(1, "Iani", 23, 2);
        students.register(iani);
        students.delete(iani);
        List<Student> studentList = students.findStudents();
        assertTrue(studentList.isEmpty());
        dropStudentsTable();
    }

    @Test
    public void addColumn() throws Exception {
        students.addColumn("Location", "varchar(20)", "DEFAULT NULL");
        ResultSet resultSet = createResultSet("select Location from students where StudentID=1");
        String studentLocation = null;
        while (resultSet.next()) {
            studentLocation = resultSet.getString("Location");
        }
        assertThat(studentLocation, is(nullValue()));
        dropStudentsTable();
    }

    @Test
    public void dropStudents() throws Exception {
        students.dropStudentsTable();
        ResultSet resultSet = createResultSet("show tables");
        String studentTable = null;
        while (resultSet.next()) {
            studentTable = resultSet.getString("Tables_in_db");
        }
        assertThat(studentTable, is(nullValue()));
    }
}