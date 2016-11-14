package agency;


import core.City;
import core.Person;
import core.Trip;

import java.util.Date;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TourAgent implements TravelAgency {
    private Connection connection;

    public TourAgent(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void scheduleTrip(Person person, String city, Date from, Date to) {
        String insertPerson = "insert into People values (?, ?, ? ,?)";
        String insertTrip = "insert into Trip values (?, ?, ? ,?)";
        try {
            connection.setAutoCommit(false);
            executePersonInsert(insertPerson, person);
            executeTripInsert(insertTrip, new Trip(person.id, from, to, city));
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            rollback();
        }
    }

    @Override
    public List<City> findAllVisitedDestination() {
        String query = "select distinct City from Trip";
        List<City> list = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                list.add(new City(rs.getString(1)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Person> findPersonsGotSameTrip(Date from, Date to, String city) {
        String query = "select People.* from People inner join Trip" +
                " on Trip.PersonID = People.PersonID" +
                " where Trip.ArrivalDate <= ? " +
                "and Trip.DepartureDate >= ? and Trip.City = ?";
        List<Person> list = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, toSqlDate(from).toString());
            statement.setString(2, toSqlDate(to).toString());
            statement.setString(3, city);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                list.add(new Person(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private java.sql.Date toSqlDate(Date date) {
        return new java.sql.Date(date.getTime());
    }

    private void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    private void executePersonInsert(String query, Person person) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, person.id);
        statement.setString(2, person.name);
        statement.setInt(3, person.age);
        statement.setString(4, person.email);
        statement.executeUpdate();
        statement.close();
    }


    private void executeTripInsert(String query, Trip trip) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, trip.personID);
        statement.setString(2, toSqlDate(trip.arrivalDate).toString());
        statement.setString(3, toSqlDate(trip.departureDate).toString());
        statement.setString(4, trip.city);
        statement.executeUpdate();
        statement.close();
    }
}