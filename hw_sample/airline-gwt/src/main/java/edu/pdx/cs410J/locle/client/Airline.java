package edu.pdx.cs410J.locle.client;

import edu.pdx.cs410J.AbstractFlight;
import edu.pdx.cs410J.AbstractAirline;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * The class airline will store the airline name
 * and the collection of flights
 */
public class Airline extends AbstractAirline
{
    private String name;
    private List<Flight> flights;

    /**
     * constructor with argument
     * @param name
     */
    public Airline(String name) {
        this.name = name;
        this.flights = new LinkedList<>();
    }

    /**
     * default constructor
     */
    public Airline() {
    }


    /**
     * get name of the airline
     */
  public String getName() {

      return name;
  }

    /**
     * add the info of new flight to the collection of flight
     */
  public void addFlight(AbstractFlight flight) {
    this.flights.add((Flight)flight);
  }

    /**
     * return the collection of the flight
     */
  public Collection getFlights() {
    return this.flights;
  }
}
