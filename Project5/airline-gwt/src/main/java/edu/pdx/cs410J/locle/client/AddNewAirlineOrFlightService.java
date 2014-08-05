package edu.pdx.cs410J.locle.client;

import com.google.gwt.user.client.rpc.RemoteService;
import edu.pdx.cs410J.AbstractAirline;

/**
 * Created by locle on 8/5/14.
 */
public interface AddNewAirlineOrFlightService extends RemoteService {
    public AbstractAirline add(Airline airline, Flight flight);
}
