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

  public AbstractAirline pingName(String name);

  public AbstractAirline addFlight(String airlineName, String number, String source,
                                     String depart, String destination, String arrive);
    public AbstractAirline searchFlight(String name, String src, String dest);

    public AbstractFlight searchAFlight(String name, String src, String dest);
}
