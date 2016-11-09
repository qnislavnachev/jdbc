package task1;

import java.sql.*;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentsRepository {
    private Connection connection;

    public StudentsRepository(Connection connection) {
        this.connection = connection;
    }

    public List<Student> findStudents() {
        String query = "select * from students";
        return getStudentsFrom(query);
    }

    public void updateByID(int ID, String updateInfo) {
        String query = "update students set " + updateInfo + " where StudentID=" + ID;
        execute(query);
    }

    public void deleteByID(int ID) {
        String query = "delete from students where StudentID=" + ID;
        execute(query);
    }

    public Student register(Student student) {
        String query = "insert into students (StudentID, Name, Age, Course)" +
                " values (" + student.ID + ", '" + student.name + "', " + student.age + ", " + student.course + ")";
        execute(query);
        return student;
    }

    public void dropStudentsTable() {
        String query = "drop table students";
        execute(query);
    }

    public void addColumn(String columnName, String dataType, String value) {
        String query = "alter table students add " + columnName + " " + dataType + " " + value;
        execute(query);
    }

    public List<Student> studentsFromCourse(int course) {
        String query = "select * from students where Course like '" + course + "'";
        return getStudentsFrom(query);
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<Student> getStudentsFrom(String query) {
        List<Student> list = new ArrayList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                list.add(new Student(resultSet.getInt("StudentID"),
                        resultSet.getString("Name"),
                        resultSet.getInt("Age"),
                        resultSet.getInt("Course")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    private void execute(String query) {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}