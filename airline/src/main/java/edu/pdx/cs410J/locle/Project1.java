package edu.pdx.cs410J.locle;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AbstractFlight;


import java.io.*;
import java.util.ArrayList;
import java.util.Collection;


/**
 * The main class for the CS410J airline Project
 */
public class Project1 {

  public static void main(String[] args) throws FileNotFoundException {
//    Class c = AbstractAirline.class;  // Refer to one of Dave's classes so that we can be sure it is on the classpath
//    System.err.println("Missing command line arguments");
//    for (String arg : args) {
//      System.out.println(arg);
//    }
//    System.exit(1);
//  }

      if (args.length == 0) {
          System.err.println("Missing command line arguments");
          System.exit(1);
      }
      else if (args.length == 1 && args[0].equals("-README")){

              System.out.println("This is a  README for this project airline \n" +
                                 "THe project takes options and arguments \n" +
                                        "\t options are \n" +
                                            "\t\t-print will print description of the new flight \n" +
                                            "\t\t-README will print a README for this project and exits \n" +
                                        "\targs are \n" +
                                            "\t name:          the name of the airline \n" +
                                            "\t flightNumber:  the flight number\n" +
                                            "\t src:           three-letter code of departure airport\n" +
                                            "\t departTime:    departure date and time (24-hour time)\n" +
                                            "\t dest:          three-letter code of arrival airport\n" +
                                            "\t arriveTime:    arrival date and time (24-hour time)\n");

              System.exit(1);

      }
      else if (args.length == 2){
            if((args[0].equals("-README") && args[1].equals("-print")) ||
                    (args[1].equals("-README") && args[0].equals("-print"))){
                System.err.println("The program don't support 2 options at the same time. Please run each option separately");
                System.exit(1);
            }
      }
      else if (args.length == 7 && args[0].equals("-print")){
          String name = args[1];
          String flightNumber = args[2];
          String source = args[3];
          String departTime = args[4];
          String destination = args[5];
          String arriveTime = args[6];

          AbstractFlight flight = new Flight(flightNumber, source, departTime, destination, arriveTime );
          Collection<Flight> flightCollection = new ArrayList<>();
          flightCollection.add((Flight) flight);
          AbstractAirline airline = new Airline(name, flightCollection);

          System.out.println(airline.toString());
          System.out.println(flight.toString());
      }
      else{
          System.err.println("Invalid syntax");
          System.exit(1);

      }
  }
}
