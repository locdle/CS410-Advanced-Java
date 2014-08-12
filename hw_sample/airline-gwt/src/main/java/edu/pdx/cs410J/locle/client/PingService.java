package edu.pdx.cs410J.locle.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AbstractFlight;

/**
 * A GWT remote service that returns a dummy airline
 */
@RemoteServiceRelativePath("ping")
public interface PingService extends RemoteService {

  /**
   * Returns the current date and time on the server
   */
  public AbstractAirline ping();


    /**
     *
     * @param airlineName : name of airline
     * @param number : flight number
     * @param source : departure terminal
     * @param depart : departure date and time
     * @param destination : arrive terminal
     * @param arrive : arrive day and time
     * @return the airline with its information
     */
  public AbstractAirline addFlight(String airlineName, String number, String source,
                                     String depart, String destination, String arrive);

    /**
     *
     * @param name : name of airline
     * @param src: departure terminal
     * @param dest: arrive terminal
     * @return the matching airline
     */
    public AbstractAirline searchFlight(String name, String src, String dest);


}
