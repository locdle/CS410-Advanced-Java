package edu.pdx.cs410J.locle;

import edu.pdx.cs410J.AbstractFlight;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


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
        int num = 0;
        try {
            num =  Integer.parseInt(number);
        } catch (NumberFormatException e) {
            System.out.println("Invalid flight number");
            System.exit(1);
        }
        return num;
    }

    @Override
    public String getSource() {
        if(source.length() != 3){
            System.out.println("Invalid departure airport");
            System.exit(1);
        }
        return source;
    }

    @Override
    public String getDepartureString() {
        String date = getDate(timeArrival);
        if(!(isValidDate(date) || isValidDateWithOneDigitDay(date) || isValidDateWithOneDigitMonth(date) ||
                isValidDateWithOneDigitDayAndValidDateWithOneDigitMonth(date)) ){
            System.out.println(date + " Invalid date");
            System.exit(1);
        }
        return timeDeparture;
    }

    @Override
    public String getDestination() {
        if (destination.length() != 3){
            System.out.println("Invalid arrival airport");
            System.exit(1);
        }
        return destination;
    }

    @Override
    public String getArrivalString() {
        return timeArrival;
    }

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

    public String getDate(String date){
        int index = -1;
        for(int i=0 ; i<date.length() -1; ++i){
            if(date.substring(i, i+1).equals(" ")){
                index = i;
                break;
            }
        }
        if(index != -1){
            return date.substring(0, index);
        }
        else {
            return date;
        }
    }
}
