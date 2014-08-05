package edu.pdx.cs410J.locle.client;

import edu.pdx.cs410J.AbstractFlight;
import edu.pdx.cs410J.AbstractAirline;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class Airline extends AbstractAirline {

//    private Collection<AbstractFlight> flights = new ArrayList<AbstractFlight>();
//
//    public String getName() {
//        return "Air CS410J";
//    }
//
//    public void addFlight(AbstractFlight flight) {
//        this.flights.add(flight);
//    }
//
//    public Collection getFlights() {
//        return this.flights;
//    }

    private String name;
    private List<Flight> flights = new LinkedList<>();

    /**
     *
     * @param name :  the name of this airline
     */
//    public Airline(String name) {
//        this.name = name;
//        this.flights = new LinkedList<>();
//    }


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
//        Collections.sort(flights);
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
        String printOutAirlineInfo = "Airline name: " + name + " has : \n";

        for(int i=0; i<flights.size(); ++i){
            printOutAirlineInfo += "\t" + flights.get(i).toString()  + ". And it takes " + flights.get(i).durationTime() + " in minutes.\n";
        }
        System.out.println();
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
