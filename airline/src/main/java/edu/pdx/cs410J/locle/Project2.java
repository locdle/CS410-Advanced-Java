package edu.pdx.cs410J.locle;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AbstractFlight;
import edu.pdx.cs410J.ParserException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by locle on 7/15/14.
 */
public class Project2 {
    private static Boolean isReadme = false;
    private static Boolean isPrint = false;
    private static Boolean isTextFile = false;
    private static String fileName = null;

    public static void main(String[] args) throws ParserException {
        if (args.length == 0) {
            System.err.println("Missing command line arguments");
            System.exit(1);
        }
        if (args.length == 1 && args[0].equals("-README")){
            System.out.println("Loc Le - Advance Java - Project 1");
            System.out.println("This is a  README for this project airline \n" +
                    "The project takes options and arguments \n" +
                    "\t options are \n" +
                    "\t\t-textFile file Where to read/write the airline info \n" +
                    "\t\t-print will print description of the new flight \n" +
                    "\t\t-README will print a README for this project and exits \n" +
                    "\t args are \n" +
                    "\t\t name:          the name of the airline \n" +
                    "\t\t flightNumber:  the flight number\n" +
                    "\t\t src:           three-letter code of departure airport\n" +
                    "\t\t departTime:    departure date and time (24-hour time)\n" +
                    "\t\t dest:          three-letter code of arrival airport\n" +
                    "\t\t arriveTime:    arrival date and time (24-hour time)\n");

            System.exit(1);
        }

        String [] argsToCreateAirLine = parseCommandLine(args);
        if(isPrint == false && argsToCreateAirLine.length ==8){
            String name = argsToCreateAirLine[0];
            String flightNumber = argsToCreateAirLine[1];
            String source = argsToCreateAirLine[2];
            String departDay = argsToCreateAirLine[3];
            String departTime = argsToCreateAirLine[4];
            String destination = argsToCreateAirLine[5];
            String arriveDay = argsToCreateAirLine[6];
            String arriveTime = argsToCreateAirLine[7];

            AbstractFlight flight = new Flight(flightNumber, source, departDay, departTime,
                    destination, arriveDay, arriveTime);
            Collection<Flight> flightCollection = new ArrayList<>();
            flightCollection.add((Flight) flight);
            AbstractAirline airline = new Airline(name, flightCollection);
            flight.toString();
        }

        if(isPrint == true && argsToCreateAirLine.length ==8){
            String name = argsToCreateAirLine[0];
            String flightNumber = argsToCreateAirLine[1];
            String source = argsToCreateAirLine[2];
            String departDay = argsToCreateAirLine[3];
            String departTime = argsToCreateAirLine[4];
            String destination = argsToCreateAirLine[5];
            String arriveDay = argsToCreateAirLine[6];
            String arriveTime = argsToCreateAirLine[7];

            AbstractFlight flight = new Flight(flightNumber, source, departDay, departTime,
                    destination, arriveDay, arriveTime);
            Collection<Flight> flightCollection = new ArrayList<>();
            flightCollection.add((Flight) flight);
            AbstractAirline airline = new Airline(name, flightCollection);
            System.out.println(flight.toString());
        }

        if(isTextFile == true && argsToCreateAirLine.length == 8){
            String name = argsToCreateAirLine[0];
            String flightNumber = argsToCreateAirLine[1];
            String source = argsToCreateAirLine[2];
            String departDay = argsToCreateAirLine[3];
            String departTime = argsToCreateAirLine[4];
            String destination = argsToCreateAirLine[5];
            String arriveDay = argsToCreateAirLine[6];
            String arriveTime = argsToCreateAirLine[7];

            AbstractFlight flight = new Flight(flightNumber, source, departDay, departTime,
                    destination, arriveDay, arriveTime);
            if(isPrint){
                System.out.println(flight.toString());
            }
            Collection<Flight> flightCollection = new ArrayList<>();
            flightCollection.add((Flight) flight);
//            AbstractAirline airline = new Airline(name, flightCollection);
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
//            Airline airlineParser = (Airline) textParser.parse();
//            System.out.println(airlineParser.print());

            }
        }

        if(argsToCreateAirLine.length != 8){
            System.err.println("Unknown command line argument or missing argument.\nRun -README to do correct input.");
            System.exit(1);
        }

        if(args.length > 11){
            System.err.println("Too many command line argument");
            System.exit(1);
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
            if(stringList.get(i).equals("-textFile")){
                isTextFile = true;
                fileName = stringList.get(i+1);
                stringList.remove(i);
                stringList.remove(i);
            }
        }

        for (int i =0; i<stringList.size(); ++i){
            if(stringList.get(i).equals("-README")){
                isReadme = true;
                stringList.remove(i);
            }
            if(stringList.get(i).equals("-textFile")){
                isTextFile = true;
                fileName = stringList.get(i+1);
                stringList.remove(i);
                stringList.remove(i);
            }
            if(stringList.get(i).equals("-print")){
                isPrint = true;
                stringList.remove(i);
            }
        }

        String[] argsToCreateAirLine =  stringList.toArray( new String[stringList.size()] );
        return argsToCreateAirLine;
    }
}
