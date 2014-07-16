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
     * @param name :  the name of this airline
     */
    public Airline(String name) {
        this.name = name;
    }

    /**
     *
     * @param name:  the name of this airline
     * @param flights: all of this airline's flights
     */
    public Airline(String name, Collection<Flight> flights) {
        this.name = name;
//        this.flights = flights;
    }

    @Override
    /**
     * get name of the airline
     */
    public String getName() {
        return name;
    }

    @Override
    /**
     * add the info of new flight to the collection of flight
     */
    public void addFlight(AbstractFlight flight) {
        flights.add((Flight) flight);
    }

    @Override
    /**
     * return the collection of the flight
     */
    public Collection getFlights() {
        return flights;
    }

    public String print(){
        String printOutAirlineInfo = "Airline name: " + name + " has ";

        for(Flight flight: flights){
            printOutAirlineInfo += flight.toString();
        }
        return printOutAirlineInfo;
    }

    public String printFlight(){
        String printOutAirlineFlight = "";
        int lastIndex = flights.size() -1;
        for(Flight flight: flights){
            printOutAirlineFlight += name + " " + flight.getNumber()
                                          + " " + flight.getSource()
                                          + " " + flight.getDateDeparture()
                                          + " " + flight.getTimeDeparture()
                                          + " " + flight.getDestination()
                                          + " " + flight.getDateArrival()
                                          + " " + flight.getTimeArrival();
        }
        return printOutAirlineFlight;
    }
}
