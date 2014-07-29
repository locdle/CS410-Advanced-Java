package edu.pdx.cs410J.locle;

import edu.pdx.cs410J.AbstractFlight;
import edu.pdx.cs410J.AirportNames;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by locle on 6/28/14.
 */
public class Flight extends AbstractFlight implements Comparable<Flight> {
    private String number;
    private String source;
    private Date departure;
    private String depart;
    private String destination;
    private Date arrival;
    private String arrive;

    public Flight(String number, String source, Date departure, String destination, Date arrival) {
        this.number = number;
        this.source = source;
        this.departure = departure;
        this.destination = destination;
        this.arrival = arrival;
    }

    public Flight(String number, String source, String depart, String destination, String arrive) {
        this.number = number;
        this.source = source;
        this.depart = depart;
        this.destination = destination;
        this.arrive = arrive;
    }



    @Override
    /**
     * get number of the flight and make sure this is valid number
     */
    public int getNumber() {
        int num = 0;
        try {
            num =  Integer.parseInt(number);
        } catch (NumberFormatException e) {
            System.err.println("Flight number isn't an integer");
            System.exit(1);
        }
        return num;
    }

    @Override
    /**
     *  get the three letter code of departure airline
     */
    public String getSource() {
        if(source.length() != 3){
            System.err.println("Invalid departure airport");
            System.exit(1);
        }
        return source;
    }

    @Override
    /**
     *  get the time and day of departure
     */
    public String getDepartureString() {
//        return DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(departure);
        return depart;
    }

    @Override
    /**
     *  get 3 letter code of destination airport
     */
    public String getDestination() {
        if (destination.length() != 3){
            System.err.println("Invalid arrival airport");
            System.exit(1);
        }
        return destination;
    }

    @Override
    /**
     *  get arrival's day and time
     */
    public String getArrivalString() {
//        return DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(arrival);
        return arrive;
    }

    @Override
    /**
     * return type date of departure
     */
    public Date getDeparture() {
        return super.getDeparture();
    }

    @Override
    /**
     * return type date of arrival
     */
    public Date getArrival() {
        return super.getArrival();
    }

    @Override
    /**
     *
     */
    public int compareTo(Flight o) {
        if(this.getSource().compareTo(o.getSource()) != 0){
            return this.getSource().compareTo(o.getSource());
        }
        else{
            return this.departure.toString().compareTo(o.departure.toString());
//            return this.getDepartureString().compareTo(o.getDepartureString());
        }
    }

    public long durationTime(){
//        long different = (this.arrival.getTime() - this.departure.getTime())/6000;
//        return different;
        long different = (this.getArrival().getTime() - this.getDeparture().getTime())/6000;
        return different;
    }

}
