package edu.pdx.cs410J.locle;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AbstractFlight;
import edu.pdx.cs410J.AirlineParser;
import edu.pdx.cs410J.ParserException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
                        AbstractFlight flight = new Flight(str[1], str[2], str[3], str[4], str[5], str[6], str[7]);
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
        if (index == -1){
            return fileName;
        }
        else{
            return fileName.substring(0, index);
        }
    }
}
