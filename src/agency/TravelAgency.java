package agency;

import core.City;
import core.Person;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface TravelAgency {

    void scheduleTrip(Person person, String city, Date from, Date to) throws SQLException;

    List<City> findAllVisitedDestination();

    List<Person> findPersonsGotSameTrip(Date from, Date to, String city);
}