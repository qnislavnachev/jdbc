CREATE TABLE IF NOT EXISTS Users (
    UserID          int(5)          NOT NULL,
    Name            varchar(20)     NOT NULL,
    Age             int(3)          NOT NULL,
    PRIMARY KEY(UserID)
);

CREATE TABLE IF NOT EXISTS Addresses (
    Address         varchar(20)     NOT NULL,
    PRIMARY KEY(Address)
);

CREATE TABLE IF NOT EXISTS Contacts (
    ContactID       int(5)          NOT NULL,
    ContactName     varchar(20)     NOT NULL,
    PRIMARY KEY(ContactID)
);