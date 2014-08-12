package edu.pdx.cs410J.locle.server;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AbstractFlight;
import edu.pdx.cs410J.locle.client.Airline;
import edu.pdx.cs410J.locle.client.Flight;
import edu.pdx.cs410J.locle.client.PingService;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * The server-side implementation of the Airline service
 */
public class PingServiceImpl extends RemoteServiceServlet implements PingService
{
    private Map<String, Airline> airlineMap = new HashMap<>();
    public AbstractAirline ping()
    {
        Airline airline = new Airline();
        airline.addFlight( new Flight() );
        return airline;
    }



    @Override
    /**
     * return the new airline or flight
     * and use the information in order to
     * add in the flextable
     */
    public AbstractAirline addFlight(String airlineName, String number, String source,
                                     String depart, String destination, String arrive) {
        Airline airline = new Airline(airlineName);
        Flight flight = new Flight(number, source, depart, destination, arrive);

        if (airlineMap.get(airlineName) == null){
            airline.addFlight(flight);
            airlineMap.put(airlineName, airline);
        }
        else{
            airlineMap.get(airlineName).addFlight(flight);
        }
        return airlineMap.get(airlineName);
    }

    @Override
    /**
     * return al the matching flights in the data
     */
    public AbstractAirline searchFlight(String name, String src, String dest) {
        if(airlineMap.get(name) == null){
            return null;
        }
        else {
            Airline airlineFound = new Airline(name);
            Airline airline = airlineMap.get(name);
            Collection flights = airline.getFlights();
            for (Object obj : flights) {
                if (src.equals(((Flight) obj).getSource()) && dest.equals(((Flight) obj).getDestination())) {
                    airlineFound.addFlight((Flight) obj);
                }
            }
            return airlineFound;
        }
    }




}
