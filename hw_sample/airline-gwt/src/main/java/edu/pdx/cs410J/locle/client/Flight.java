package edu.pdx.cs410J.locle.client;

import com.google.gwt.i18n.shared.DateTimeFormat;
import edu.pdx.cs410J.AbstractFlight;

import java.util.Date;

public class Flight extends AbstractFlight
{

    private String number;
    private String source;
    private Date departure;
    private String depart;
    private String destination;
    private Date arrival;
    private String arrive;

    public Flight(String number, String source, String depart, String destination, String arrive) {
        this.number = number;
        this.source = source;
        this.depart = depart;
        this.destination = destination;
        this.arrive = arrive;
    }

    public Flight(){

    }

  @Override
  public int getNumber() {
//    if(number != null){
//        return Integer.parseInt(number);
//    }
//      else {
//        return 42;
//    }
      int num = 0;
      try {
          num =  Integer.parseInt(number);
      } catch (NumberFormatException e) {
//          System.err.println("Flight number isn't an integer");
      }
      return num;
  }

  @Override
  public String getSource() {
//      if (source != null){
//          return source;
//      }
//      else {
//          return "PDX";
//      }
      return source;
  }

  @Override
  public Date getDeparture() {
      DateTimeFormat dateTimeFormat = new DateTimeFormat("mm/dd/yyyy hh:mm a") {};
      Date date = dateTimeFormat.parse(depart);
      return date;
//      return new Date();
  }

  public String getDepartureString() {
    return /*"DEPART " */ "" +  getDeparture();
  }

  public String getDestination() {
//    if(destination!= null){
//        return destination;
//    }
//      else {
//        return "MHT";
//    }
      return destination;
  }

  public Date getArrival() {
      DateTimeFormat dateTimeFormat = new DateTimeFormat("mm/dd/yyyy hh:mm a") {};
      Date date = dateTimeFormat.parse(arrive);
      return date;
//    return new Date();
  }

  public String getArrivalString() {
    return /*"ARRIVE "*/ "" + getArrival();
  }

}
