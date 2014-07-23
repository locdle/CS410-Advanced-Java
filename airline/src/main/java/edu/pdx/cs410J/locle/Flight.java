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
    private String dateDeparture;
    private String timeDeparture;
    private Date departure;
    private String destination;
    private Date arrival;
    private String dateArrival;
    private String timeArrival;
    private Pattern pattern;
    private Matcher matcher;
    private static final String TIME24HOURS_PATTERN =
            "([01]?[0-9]|2[0-3]):[0-5][0-9]";

    /**
     *  getter the getDate departure
     * @return the string for date departure
     */
    public String getDateDeparture() {
        return dateDeparture;
    }

    /**
     * getter for get time departure
     * @return the string for time departure
     */
    public String getTimeDeparture() {
        return timeDeparture;
    }

    /**
     *  getter the getDate arrival
     * @return the string for date arrival
     */
    public String getDateArrival() {
        return dateArrival;
    }

    /**
     * getter for get time arrival
     * @return the string for time arrival
     */
    public String getTimeArrival() {
        return timeArrival;
    }

    /**
     *
     * @return the pattern
     */
    public Pattern getPattern() {
        return pattern;
    }

    /**
     *
     * @return the matcher
     */
    public Matcher getMatcher() {
        return matcher;
    }

    /**
     *
     * @return 24hours time pattern
     */
    public static String getTime24hoursPattern() {
        return TIME24HOURS_PATTERN;
    }

    /**
     *
     * @param number: number that uniquely identifies this flight
     * @param source: the three-letter code of the airport at which this flight originates
     * @param timeDeparture: representation of this flight's departure time
     * @param destination: three-letter code of the airport at which this flight terminates
     * @param timeArrival: representation of this flight's arrival time
     */
    public Flight(String number, String source, String dateDeparture, String timeDeparture,
                  String destination, String dateArrival, String timeArrival) {
        this.number = number;
        this.source = source;
        this.dateDeparture = dateDeparture;
        this.timeDeparture = timeDeparture;
        this.destination = destination;
        this.dateArrival = dateArrival;
        this.timeArrival = timeArrival;
    }

    public Flight(String number, String source, Date departure, String destination, Date arrival) {
        this.number = number;
        this.source = source;
        this.departure = departure;
        this.destination = destination;
        this.arrival = arrival;
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
//        if(!(isValidDate(dateDeparture) || isValidDateWithOneDigitDay(dateDeparture) || isValidDateWithOneDigitMonth(dateDeparture) ||
//                isValidDateWithOneDigitDayAndValidDateWithOneDigitMonth(dateDeparture)) ){
//            System.err.println(dateDeparture + ": Invalid date");
//            System.exit(1);
//        }
//        if(!(isValidateTime(timeDeparture))){
//            System.err.println(timeDeparture + ": is invalid time");
//            System.exit(1);
//        }
//        return dateDeparture + " " +timeDeparture;
        return DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(departure);
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
//        if(!(isValidDate(dateArrival) || isValidDateWithOneDigitDay(dateArrival) || isValidDateWithOneDigitMonth(dateArrival) ||
//                isValidDateWithOneDigitDayAndValidDateWithOneDigitMonth(dateArrival)) ){
//            System.err.println(dateArrival + " is invalid date");
//            System.exit(1);
//        }
//        if(!(isValidateTime(timeArrival))){
//            System.err.println(timeArrival + " is invalid time");
//            System.exit(1);
//        }
//        return dateArrival + " " + timeArrival;
        return DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(arrival);
    }

    /**
     * check date is in form mm/dd/yyyy
     * @param date: at departure or arrival
     * @return true if it's valid and vice versa
     */
    public boolean isValidDate (String date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy");
        Date testDate = null;
        try{
            testDate = dateFormat.parse(date);
        }
        catch (ParseException e){
            return false;
        }
        if(!dateFormat.format(testDate).equals(date)){
            return false;
        }
        return true;
    }

    /**
     * check date is in form m/dd/yyyy
     * @param date: at departure or arrival
     * @return true if it's valid and vice versa
     */
    public boolean isValidDateWithOneDigitMonth (String date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("m/dd/yyyy");
        Date testDate = null;
        try{
            testDate = dateFormat.parse(date);

        }
        catch (ParseException e){
            return false;
        }
        if(!dateFormat.format(testDate).equals(date)){
            return false;
        }
        return true;
    }

    /**
     * check date is in form mm/d/yyyy
     * @param date: at departure or arrival
     * @return true if it's valid and vice versa
     */
    public boolean isValidDateWithOneDigitDay (String date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("mm/d/yyyy");
        Date testDate = null;
        try{
            testDate = dateFormat.parse(date);
        }
        catch (ParseException e){
            return false;
        }
        if(!dateFormat.format(testDate).equals(date)){
            return false;
        }
        if(Integer.parseInt(date.substring(0,2)) > 12){
            return false;
        }
        return true;
    }

    /**
     * check date is in form m/d/yyyy
     * @param date: at departure or arrival
     * @return true if it's valid and vice versa
     */
    public boolean isValidDateWithOneDigitDayAndValidDateWithOneDigitMonth (String date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("m/d/yyyy");
        Date testDate = null;
        try{
            testDate = dateFormat.parse(date);
        }
        catch (ParseException e){
            return false;
        }
        if(!dateFormat.format(testDate).equals(date)){
            return false;
        }
        try{
            if(Integer.parseInt(date.substring(0,2)) > 12){
                return false;
            }
        }
        catch (NumberFormatException e) {
        }
        return true;
    }

    /**
     *
     * @param dateAndTime date and time at departure or arrival airport
     * @return date at departure or arrival airport
     */
    public String getDate(String dateAndTime){
        int index = -1;
        for(int i=0 ; i<dateAndTime.length() -1; ++i){
            if(dateAndTime.substring(i, i+1).equals(" ")){
                index = i;
                break;
            }
        }
        if(index != -1){
            return dateAndTime.substring(0, index);
        }
        else {
            return dateAndTime;
        }
    }

    /**
     *
     * @param dayAndTime date and time at departure or arrival airport
     * @return time  at departure or arrival airport
     */
    public String getTime(String dayAndTime){
        int index = -1;
        for(int i=0 ; i<dayAndTime.length() -1; ++i){
            if(dayAndTime.substring(i, i+1).equals(" ")){
                index = i;
                break;
            }
        }
        if(index != -1){
            return dayAndTime.substring(index+1, dayAndTime.length());
        }
        else {
            return dayAndTime;
        }
    }

    /**
     *
     * @param time  at departure or arrival airport
     * @return true it it's valid 24 hours time and vice versa
     */
    public boolean isValidateTime(String time){
        pattern = Pattern.compile(TIME24HOURS_PATTERN);
        matcher = pattern.matcher(time);
        return matcher.matches();
    }


    @Override
    public Date getDeparture() {
        return super.getDeparture();
    }

    @Override
    public Date getArrival() {
        return super.getArrival();
    }

    @Override
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
        long different = (this.arrival.getTime() - this.departure.getTime())/6000;
        return different;
    }

}
