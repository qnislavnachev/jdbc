package task1;

import java.sql.*;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentsRepository {
    private Statement statement;
    private Connection connection;

    public StudentsRepository(Connection connection) throws SQLException {
        this.connection = connection;
        statement = connection.createStatement();
    }

    public List<Student> findStudents() throws SQLException {
        List<Student> list = new ArrayList<>();
        String query = "select * from students";
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            Student student = new Student(resultSet.getInt("StudentID"),
                    resultSet.getString("Name"),
                    resultSet.getInt("Age"),
                    resultSet.getInt("Course"));
            list.add(student);
        }
        return list;
    }

    public void update(Student student, String updateInfo) throws SQLException {
        String query = "update students set " + updateInfo + " where StudentID=" + student.ID;
        statement.executeUpdate(query);
    }

    public void delete(Student student) throws SQLException {
        String query = "delete from students where StudentID=" + student.ID;
        statement.executeUpdate(query);
    }

    public void register(Student student) throws SQLException {
        String query = "insert into students (StudentID, Name, Age, Course)" +
                " values (" + student.ID + ", '" + student.name + "', " + student.age + ", " + student.course + ")";
        statement.executeUpdate(query);
    }

    public void dropStudentsTable() throws SQLException {
        String query = "drop table students";
        statement.executeUpdate(query);
    }

    public void addColumn(String columnName, String dataType, String value) throws SQLException {
        String query = "alter table students add " + columnName + " " + dataType + " " + value;
        statement.executeUpdate(query);
    }

    public void studentsFromCourse(int course) throws SQLException {
        String query = "select * from students where Course like '" + course + "'";
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