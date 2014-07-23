package edu.pdx.cs410J.locle;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AbstractFlight;
import edu.pdx.cs410J.AirlineParser;
import edu.pdx.cs410J.ParserException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by locle on 7/15/14.
 */
public class TextParser implements AirlineParser {
    private String filename;
    private AbstractAirline abstractAirline;

    public TextParser(String filename, AbstractAirline airline) {
        this.filename = filename;
        this.abstractAirline = airline;
    }

    @Override
    /**
     * parse the information of the airline from the external file,
     * pass it to the airline class
     */
    public AbstractAirline parse() throws ParserException {
        String filenameWithoutExtension = getFileName(filename);
        if(filenameWithoutExtension.equals(abstractAirline.getName())) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
                StringBuilder stringBuilder = new StringBuilder();
                String line = bufferedReader.readLine();
                line = bufferedReader.readLine();
                while (line != null){
                    stringBuilder.append(line);
                    String[] str = stringBuilder.toString().split("\\s+");
                    try {
                        Date departure = dateAndTimeFormat(str[3], str[4], str[5]);
                        Date arrival = dateAndTimeFormat(str[7], str[8], str[9]);
                        AbstractFlight flight = new Flight(str[1], str[2], departure, str[6], arrival);
                        flight.toString();
                        abstractAirline.addFlight(flight);
                        stringBuilder.append(System.lineSeparator());
                        line = bufferedReader.readLine();
                    }
                    catch (NullPointerException e){
                        System.err.println("missing content to create an airline. Check back your documents");
                        System.exit(0);
                    }
                }
            } catch (IOException e) {
                System.err.println("File I/O error");
                System.exit(1);
            }
            return abstractAirline;
        }
        else {
            System.err.print("the file name doesn't match with abstractAirline name");
            System.exit(0);
            return null;
        }

    }

    /**
     *
     * @param fileName:
     *      if the file name has extension
     * @return the file name without extension
     *      else
     * @return the original file name
     */
    public String getFileName(String fileName){
        int index = fileName.indexOf(".");
        int index1 = fileName.lastIndexOf("/");
        if (index == -1){
            return fileName;
        }
        else{
            if(index1 == -1) {
                return fileName.substring(0, index);
            }
            else{
                return fileName.substring(index1 + 1, index);

            }
        }
    }

    /**
     * valid date and time from users input
     * @param date user input date
     * @param time user input time
     * @param a user inputam/pm
     * @return type date of valid date
     */
    public static Date dateAndTimeFormat(String date, String time, String a){
        Date date1 = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy hh:mm a");
        String dateAndTime = date + " " + time + " " + a;
        try{
            date1 = dateFormat.parse(dateAndTime);
        } catch (ParseException e) {

            System.err.println("date and time are malformed " + dateAndTime);
            System.exit(1);
        }
        return date1;
    }
}
