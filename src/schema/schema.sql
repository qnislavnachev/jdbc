<<<<<<< HEAD
CREATE TABLE IF NOT EXISTS Cars (
    RegistrationNum          int(10)         NOT NULL        AUTO_INCREMENT,
    Name                     varchar(20)     NOT NULL,
    Model                    varchar(20)     NOT NULL,
    Color                    varchar(20)     NOT NULL,
    PRIMARY KEY(RegistrationNum)
);

CREATE TABLE IF NOT EXISTS CarsHistory(
    RegistrationNum          int(10)         NOT NULL,
    Name                     varchar(20)     NOT NULL,
    Model                    varchar(20)     NOT NULL,
    Color                    varchar(20)     NOT NULL,
    PRIMARY KEY(RegistrationNum)
);

CREATE TRIGGER updateCars BEFORE UPDATE ON Cars
    FOR EACH ROW
    INSERT INTO CarsHistory
    VALUES (OLD.RegistrationNum, OLD.Name, OLD.Model, OLD.Color);
=======
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
>>>>>>> 995c3ace0a225457723fb8b7544604de554f523e
