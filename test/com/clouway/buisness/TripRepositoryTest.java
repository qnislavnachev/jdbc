package com.clouway.buisness;

import com.clouway.adapter.ConnectionProvider;
import com.clouway.adapter.PersistentPersonRepository;
import com.clouway.adapter.PersistentTripRepository;
import com.clouway.core.City;
import com.clouway.core.Person;
import com.clouway.core.Trip;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Vasil Mitov <v.mitov.clouway@gmail.com>
 */
public class TripRepositoryTest {
  private ConnectionProvider connectionProvider = new ConnectionProvider("TASK2");
  private PersistentPersonRepository personRepository = new PersistentPersonRepository(connectionProvider);
  private PersistentTripRepository tripRepository = new PersistentTripRepository(connectionProvider);
  private TableManagerForTest tableManager = new TableManagerForTest(connectionProvider);
  private CalendarForTest calendar = new CalendarForTest();
  private Person person1 = new Person("Vasko", "1111111111", 25, "pojoemail@object.com");
  private Person person2 = new Person("Vaskis", "1111111112", 23, "pojoemail@object.com");
  private Person person3 = new Person("Marto", "1111111113", 28, "pojoemail@object.com");
  private Trip tripToPleven = new Trip("1111111111", calendar.getDate(10, 12, 2016), calendar.getDate(12, 12, 2016), "Pleven");
  private Trip tripToPleven2 = new Trip("1111111112", calendar.getDate(10, 12, 2016), calendar.getDate(12, 12, 2016), "Pleven");
  private Trip tripToTarnovo = new Trip("1111111113", calendar.getDate(10, 12, 2016), calendar.getDate(13, 12, 2016), "Tarnovo");


  @Before
  public void setup() {
    tableManager.deleteTable("TRIP");
    tableManager.deleteTable("PEOPLE");
    personRepository.register(person1);
    personRepository.register(person2);
    personRepository.register(person3);
    tripRepository.register(tripToPleven);
    tripRepository.register(tripToPleven2);
    tripRepository.register(tripToTarnovo);
  }

  @Test
  public void registerTrip() throws Exception {
    Trip expected = new Trip("1111111111", calendar.getDate(10, 12, 2016), calendar.getDate(12, 12, 2016), "Pleven");
    Optional<Trip> actual = tripRepository.find("1111111111");

    assertThat(actual.get(), is(expected));
  }

  @Test
  public void mostVisited() throws Exception {
    List<City> expected = new LinkedList<>();
    expected.add(new City("Pleven", 2));
    expected.add(new City("Tarnovo", 1));
    List<City> actual = tripRepository.mostVisited();
    assertThat(actual, is(expected));
  }
}
