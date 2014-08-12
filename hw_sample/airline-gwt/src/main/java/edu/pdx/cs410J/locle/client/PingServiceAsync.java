package edu.pdx.cs410J.locle.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AbstractFlight;

/**
 * The client-side interface to the ping service
 */
public interface PingServiceAsync {

  /**
   * Return the current date/time on the server
   */
  void ping(AsyncCallback<AbstractAirline> async);


    /**
     *
     * @param airlineName :name of airline
     * @param number : flight number
     * @param source : departure terminal
     * @param depart : departure day and time
     * @param destination : arrive terminal
     * @param arrive : arrive day and time
     * @param async
     */
    void addFlight(String airlineName, String number, String source,
                   String depart, String destination, String arrive, AsyncCallback<AbstractAirline> async);

    /**
     *
     * @param name : name of airline
     * @param src: departure terminal
     * @param dest : arrive terminal
     * @param async
     */
    void searchFlight(String name, String src, String dest, AsyncCallback<AbstractAirline> async);


}
