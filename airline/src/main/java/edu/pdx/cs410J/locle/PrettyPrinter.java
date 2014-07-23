package edu.pdx.cs410J.locle;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AirlineDumper;

import java.io.*;

/**
 * Created by locle on 7/21/14.
 */
public class PrettyPrinter implements AirlineDumper {
    private String prettyFilename;

    /**
     * constructor of pretty print class
     * @param prettyFilename : file name to write on textfile
     */
    public PrettyPrinter(String prettyFilename) {
        this.prettyFilename = prettyFilename;
    }

    @Override
    public void dump(AbstractAirline airline) throws IOException {
        try {
            File file = new File(prettyFilename);
            if (!file.exists()) {
                createDirectory();
                FileWriter fileWriter = new FileWriter(new File(prettyFilename));
                Airline newAirline = (Airline) airline;
                fileWriter.write(newAirline.print());
                fileWriter.close();
            } else {
                PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(prettyFilename, true)));
                Airline newAirline = (Airline) airline;
                printWriter.write("\n");
                printWriter.write(newAirline.print());
                printWriter.close();
            }
        }
        catch (IOException io){
            System.err.print("Error writing to a file " + prettyFilename);
        }
    }

    public void createDirectory(){
        String [] stringSplitSlash = prettyFilename.split("/");
        String stringPath = "";
        for(int i = 0; i<stringSplitSlash.length-1;++i){
            stringPath+=stringSplitSlash[i]+'/';
        }

        boolean success = (new File(stringPath)).mkdirs();
    }
}
