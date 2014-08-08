package edu.pdx.cs410J.locle.client;

import edu.pdx.cs410J.AbstractFlight;
import edu.pdx.cs410J.AbstractAirline;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class Airline extends AbstractAirline
{
    private String name;
    private List<Flight> flights;

    public Airline(String name) {
        this.name = name;
        this.flights = new LinkedList<>();
    }

    public Airline() {
    }

//    private Collection<AbstractFlight> flights = new ArrayList<AbstractFlight>();

  public String getName() {

      if (name != null) {
          return name;
      } else {
          return "Air CS410J";
      }
  }

  public void addFlight(AbstractFlight flight) {
    this.flights.add((Flight)flight);
  }

  public Collection getFlights() {
    return this.flights;
  }
}
