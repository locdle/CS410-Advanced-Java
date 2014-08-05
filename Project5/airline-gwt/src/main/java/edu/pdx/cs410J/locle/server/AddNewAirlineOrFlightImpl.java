package edu.pdx.cs410J.locle.server;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.locle.client.AddNewAirlineOrFlightService;
import edu.pdx.cs410J.locle.client.Airline;
import edu.pdx.cs410J.locle.client.Flight;

/**
 * Created by locle on 8/5/14.
 */
public class AddNewAirlineOrFlightImpl extends RemoteServiceServlet implements AddNewAirlineOrFlightService {
    @Override
    public AbstractAirline add(Airline airlineName, Flight flight) {
        Airline airline = new Airline();
        airline.addFlight(new Flight());
        return airline;
    }
}
