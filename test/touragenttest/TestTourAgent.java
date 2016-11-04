package touragenttest;

import agency.TourAgent;
import core.City;
import core.Person;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class TestTourAgent {
    private Connection connection;
    private TourAgent agent;

    @Before
    public void setUp() throws Exception {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "iani");
        agent = new TourAgent(connection);
        createPeopleTable();
        createTripTable();
    }

    @After
    public void tearDown() throws Exception {
        dropTables();
    }

    @Test
    public void getAllDestinations() throws Exception {
        Person iani = new Person(4, "Iani", 23, "iani@gmail.com");
        Person maggie = new Person(1, "Maggie", 20, "maggie@gmail.com");
        Person kika = new Person(2, "Kika", 22, "kika@gmail.com");

        agent.scheduleTrip(iani, "sofia", getDate("8-11-2016"), getDate("12-11-2016"));
        agent.scheduleTrip(maggie, "varna", getDate("9-11-2016"), getDate("30-11-2016"));
        agent.scheduleTrip(kika, "varna", getDate("20-11-2016"), getDate("25-11-2016"));

        List<City> trips = agent.findAllVisitedDestination();

        assertThat(trips.size(), is(2));
        assertThat(trips.get(0).name, is("sofia"));
        assertThat(trips.get(1).name, is("varna"));
    }

    @Test
    public void peopleWithSameTripList() throws Exception {
        Person iani = new Person(4, "Iani", 23, "iani@gmail.com");
        Person maggie = new Person(1, "Maggie", 20, "maggie@gmail.com");
        Person kika = new Person(2, "Kika", 22, "kika@gmail.com");

        agent.scheduleTrip(iani, "sofia", getDate("8-11-2016"), getDate("15-11-2016"));
        agent.scheduleTrip(maggie, "sofia", getDate("9-11-2016"), getDate("30-11-2016"));
        agent.scheduleTrip(kika, "sofia", getDate("20-11-2016"), getDate("25-11-2016"));

        List<Person> persons = agent.findPersonsGotSameTrip(getDate("12-11-2016"), getDate("14-11-2016"), "sofia");

        assertThat(persons.size(), is(2));
        assertThat(persons.contains(kika), is(false));
    }

    private void createTripTable() throws Exception {
        String query = "CREATE TABLE IF NOT EXISTS Trip (\n" +
                "    PersonID            int(10)             NOT NULL,\n" +
                "    ArrivalDate         Date                NOT NULL,\n" +
                "    DepartureDate       Date                NOT NULL,\n" +
                "    City                varchar(20)         NOT NULL,\n" +
                "    FOREIGN KEY(PersonID) REFERENCES People(PersonID)\n" +
                ");";
        connection.createStatement().executeUpdate(query);
    }

    private void createPeopleTable() throws Exception {
        String query = "CREATE TABLE IF NOT EXISTS People (\n" +
                "    PersonID            int(10)             NOT NULL,\n" +
                "    Name                varchar(30)         NOT NULL,\n" +
                "    Age                 int(3)              NOT NULL,\n" +
                "    Email              varchar(30)          NOT NULL,\n" +
                "    PRIMARY KEY(PersonID)\n" +
                ");";
        connection.createStatement().executeUpdate(query);
    }

    private void dropTables() throws Exception {
        String query = "drop table Trip";
        connection.createStatement().executeUpdate(query);
        query = "drop table People";
        connection.createStatement().executeUpdate(query);
    }

    private Date getDate(String date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return new Date(dateFormat.parse(date).getTime());
    }
}