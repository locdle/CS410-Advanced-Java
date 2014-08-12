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
    public AbstractAirline pingName(String name) {
        Airline airline = new Airline(name);
        airline.addFlight(new Flight());
        return airline;
    }

    @Override
    public AbstractAirline addFlight(String airlineName, String number, String source,
                                     String depart, String destination, String arrive) {
        Airline airline = new Airline(airlineName);
        Flight flight = new Flight(number, source, depart, destination, arrive);
//        airline.addFlight(flight);
//        return airline;

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
    public AbstractAirline searchFlight(String name, String src, String dest) {
        Airline airlineFound = new Airline(name);
        Airline airline = airlineMap.get(name);
        Collection flights = airline.getFlights();
//        boolean found = false;
        for(Object obj:flights){
            if(src.equals(((Flight)obj).getSource()) && dest.equals(((Flight)obj).getDestination())){
//                found = true;
                  airlineFound.addFlight((Flight)obj);
            }
        }
        return airlineFound;
    }

    @Override
    public AbstractFlight searchAFlight(String name, String src, String dest) {
        Airline airline = airlineMap.get(name);
        Collection flights = airline.getFlights();

        for(Object obj:flights){
            if(src.equals(((Flight)obj).getSource()) && dest.equals(((Flight)obj).getDestination())){
               return (Flight)obj;
            }
        }
        return null;
    }


}
