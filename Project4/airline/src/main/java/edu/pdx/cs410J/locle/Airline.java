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

    /**
     *
     * @param name :  the name of this airline
     */
    public Airline(String name) {
        this.name = name;
        this.flights = new LinkedList<>();
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

    /**
     *
     * @return print out the collection of flight for pretty print
     */
    public String print(){
        String printOutAirlineInfo = "Airline name: " + name + " has ";

        for(int i=0; i<flights.size(); ++i){
            printOutAirlineInfo += flights.get(i).toString(); /* + ". And it takes " + flights.get(i).durationTime() + " in minutes";*/
        }

        return printOutAirlineInfo;
    }

    /**
     *
     * @return print out the collection of flights for text dumper
     */
    public String printFlight(){
        String printOutAirlineFlight = "";

        for (int i=0; i<flights.size(); ++i){
            Flight printFLight = (Flight)flights.get(i);
            printOutAirlineFlight += name + " " + printFLight.toString();
        }

        return printOutAirlineFlight;
    }
}
