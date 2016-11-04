package agency;


import core.City;
import core.Person;
import core.Trip;

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
            execute(insertPerson, person);
            execute(insertTrip, new Trip(person.id, from, to, city));
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
            statement.setDate(1, from);
            statement.setDate(2, to);
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

    private void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    private void execute(String query, Object o) throws SQLException {
        if (o instanceof Person) {
            Person person = (Person) o;
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setObject(1, person.id);
            statement.setObject(2, person.name);
            statement.setObject(3, person.age);
            statement.setObject(4, person.email);
            statement.executeUpdate();
            statement.close();
        }
        if (o instanceof Trip) {
            Trip trip = (Trip) o;
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setObject(1, trip.personID);
            statement.setObject(2, trip.arrivalDate);
            statement.setObject(3, trip.departureDate);
            statement.setObject(4, trip.city);
            statement.executeUpdate();
            statement.close();
        }
    }
}