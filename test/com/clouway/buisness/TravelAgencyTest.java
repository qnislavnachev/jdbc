package com.clouway.buisness;

import com.clouway.adapter.ConnectionProvider;
import com.clouway.adapter.PersistentPersonRepository;
import com.clouway.adapter.PersistentTripRepository;
import com.clouway.core.City;
import com.clouway.core.Person;
import com.clouway.core.Trip;
import org.junit.Before;
import org.junit.Test;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;


/**
 * @author Vasil Mitov <v.mitov.clouway@gmail.com>
 */
public class TravelAgencyTest {
  private ConnectionProvider connectionProvider = new ConnectionProvider("TASK2");
  private PersistentPersonRepository personRepository = new PersistentPersonRepository(connectionProvider);
  private PersistentTripRepository tripRepository = new PersistentTripRepository(connectionProvider);
  private TravelAgency travelAgency = new TravelAgency(personRepository, tripRepository);
  private CalendarForTest calendar = new CalendarForTest();

  Person person1 = new Person("Vasko", "1111111111", 25, "pojoemail@object.com");
  Person person2 = new Person("Denis", "1111111112", 23, "pojoemail@object.com");
  Person person3 = new Person("Qnislav", "1111111113", 24, "pojoemail@object.com");
  Person person4 = new Person("Vaskoo", "1111111114", 24, "pojoemail@object.com");
  Trip tripToPleven = new Trip("1111111111", calendar.getDate(10, 12, 2016), calendar.getDate(12, 12, 2016), "Pleven");
  Trip tripToPleven2 = new Trip("1111111112", calendar.getDate(10, 12, 2016), calendar.getDate(13, 12, 2016), "Pleven");
  Trip tripToTarnovo = new Trip("1111111113", calendar.getDate(10, 12, 2016), calendar.getDate(13, 12, 2016), "Tarnovo");

  @Before
  public void cleanup() {
    deleteTable("TRIP");
    deleteTable("PEOPLE");
  }

  @Test
  public void registerClient() throws Exception {
    travelAgency.registerClient(person1);

    assertThat(travelAgency.findClient(person1.egn), is(person1));
  }

  @Test
  public void registerClientTrip() throws Exception {
    travelAgency.registerClient(person1);
    travelAgency.registerTrip(tripToPleven);

    Trip expected=tripToPleven;
    Trip actual=travelAgency.findTrip(tripToPleven.egn);

    assertThat(actual,is((expected)));
  }

  @Test
  public void findAllClientsStartingWithTheSameLetter() throws Exception {
    travelAgency.registerClient(person1);
    travelAgency.registerClient(person2);
    travelAgency.registerClient(person4);

    List<Person> expected=new LinkedList<>();
    expected.add(person1);
    expected.add(person4);
    List<Person> actual=travelAgency.findClientsStartingWithLetter("V");

    assertThat(actual,is(expected));
  }

  @Test
  public void mostVisitedCities() throws Exception {
    travelAgency.registerClient(person1);
    travelAgency.registerClient(person2);
    travelAgency.registerClient(person3);
    travelAgency.registerTrip(tripToPleven);
    travelAgency.registerTrip(tripToPleven2);
    travelAgency.registerTrip(tripToTarnovo);

    List<City> actual=travelAgency.mostVisitedCities();
    List<City> expected=new LinkedList<>();
    expected.add(new City("Pleven",2));
    expected.add(new City("Tarnovo",1));

    assertThat(actual,is(expected));
  }

  @Test
  public void visitedAtTheSameTime() throws Exception {


  }

  @Test
  public void displayTables() throws Exception {
    travelAgency.registerClient(person1);
    travelAgency.registerClient(person2);
    travelAgency.registerClient(person3);
    travelAgency.registerTrip(tripToPleven);
    travelAgency.registerTrip(tripToPleven2);
    travelAgency.registerTrip(tripToTarnovo);

    List actualPersonList=travelAgency.registeredClients();
    List actualTripList=travelAgency.registeredTrips();
    List<Person> expectedPersonList=new LinkedList<>();
    List<Trip> expectedTripList=new LinkedList<>();
    expectedPersonList.add(person1);
    expectedPersonList.add(person2);
    expectedPersonList.add(person3);
    expectedTripList.add(tripToPleven);
    expectedTripList.add(tripToPleven2);
    expectedTripList.add(tripToTarnovo);


    assertThat(actualPersonList,is(expectedPersonList));
    assertThat(actualTripList,is(expectedTripList));
  }

  private void deleteTable(String table) {
    Connection connection = connectionProvider.get();
    String query = "DELETE FROM " + table;
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        connection.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
}
