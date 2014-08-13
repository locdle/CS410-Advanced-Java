package edu.pdx.cs410J.locle.client;

import com.google.gwt.i18n.shared.DateTimeFormat;
import edu.pdx.cs410J.AbstractFlight;

import java.util.Date;

/**
 * this is a class flight will store the information of its flight
 */
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
  /**
   * get number of the flight and make sure this is valid number
   */
  public int getNumber() {
      int num = 0;
      try {
          num =  Integer.parseInt(number);
      } catch (NumberFormatException e) {
      }
      return num;
  }

  @Override
  /**
   *  get the letter code of departure airline
   */
  public String getSource() {
      return source;
  }

  @Override
  /**
   * return type date of departure
   */
  public Date getDeparture() {
      DateTimeFormat dateTimeFormat = new DateTimeFormat("mm/dd/yyyy hh:mm a") {};
      Date date = dateTimeFormat.parse(depart);
      return date;
  }

    /**
     *  get the time and day of departure
     */
  public String getDepartureString() {
    return  "" +  getDeparture();
  }

    /**
     *  get  letter code of destination airport
     */
  public String getDestination() {
      return destination;
  }

    /**
     * return type date of arrival
     */
  public Date getArrival() {
      DateTimeFormat dateTimeFormat = new DateTimeFormat("mm/dd/yyyy hh:mm a") {};
      Date date = dateTimeFormat.parse(arrive);
      return date;
  }

    /**
     *  get arrival's day and time
     */
  public String getArrivalString() {
    return  "" + getArrival();
  }


//    public int compareTo(Flight o) {
//        if(this.getSource().compareTo(o.getSource()) != 0){
//            return this.getSource().compareTo(o.getSource());
//        }
//        else{
//            return this.getDepartureString().compareTo(o.getDepartureString());
//        }
//    }

    public long durationTime(){
        long different = (this.getArrival().getTime() - this.getDeparture().getTime())/6000;
        return different;
    }

}
