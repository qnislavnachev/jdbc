package task1;

public class Student {
    public final int ID;
    public final String name;
    public final int age;
    public final int course;

    public Student(int ID, String name, int age, int course) {
        this.ID = ID;
        this.name = name;
        this.age = age;
        this.course = course;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (ID != student.ID) return false;
        if (age != student.age) return false;
        if (course != student.course) return false;
        return name != null ? name.equals(student.name) : student.name == null;

    }

    @Override
    public String toString() {
        return "Student ID" + ID + ", Name=" + name + ", Age=" + age + ", Course=" + course;
    }
}