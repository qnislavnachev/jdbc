CREATE TABLE IF NOT EXISTS students (
    StudentID   int(5)          NOT NULL,
    Name        varchar(20)     NOT NULL,
    Age         int(5)          NOT NULL,
    Course      int(3)          NOT NULL,
    PRIMARY KEY(StudentID)
);