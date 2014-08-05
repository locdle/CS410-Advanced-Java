package edu.pdx.cs410J.locle.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import edu.pdx.cs410J.AbstractAirline;

public interface AddNewAirlineOrFlightServiceAsync {

    void add(Airline airline, Flight flight, AsyncCallback<AbstractAirline> async);
}
