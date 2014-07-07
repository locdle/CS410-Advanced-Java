package edu.pdx.cs410J.locle;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AbstractFlight;
import sun.swing.SwingUtilities2;

import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

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
      if (args.length == 1 && args[0].equals("-README")){

              System.out.println("Prints a README for this project");
              System.exit(1);

      }
      if (args.length == 2){
            if((args[0].equals("-README") && args[1].equals("-print")) ||
                    (args[1].equals("-README") && args[0].equals("-print"))){
                System.err.println("The program don't support 2 options at the same time. Please run each option separately");
                System.exit(1);
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
          System.out.println(flight.toString());
      }
  }
}
