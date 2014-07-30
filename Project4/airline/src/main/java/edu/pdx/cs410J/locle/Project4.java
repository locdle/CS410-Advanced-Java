package edu.pdx.cs410J.locle;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AirportNames;
import edu.pdx.cs410J.web.HttpRequestHelper;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * The main class that parses the command line and communicates with the
 * Airline server using REST.
 */
public class Project4 {

    public static final String MISSING_ARGS = "Missing command line arguments";
    private static boolean isReadme = false;
    private static boolean isPrint = false;
    private static String hostName = null;
    private static String portString = null;
    private static String print = null;
    private static boolean isSearch = false;

    public static void main(String... args) {
        if(args.length ==0){
            System.err.println(MISSING_ARGS);
            System.exit(1);
        }
        else  if (args.length == 1 && args[0].equals("-README")){
            System.out.println("Loc Le - Advance Java - Project 4");
            System.out.println("This is a  README for this project airline \n" +
                    "The project takes options and arguments \n" +
                    "\t options are \n" +
                    "\t\t-host hostname    Host computer on which the server runs \n" +
                    "\t\t-port port        Port on which the server is listening \n" +
                    "\t\t-search           Search for flights \n" +
                    "\t\t-print            Prints descriptions of the new flight \n" +
                    "\t\t-README           Prints a README for this project and exits \n" +
                    "\t args are \n" +
                    "\t\t name:          the name of the airline \n" +
                    "\t\t flightNumber:  the flight number\n" +
                    "\t\t src:           three-letter code of departure airport\n" +
                    "\t\t departTime:    departure date and time (24-hour time)\n" +
                    "\t\t dest:          three-letter code of arrival airport\n" +
                    "\t\t arriveTime:    arrival date and time (24-hour time)");

            System.exit(1);
        }
        else if(args.length < 9){
            String airline = null;
            String src = null;
            String destination = null;

            String[] argsToSearch = parseCommandLine(args);

            if (hostName == null) {
                usage(MISSING_ARGS);

            } else if (portString == null) {
                usage("Missing port");
            }

            for(String str: argsToSearch){
                if(airline == null){
                    airline = str;
                }
                else if(src == null){
                    src = str;
                }
                else if(destination == null){
                    destination = str;
                }
                else{
                    System.err.println("Too many argument for searching flights");
                    System.exit(1);
                }
            }

            int port;
            try {
                port = Integer.parseInt(portString);

            } catch (NumberFormatException ex) {
                usage("Port \"" + portString + "\" must be an integer");
                return;
            }

            AirlineRestClient client = new AirlineRestClient(hostName, port);

            HttpRequestHelper.Response response;
            try{
                response = client.searchFlight(airline, src, destination);
            }catch (IOException ex) {
                error("While contacting server: " + ex);
                return;
            }

            System.out.println(response.getContent());

            System.exit(0);


        }
        else {
//            String hostName = null;
//            String portString = null;



            String airline = null;
            String flightNumber = null;
            String src = null;
            String departDay = null;
            String departTime = null;
            String ampm = null;
            String destination = null;
            String arriveDay = null;
            String arriveTime = null;
            String ampm1 = null;

            String[] argsToCreateAirLine = parseCommandLine(args);


            if (hostName == null) {
                usage(MISSING_ARGS);

            } else if (portString == null) {
                usage("Missing port");
            }

//            if (argsToCreateAirLine.length < 10) {
//                System.err.println("Missing arguments to create an airline");
//                System.exit(1);
//            }

            for (String str : argsToCreateAirLine) {
                if (airline == null) {
                    airline = str;
                } else if (flightNumber == null) {
                    flightNumber = str;
                } else if (src == null) {
                    src = str;
                } else if (departDay == null) {
                    departDay = str;
                } else if (departTime == null) {
                    departTime = str;
                } else if (ampm == null) {
                    ampm = str;
                } else if (destination == null) {
                    destination = str;
                } else if (arriveDay == null) {
                    arriveDay = str;
                } else if (arriveTime == null) {
                    arriveTime = str;
                } else if (ampm1 == null) {
                    ampm1 = str;
                } else {
                    System.err.println("Too many argument to create airline");
                    System.exit(1);
                }
            }

            validAirportName(src);
            validAirportName(destination);

            String departure = dateAndTimeFormatInString(departDay, departTime, ampm);
            String arrival = dateAndTimeFormatInString(arriveDay, arriveTime, ampm1);

            Flight flight = new Flight(flightNumber, src, departure, destination, arrival);

            if(isPrint && isSearch){
                System.err.println("Can't handle both print and search in the same time");
                System.exit(1);
            }

            if(isPrint){
                System.out.println(flight.print());
            }


            AbstractAirline abstractAirline = new Airline(airline);
            abstractAirline.addFlight(flight);
            Airline airline1 = (Airline)abstractAirline;
//            System.out.println(airline1.print());
            int port;
            try {
                port = Integer.parseInt(portString);

            } catch (NumberFormatException ex) {
                usage("Port \"" + portString + "\" must be an integer");
                return;
            }

            AirlineRestClient client = new AirlineRestClient(hostName, port);

            HttpRequestHelper.Response response;
            try {
                response = client.addFlight(airline, flightNumber, src, departure, destination, arrival);

                checkResponseCode(HttpURLConnection.HTTP_OK, response);

            } catch (IOException ex) {
                error("While contacting server: " + ex);
                return;
            }

            System.out.println(response.getContent());

            System.exit(0);
        }
    }

    /**
     * Makes sure that the give response has the expected HTTP status code
     * @param code The expected status code
     * @param response The response from the server
     */
    private static void checkResponseCode( int code, HttpRequestHelper.Response response )
    {
        if (response.getCode() != code) {
            error(String.format("Expected HTTP code %d, got code %d.\n\n%s", code,
                                response.getCode(), response.getContent()));
        }
    }

    /**
     * David's function
     * @param message
     */
    private static void error( String message )
    {
        PrintStream err = System.err;
        err.println("** " + message);

        System.exit(1);
    }

    /**
     * Prints usage information for this program and exits
     * @param message An error message to print
     */
    private static void usage( String message )
    {
        PrintStream err = System.err;
        err.println("** " + message);
        err.println();
        err.println("usage: java Project4 host port [key] [value]");
        err.println("  host    Host of web server");
        err.println("  port    Port of web server");
        err.println("  key     Key to query");
        err.println("  value   Value to add to server");
        err.println();
        err.println("This simple program posts key/value pairs to the server");
        err.println("If no value is specified, then all values are printed");
        err.println("If no key is specified, all key/value pairs are printed");
        err.println();

        System.exit(1);
    }


    /**
     * valid data and time in 12 hours
     * @param date users input date
     * @param time users input time
     * @param a am/pm
     * @return the type string of date and time which users input
     */
    public static String dateAndTimeFormatInString(String date, String time, String a){
        Date date1 = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy hh:mm a");
        String dateAndTime = date + " " + time + " " + a;
        try{
            date1 = dateFormat.parse(dateAndTime);
        } catch (ParseException e) {

            System.err.println("date and time are malformed " + dateAndTime);
            System.exit(1);
        }
        return dateFormat.format(date1);
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
            if(stringList.get(i).equals("-host")){
                hostName = stringList.get(i+1);
                stringList.remove(i);
                stringList.remove(i);
            }
            if(stringList.get(i).equals("-port")){
                portString = stringList.get(i+1);
                stringList.remove(i);
                stringList.remove(i);
            }
            if(stringList.get(i).equals("-print")){
                isPrint = true;
                print = "print";
                stringList.remove(i);
            }
            if(stringList.get(i).equals("-search")){
                isSearch = true;
                stringList.remove(i);
            }

        }
        String[] argsToCreateAirLine =  stringList.toArray( new String[stringList.size()] );
        return argsToCreateAirLine;
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