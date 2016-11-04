CREATE TABLE IF NOT EXISTS People (
    PersonID            int(10)             NOT NULL,
    Name                varchar(30)         NOT NULL,
    Age                 int(3)              NOT NULL,
    Email              varchar(30)          NOT NULL,
    PRIMARY KEY(PersonID)
);

CREATE TABLE IF NOT EXISTS Trip (
    PersonID            int(10)             NOT NULL,
    ArrivalDate         Date                NOT NULL,
    DepartureDate       Date                NOT NULL,
    City                varchar(20)         NOT NULL,
    FOREIGN KEY(PersonID) REFERENCES People(PersonID)
);