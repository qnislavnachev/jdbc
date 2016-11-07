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
    public String toString() {
        return "Student ID" + ID + ", Name=" + name + ", Age=" + age + ", Course=" + course;
    }
}