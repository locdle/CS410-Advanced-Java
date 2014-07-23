package edu.pdx.cs410J.locle;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AbstractFlight;
import edu.pdx.cs410J.AirportNames;
import edu.pdx.cs410J.ParserException;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by locle on 7/21/14.
 */
public class Project3 {
    private static Boolean isReadme = false;
    private static Boolean isPrint = false;
    private static Boolean isTextFile = false;
    private static Boolean isPretty = false;
    private static Boolean isDash = false;
    private static String fileName = null;
    private static String prettyFileName = null;

    public static void main(String[] args) throws ParserException, IOException {
        if (args.length == 0) {
            System.err.println("Missing command line arguments");
            System.exit(1);
        }
        if (args.length == 1 && args[0].equals("-README")){
            System.out.println("Loc Le - Advance Java - Project 3");
            System.out.println("This is a  README for this project airline \n" +
                    "The project takes options and arguments \n" +
                    "\t options are \n" +
                    "\t\t-pretty File print file to the airline's fight to a text file or standard out \n" +
                    "\t\t-textFile file Where to read/write the airline info \n" +
                    "\t\t-print will print description of the new flight \n" +
                    "\t\t-README will print a README for this project and exits \n" +
                    "\t args are \n" +
                    "\t\t name:          the name of the airline \n" +
                    "\t\t flightNumber:  the flight number\n" +
                    "\t\t src:           three-letter code of departure airport\n" +
                    "\t\t departTime:    departure date and time (24-hour time)\n" +
                    "\t\t dest:          three-letter code of arrival airport\n" +
                    "\t\t arriveTime:    arrival date and time (24-hour time)");

            System.exit(1);
        }

        String [] argsToCreateAirLine = parseCommandLine(args);

        if(argsToCreateAirLine.length != 10){
            System.err.println("Unknown command line argument or missing argument.\nRun -README to do correct input.");
            System.exit(1);
        }

        if(args.length > 13){
            System.err.println("Too many command line argument");
            System.exit(1);
        }

        if(isPrint == false && argsToCreateAirLine.length == 10){
            String name = argsToCreateAirLine[0];
            String flightNumber = argsToCreateAirLine[1];
            String source = argsToCreateAirLine[2];
            String departDay = argsToCreateAirLine[3];
            String departTime = argsToCreateAirLine[4];
            String ampm = argsToCreateAirLine[5];
            String destination = argsToCreateAirLine[6];
            String arriveDay = argsToCreateAirLine[7];
            String arriveTime = argsToCreateAirLine[8];
            String ampm1 = argsToCreateAirLine[9];

            validAirportName(source);
            validAirportName(destination);

            Date dateDepature = dateAndTimeFormat(departDay, departTime, ampm);
            Date dateArrival = dateAndTimeFormat(arriveDay, arriveTime, ampm1);

            AbstractFlight flight = new Flight(flightNumber, source, dateDepature,
                    destination, dateArrival);
            AbstractAirline airline = new Airline(name);
            airline.addFlight(flight);
            flight.toString();
        }

         if(isPrint == true && argsToCreateAirLine.length == 10 && !isPretty && !isTextFile){
            String name = argsToCreateAirLine[0];
            String flightNumber = argsToCreateAirLine[1];
            String source = argsToCreateAirLine[2];
            String departDay = argsToCreateAirLine[3];
            String departTime = argsToCreateAirLine[4];
            String ampm = argsToCreateAirLine[5];
            String destination = argsToCreateAirLine[6];
            String arriveDay = argsToCreateAirLine[7];
            String arriveTime = argsToCreateAirLine[8];
            String ampm1 = argsToCreateAirLine[9];

            Date dateDepature = dateAndTimeFormat(departDay, departTime, ampm);
            Date dateArrival = dateAndTimeFormat(arriveDay, arriveTime, ampm1);

            AbstractFlight flight = new Flight(flightNumber, source, dateDepature,
                    destination, dateArrival);
            AbstractAirline airline = new Airline(name);
            airline.addFlight(flight);
            System.out.println(flight.toString());
        }

          if(isTextFile == true && argsToCreateAirLine.length == 10){
            String name = argsToCreateAirLine[0];
            String flightNumber = argsToCreateAirLine[1];
            String source = argsToCreateAirLine[2];
            String departDay = argsToCreateAirLine[3];
            String departTime = argsToCreateAirLine[4];
            String ampm = argsToCreateAirLine[5];
            String destination = argsToCreateAirLine[6];
            String arriveDay = argsToCreateAirLine[7];
            String arriveTime = argsToCreateAirLine[8];
            String ampm1 = argsToCreateAirLine[9];

            validAirportName(source);
            validAirportName(destination);

            Date dateDepature = dateAndTimeFormat(departDay, departTime, ampm);
            Date dateArrival = dateAndTimeFormat(arriveDay, arriveTime, ampm1);

            AbstractFlight flight = new Flight(flightNumber, source, dateDepature,
                    destination, dateArrival);
            if(isPrint){
                System.out.println(flight.toString());
            }

            AbstractAirline airline = new Airline(name);
            airline.addFlight(flight);
            File file = new File(fileName);
            if(!file.exists()) {
                TextDumper textDumper = new TextDumper(fileName);
                textDumper.dump(airline);
            }
            else {
                TextDumper textDumper = new TextDumper(fileName);
                textDumper.dump(airline);
                TextParser textParser = new TextParser(fileName, airline);
                textParser.parse();
            }
        }

        if(isPretty == true && argsToCreateAirLine.length == 10 && !isDash){
            String name = argsToCreateAirLine[0];
            String flightNumber = argsToCreateAirLine[1];
            String source = argsToCreateAirLine[2];
            String departDay = argsToCreateAirLine[3];
            String departTime = argsToCreateAirLine[4];
            String ampm = argsToCreateAirLine[5];
            String destination = argsToCreateAirLine[6];
            String arriveDay = argsToCreateAirLine[7];
            String arriveTime = argsToCreateAirLine[8];
            String ampm1 = argsToCreateAirLine[9];

            validAirportName(source);
            validAirportName(destination);

            Date dateDepature = dateAndTimeFormat(departDay, departTime, ampm);
            Date dateArrival = dateAndTimeFormat(arriveDay, arriveTime, ampm1);

            AbstractFlight flight = new Flight(flightNumber, source, dateDepature,
                    destination, dateArrival);
            if(isPrint){
                System.out.println(flight.toString());
            }

            AbstractAirline airline = new Airline(name);
            airline.addFlight(flight);
            PrettyPrinter prettyPrinter = new PrettyPrinter(prettyFileName);
            prettyPrinter.dump(airline);
        }

        if(isPretty == true && argsToCreateAirLine.length == 10 && isDash){
            String name = argsToCreateAirLine[0];
            String flightNumber = argsToCreateAirLine[1];
            String source = argsToCreateAirLine[2];
            String departDay = argsToCreateAirLine[3];
            String departTime = argsToCreateAirLine[4];
            String ampm = argsToCreateAirLine[5];
            String destination = argsToCreateAirLine[6];
            String arriveDay = argsToCreateAirLine[7];
            String arriveTime = argsToCreateAirLine[8];
            String ampm1 = argsToCreateAirLine[9];

            validAirportName(source);
            validAirportName(destination);

            Date dateDepature = dateAndTimeFormat(departDay, departTime, ampm);
            Date dateArrival = dateAndTimeFormat(arriveDay, arriveTime, ampm1);

            AbstractFlight flight = new Flight(flightNumber, source, dateDepature,
                    destination, dateArrival);
            if(isPrint){
                System.out.println(flight.toString());
            }

            AbstractAirline airline = new Airline(name);
            airline.addFlight(flight);
            PrettyPrinter prettyPrinter = new PrettyPrinter(prettyFileName);
//            prettyPrinter.dump(airline);
            System.out.println(flight.toString());
            System.out.println(airline.toString());
        }

        if(isPretty == true && isTextFile && argsToCreateAirLine.length == 10 && !isDash){
            String name = argsToCreateAirLine[0];
            String flightNumber = argsToCreateAirLine[1];
            String source = argsToCreateAirLine[2];
            String departDay = argsToCreateAirLine[3];
            String departTime = argsToCreateAirLine[4];
            String ampm = argsToCreateAirLine[5];
            String destination = argsToCreateAirLine[6];
            String arriveDay = argsToCreateAirLine[7];
            String arriveTime = argsToCreateAirLine[8];
            String ampm1 = argsToCreateAirLine[9];

            validAirportName(source);
            validAirportName(destination);

            Date dateDepature = dateAndTimeFormat(departDay, departTime, ampm);
            Date dateArrival = dateAndTimeFormat(arriveDay, arriveTime, ampm1);

            AbstractFlight flight = new Flight(flightNumber, source, dateDepature,
                    destination, dateArrival);
            if(isPrint){
                System.out.println(flight.toString());
            }

            AbstractAirline airline = new Airline(name);
            airline.addFlight(flight);

            File file = new File(fileName);
            if(!file.exists()) {
                TextDumper textDumper = new TextDumper(fileName);
                textDumper.dump(airline);
            }
            else {
                TextDumper textDumper = new TextDumper(fileName);
                textDumper.dump(airline);
                TextParser textParser = new TextParser(fileName, airline);
                textParser.parse();
            }

            PrettyPrinter prettyPrinter = new PrettyPrinter(prettyFileName);
            prettyPrinter.dump(airline);
        }

        if(isPretty == true && argsToCreateAirLine.length == 10 && isDash){
            String name = argsToCreateAirLine[0];
            String flightNumber = argsToCreateAirLine[1];
            String source = argsToCreateAirLine[2];
            String departDay = argsToCreateAirLine[3];
            String departTime = argsToCreateAirLine[4];
            String ampm = argsToCreateAirLine[5];
            String destination = argsToCreateAirLine[6];
            String arriveDay = argsToCreateAirLine[7];
            String arriveTime = argsToCreateAirLine[8];
            String ampm1 = argsToCreateAirLine[9];

            validAirportName(source);
            validAirportName(destination);

            Date dateDepature = dateAndTimeFormat(departDay, departTime, ampm);
            Date dateArrival = dateAndTimeFormat(arriveDay, arriveTime, ampm1);

            AbstractFlight flight = new Flight(flightNumber, source, dateDepature,
                    destination, dateArrival);
            if(isPrint){
                System.out.println(flight.toString());
            }

            AbstractAirline airline = new Airline(name);
            airline.addFlight(flight);

            File file = new File(fileName);
            if(!file.exists()) {
                TextDumper textDumper = new TextDumper(fileName);
                textDumper.dump(airline);
            }
            else {
                TextDumper textDumper = new TextDumper(fileName);
                textDumper.dump(airline);
                TextParser textParser = new TextParser(fileName, airline);
                textParser.parse();
            }

            PrettyPrinter prettyPrinter = new PrettyPrinter(prettyFileName);
//            prettyPrinter.dump(airline);
            System.out.println(flight.toString());
            System.out.println(airline.toString());
        }
    }

    /**
     *
     * @param args: the argument from command line
     * @return the arguments to create an airline without option argument
     */
    public static String[] parseCommandLine(String[] args){
        List<String> stringList = new ArrayList<String>();
        for(String s: args) {
            if (!s.trim().isEmpty()) {
                stringList.add(s);
            }
        }


        for (int i =0; i<stringList.size(); ++i){
            if(stringList.get(i).equals("-README")){
                isReadme = true;
                stringList.remove(i);
            }
            if(stringList.get(i).equals("-print")){
                isPrint = true;
                stringList.remove(i);
            }
//            if(stringList.get(i).equals("-textFile")){
//                isTextFile = true;
//                fileName = stringList.get(i+1);
//                stringList.remove(i);
//                stringList.remove(i);
//            }

        }

        for (int i =0; i<stringList.size(); ++i){
            if(stringList.get(i).equals("-textFile")){
                isTextFile = true;
                fileName = stringList.get(i+1);
                stringList.remove(i);
                stringList.remove(i);
            }
            if(stringList.get(i).equals("-pretty")){
                isPretty = true;
                if(stringList.get(i+1).startsWith("-")){
                    isDash = true;
                }
                else {
                    prettyFileName = stringList.get(i + 1);
                }
                stringList.remove(i);
                stringList.remove(i);
            }
        }

        for (int i =0; i<stringList.size(); ++i){
            if(stringList.get(i).equals("-pretty")){
                isPretty = true;
                if(stringList.get(i+1).startsWith("-")){
                    isDash = true;
                }
                else {
                    prettyFileName = stringList.get(i + 1);
                }
                stringList.remove(i);
                stringList.remove(i);
            }
            if(stringList.get(i).equals("-textFile")){
                isTextFile = true;
                fileName = stringList.get(i+1);
                stringList.remove(i);
                stringList.remove(i);
            }
        }

        String[] argsToCreateAirLine =  stringList.toArray( new String[stringList.size()] );
        return argsToCreateAirLine;
    }

    /**
     * valid data and time in 12 hours
     * @param date users input date
     * @param time users input time
     * @param a am/pm
     * @return the type date of date and time which users input
     */
    public static Date dateAndTimeFormat(String date, String time, String a){
        Date date1 = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy hh:mm a");
        String dateAndTime = date + " " + time + " " + a;
      //  DateFormat dateFormat1 = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
        try{
//            Date aDate = dateFormat.parse(date + " " + time + " " + a);
//            date1 = dateFormat1.parse(date + " " + time + " " + a);
//            date1 = dateFormat.parse(date + " " + time + " " + a);
            date1 = dateFormat.parse(dateAndTime);
        } catch (ParseException e) {

            System.err.println("date and time are malformed " + dateAndTime);
//            e.printStackTrace();
            System.exit(1);
        }
        return date1;
    }

    /**
     *
     * @param name : airport name
     *  Sort the flights by the source
     */
    public static void validAirportName(String name) {
        if (AirportNames.getName(name.toUpperCase()) == null) {
            System.err.println("airport code "+ name + " is not valid");
            System.exit(1);
        }
    }
}
