package edu.pdx.cs410J.locle;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AbstractFlight;

import java.util.ArrayList;
import java.util.Collection;

/**
 * The main class for the CS410J airline Project
 */
public class Project1 {

  public static void main(String[] args) {
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
      if (args.length == 1){
          if(args[0].equals("-print")){
              System.out.println(" Prints a description of the new flight");
          }
      }
      if (args.length == 7 && args[0].equals("-print")){
          String name = args[1];
          String flightNumber = args[2];
          String source = args[3];
          String departTime = args[4];
          String destination = args[5];
          String arriveTime = args[6];
          AbstractFlight flight = new Flight(flightNumber, source, departTime, destination, arriveTime );
          Collection <Flight> flights = new ArrayList<>();
          flights.add((Flight)flight);
          AbstractAirline airline = new Airline(name, flights);
          airline.toString();
          System.out.println(airline.toString());
          System.out.println(flight.toString());
      }
  }
}
