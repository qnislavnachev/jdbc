package com.clouway.buisness;

import com.clouway.adapter.PersistentPersonRepository;
import com.clouway.adapter.PersistentTripRepository;
import com.clouway.core.City;
import com.clouway.core.Person;
import com.clouway.core.Provider;
import com.clouway.core.Trip;

import java.sql.Connection;
import java.util.List;
import java.util.zip.CheckedInputStream;

/**
 * @author Vasil Mitov <v.mitov.clouway@gmail.com>
 */
public class TravelAgency {
  private Provider<Connection> provider;
  private PersistentPersonRepository personRepository;
  private PersistentTripRepository tripRepository;

  public TravelAgency(PersistentPersonRepository personRepository, PersistentTripRepository tripRepository) {
    this.personRepository = personRepository;
    this.tripRepository = tripRepository;
  }

  public void registerClient(Person person) {
    personRepository.register(person);
  }

  public void registerTrip(Trip trip) {
    tripRepository.register(trip);
  }

  public void cancleTrip(Trip trip) {
    tripRepository.delete(trip);
  }

  public List<City> mostVisitedCities() {
    return tripRepository.mostVisited();
  }

  public List<Person> registeredClients() {
    return personRepository.display();
  }

  public List<Trip> registeredTrips() {
    return tripRepository.display();
  }

  public List<Person> peopleStartingWith(String letter) {
    return personRepository.findAll(letter);
  }

  public void cleanup(){
    personRepository.deleteTableContents();
    tripRepository.deleteTableContents();
  }

}
