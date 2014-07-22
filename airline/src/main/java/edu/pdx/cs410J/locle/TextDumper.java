package edu.pdx.cs410J.locle;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AirlineDumper;

import java.io.*;

/**
 * Created by locle on 7/15/14.
 */
public class TextDumper implements edu.pdx.cs410J.AirlineDumper {
    private String fileName;

    public TextDumper(String fileName) {
        this.fileName = fileName;
    }

    @Override
    /**
     * This function will dump the content of airline to
     * the external file.
     */
    public void dump(AbstractAirline airline)  {
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                createDirectory();
                FileWriter fileWriter = new FileWriter(new File(fileName));
                Airline newAirline = (Airline) airline;
                fileWriter.write(newAirline.getName());
                fileWriter.close();
            } else {
                PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)));
                Airline newAirline = (Airline) airline;
                printWriter.write("\n");
                printWriter.write(newAirline.printFlight());
                printWriter.close();
            }
        }
        catch (IOException io){
            System.err.print("Error writing to a file " + fileName);
        }
    }

    public void createDirectory(){
        String [] split = fileName.split("/");
        String filePath = "";
        for(int i = 0; i<split.length-1;++i){
            filePath+=split[i]+'/';
        }

        boolean success = (new File(filePath)).mkdirs();
    }

}