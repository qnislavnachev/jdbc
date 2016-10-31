package com.clouway.task2;

import com.clouway.task2.adapter.ConnectionProvider;
import com.clouway.task2.adapter.PersistentPersonRepository;
import com.clouway.task2.adapter.PersistentTripRepository;
import com.clouway.task2.core.Person;
import com.clouway.task2.core.Trip;
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
public class PersonRepositoryTest {
  private ConnectionProvider connectionProvider = new ConnectionProvider("TASK2");
  private PersistentPersonRepository personRepository = new PersistentPersonRepository(connectionProvider);
  private TableManagerForTest tableManager = new TableManagerForTest(connectionProvider);
  private CalendarForTest calendar = new CalendarForTest();

  @Before
  public void setup() {
    tableManager.deleteTable("TRIP");
    tableManager.deleteTable("PEOPLE");
  }


  @Test
  public void happyPath() throws Exception {
    personRepository.register(new Person("Vasko", "1111111111", 25, "pojoemail@object.com"));
    Person expected = new Person("Vasko", "1111111111", 25, "pojoemail@object.com");
    Optional<Person> actual = personRepository.find("1111111111");
    assertThat(actual.get(), is(expected));
  }

  @Test
  public void notFindingAPerson() throws Exception {
    Optional<Person> expected = Optional.empty();
    Optional<Person> actual = personRepository.find("2111111111");
    assertThat(actual, is(expected));
  }


  @Test
  public void findAllStartingWithSequens() throws Exception {
    personRepository.register(new Person("Vasko", "1111111111", 25, "pojoemail@object.com"));
    personRepository.register(new Person("Vaskis", "1111111112", 23, "pojoemail@object.com"));
    personRepository.register(new Person("Marto", "1111111113", 28, "pojoemail@object.com"));
    List<Person> expected = new LinkedList<>();
    expected.add(new Person("Vasko", "1111111111", 25, "pojoemail@object.com"));
    expected.add(new Person("Vaskis", "1111111112", 23, "pojoemail@object.com"));
    List<Person> actual = personRepository.findAllStartingWith("V");
    assertThat(actual, is(expected));
  }

  @Test
  public void visitingAtTheSameTime() throws Exception {
    personRepository.register(new Person("Vasko", "1111111111", 25, "pojoemail@object.com"));
    personRepository.register(new Person("Vaskis", "1111111112", 23, "pojoemail@object.com"));
    personRepository.register(new Person("Marto", "1111111113", 28, "pojoemail@object.com"));

    PersistentTripRepository tripRepository = new PersistentTripRepository(connectionProvider);
    Trip tripToPleven = new Trip("1111111111", calendar.getDate(10, 12, 2016), calendar.getDate(12, 12, 2016), "Pleven");
    Trip tripToPleven2 = new Trip("1111111112", calendar.getDate(10, 12, 2016), calendar.getDate(12, 12, 2016), "Pleven");
    tripRepository.register(tripToPleven);
    tripRepository.register(tripToPleven2);

    List<Person> expected = new LinkedList<>();
    expected.add(new Person("Vasko", "1111111111", 25, "pojoemail@object.com"));
    expected.add(new Person("Vaskis", "1111111112", 23, "pojoemail@object.com"));

    List<Person> actual = personRepository.findAllStayingAtTheSameTime("Pleven", calendar.getDate(11, 12, 2016));

    assertThat(actual, is(expected));
  }
}
