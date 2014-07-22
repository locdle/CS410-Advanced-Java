package edu.pdx.cs410J.locle;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AbstractFlight;

import java.util.*;

/**
 * Created by locle on 6/28/14.
 */
public class Airline extends AbstractAirline {
    private String name;
    private List<Flight> flights;
//    private Collection<Flight> flights = new ArrayList<>();

    /**
     *
     * @param name :  the name of this airline
     */
    public Airline(String name) {
        this.name = name;
        this.flights = new LinkedList<>();
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
        flights.add((Flight)flight);
        Collections.sort(flights);
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

        for(int i=0; i<flights.size(); ++i){
            printOutAirlineInfo += flights.get(i).toString();
        }
//        for(Flight flight: flights){
//            printOutAirlineInfo += flight.toString();
//        }
        return printOutAirlineInfo;
    }

    public String printFlight(){
        String printOutAirlineFlight = "";

        for (int i=0; i<flights.size(); ++i){
            Flight printFLight = (Flight)flights.get(i);
            printOutAirlineFlight += name + " " + printFLight.getNumber()
                    + " " + printFLight.getSource()
                    + " " + printFLight.getDateDeparture()
                    + " " + printFLight.getTimeDeparture()
                    + " " + printFLight.getDestination()
                    + " " + printFLight.getDateArrival()
                    + " " + printFLight.getTimeArrival();
        }
//        for(Flight flight: flights){
//            printOutAirlineFlight += name + " " + flight.getNumber()
//                                          + " " + flight.getSource()
//                                          + " " + flight.getDateDeparture()
//                                          + " " + flight.getTimeDeparture()
//                                          + " " + flight.getDestination()
//                                          + " " + flight.getDateArrival()
//                                          + " " + flight.getTimeArrival();
//        }
        return printOutAirlineFlight;
    }
}
