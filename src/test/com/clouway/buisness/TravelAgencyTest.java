package com.clouway.buisness;

import com.clouway.adapter.ConnectionProvider;
import com.clouway.adapter.PersistentPersonRepository;
import com.clouway.adapter.PersistentTripRepository;
import com.clouway.core.Person;
import com.clouway.core.Trip;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Vasil Mitov <v.mitov.clouway@gmail.com>
 */
public class TravelAgencyTest {
  private ConnectionProvider connectionProvider=new ConnectionProvider("TASK2");
  private PersistentPersonRepository personRepository=new PersistentPersonRepository(connectionProvider);
  private PersistentTripRepository tripRepository=new PersistentTripRepository(connectionProvider);
  private TravelAgency travelAgency=new TravelAgency(personRepository,tripRepository);

  Person person1=new Person("Vasko",1111111111,25,"pojoemail@object.com");
  Person person2=new Person("Denis",1111111112,23,"pojoemail@object.com");
  Person person3=new Person("Qnislav",1111111113,24,"pojoemail@object.com");
  Trip trip1=new Trip(1111111111,)

  @Before
  void cleanup(){
    travelAgency.cleanup();
  }

  @Test
  public void registryTest() throws Exception {


  }
}