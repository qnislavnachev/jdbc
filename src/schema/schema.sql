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