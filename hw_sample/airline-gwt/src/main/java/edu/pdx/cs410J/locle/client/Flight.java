package edu.pdx.cs410J.locle.client;

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
    if(number != null){
        return Integer.parseInt(number);
    }
      else {
        return 42;
    }
  }

  @Override
  public String getSource() {
      if (source != null){
          return source;
      }
      else {
          return "PDX";
      }
  }

  @Override
  public Date getDeparture() {
    return new Date();
  }

  public String getDepartureString() {
    return "DEPART " + getDeparture();
  }

  public String getDestination() {
    if(destination!= null){
        return destination;
    }
      else {
        return "MHT";
    }
  }

  public Date getArrival() {
    return new Date();
  }

  public String getArrivalString() {
    return "ARRIVE " + getArrival();
  }

}
