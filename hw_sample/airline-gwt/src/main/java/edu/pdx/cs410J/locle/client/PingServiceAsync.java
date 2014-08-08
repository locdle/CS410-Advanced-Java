package edu.pdx.cs410J.locle.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import edu.pdx.cs410J.AbstractAirline;

/**
 * The client-side interface to the ping service
 */
public interface PingServiceAsync {

  /**
   * Return the current date/time on the server
   */
  void ping(AsyncCallback<AbstractAirline> async);


    void pingName(String name, AsyncCallback<AbstractAirline> async);

    void addFlight(String airlineName, String number, String source,
                   String depart, String destination, String arrive, AsyncCallback<AbstractAirline> async);
}
