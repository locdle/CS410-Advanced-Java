package edu.pdx.cs410J.locle.client;

import com.google.gwt.i18n.client.DefaultDateTimeFormatInfo;
import com.google.gwt.i18n.shared.DateTimeFormat;
import edu.pdx.cs410J.AbstractFlight;

import java.text.ParseException;

import java.util.Date;

import static java.lang.System.exit;

public class Flight extends AbstractFlight
{
//  @Override
//  public int getNumber() {
//    return 42;
//  }
//
//  @Override
//  public String getSource() {
//    return "PDX";
//  }
//
//  @Override
//  public Date getDeparture() {
//    return new Date();
//  }
//
//  public String getDepartureString() {
//    return "DEPART " + getDeparture();
//  }
//
//  public String getDestination() {
//    return "MHT";
//  }
//
//  public Date getArrival() {
//    return new Date();
//  }
//
//  public String getArrivalString() {
//    return "ARRIVE " + getArrival();
//  }

    private String number;
    private String source;
    private Date departure;
    private String depart;
    private String destination;
    private Date arrival;
    private String arrive;

//    public Flight(String number, String source, Date departure, String destination, Date arrival) {
//        this.number = number;
//        this.source = source;
//        this.departure = departure;
//        this.destination = destination;
//        this.arrival = arrival;
//    }
//
//    public Flight(String number, String source, String depart, String destination, String arrive) {
//        this.number = number;
//        this.source = source;
//        this.depart = depart;
//        this.destination = destination;
//        this.arrive = arrive;
//    }



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
        Date arrival1 = dateAndTimeFormat(arrive);
        Date depature1 = dateAndTimeFormat(depart);

        long different = (arrival1.getTime() - depature1.getTime())/6000;
        return different;
    }

    public static Date dateAndTimeFormat(String date){
        Date date1 = null;
        String pattern = "mm/dd/yyyy hh:mm a";
        DefaultDateTimeFormatInfo info = new DefaultDateTimeFormatInfo();
        DateTimeFormat dateTimeFormat = new DateTimeFormat(pattern, info) {};

        date1 = dateTimeFormat.parse(date);

        return date1;
    }

    public String print(){
        return this.toString() +  ". And it takes " + this.durationTime() + " in minutes.\n";
    }
}
