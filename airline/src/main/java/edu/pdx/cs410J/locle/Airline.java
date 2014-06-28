package edu.pdx.cs410J.locle;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AbstractFlight;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by locle on 6/28/14.
 */
public class Airline extends AbstractAirline {
    private String name;
    private Collection<Flight> flights = new ArrayList<>();

    /**
     *
     * @param name:  the name of this airline
     * @param flights: all of this airline's flights
     */
    public Airline(String name, Collection<Flight> flights) {
        this.name = name;
        this.flights = flights;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void addFlight(AbstractFlight flight) {
        flights.add((Flight) flight);
    }

    @Override
    public Collection getFlights() {
        return flights;
    }
}
