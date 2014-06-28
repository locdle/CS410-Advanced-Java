package edu.pdx.cs410J.locle;

import edu.pdx.cs410J.AbstractFlight;

/**
 * Created by locle on 6/28/14.
 */
public class Flight extends AbstractFlight {
    private String number;
    private String source;
    private String timeDeparture;
    private String destination;
    private String timeArrival;

    /**
     *
     * @param number: number that uniquely identifies this flight
     * @param source: the three-letter code of the airport at which this flight originates
     * @param timeDeparture: representation of this flight's departure time
     * @param destination: three-letter code of the airport at which this flight terminates
     * @param timeArrival: representation of this flight's arrival time
     */
    public Flight(String number, String source, String timeDeparture, String destination, String timeArrival) {
        this.number = number;
        this.source = source;
        this.timeDeparture = timeDeparture;
        this.destination = destination;
        this.timeArrival = timeArrival;
    }

    @Override
    public int getNumber() {
        return Integer.parseInt(number);
    }

    @Override
    public String getSource() {
        return source;
    }

    @Override
    public String getDepartureString() {
        return timeDeparture;
    }

    @Override
    public String getDestination() {
        return destination;
    }

    @Override
    public String getArrivalString() {
        return timeArrival;
    }
}
