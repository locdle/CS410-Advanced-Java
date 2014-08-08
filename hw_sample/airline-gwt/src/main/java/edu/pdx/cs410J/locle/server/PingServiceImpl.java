package edu.pdx.cs410J.locle.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.locle.client.Airline;
import edu.pdx.cs410J.locle.client.Flight;
import edu.pdx.cs410J.locle.client.PingService;

/**
 * The server-side implementation of the Airline service
 */
public class PingServiceImpl extends RemoteServiceServlet implements PingService
{
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
        airline.addFlight(flight);
        return airline;
    }


}
