package edu.pdx.cs410J.locle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class AirlineServlet extends HttpServlet
{
    private final Map<String, String> data = new HashMap<String, String>();
    private final Map<String, Airline> airlineMap = new HashMap<String, Airline>();
    private Airline airline = null;

    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {
        response.setContentType( "text/plain" );

        String airline = getParameter( "airline", request );
//        if (key != null) {
//            writeValue(key, response);
//
//        } else {
//            writeAllMappings(response);
//        }
        if (airline !=null){
            writeValue(airline, response);
        }
        else{
            writeAllMappings(response);
        }
    }

    @Override
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {
        response.setContentType( "text/plain" );

//        String key = getParameter( "key", request );
//        if (key == null) {
//            missingRequiredParameter( response, key );
//            return;
//        }

//        String value = getParameter( "value", request );
//        if ( value == null) {
//            missingRequiredParameter( response, value );
//            return;
//        }

//        this.data.put(key, value);


        String flightNumber = getParameter("flightNumber", request);
        if (flightNumber == null){
            missingRequiredParameter(response, flightNumber);
            return;
        }


        String src = getParameter("src", request);
//        if (src == null){
//            missingRequiredParameter(response, src);
//            return;
//        }

        String departTime = getParameter("departTime", request);
//        if (departDay == null){
//            missingRequiredParameter(response, departDay);
//            return;
//        }

        String dest = getParameter("dest", request);
//        if (dest == null){
//            missingRequiredParameter(response, dest);
//            return;
//        }

        String arriveTime = getParameter("arriveTime", request);
//        if (arriveDay == null){
//            missingRequiredParameter(response, arriveDay);
//            return;
//        }

        Flight flight = new Flight(flightNumber, src, departTime, dest, arriveTime);

        String airline = getParameter("airline", request);
        if (airline == null){
            missingRequiredParameter(response, airline);
            return;
        }
        if (this.airline == null){
            Airline airline1 = new Airline(airline);
            this.airline = airline1;
            this.airline.addFlight(flight);
            this.airlineMap.put(airline, this.airline);
        }
        else{
            this.airline.addFlight(flight);
            this.airlineMap.put(airline, this.airline);
        }


        PrintWriter pw = response.getWriter();
        pw.println(Messages.mappedKeyValue(flightNumber, airline));
        pw.flush();

        response.setStatus( HttpServletResponse.SC_OK);
    }

    private void missingRequiredParameter( HttpServletResponse response, String key )
        throws IOException
    {
        PrintWriter pw = response.getWriter();
        pw.println( Messages.missingRequiredParameter(key));
        pw.flush();
        
        response.setStatus( HttpServletResponse.SC_PRECONDITION_FAILED );
    }

    private void writeValue( String key, HttpServletResponse response ) throws IOException
    {
        String value = this.data.get(key);

        PrintWriter pw = response.getWriter();
        pw.println(Messages.getMappingCount( value != null ? 1 : 0 ));
        pw.println(Messages.formatKeyValuePair( key, value ));

        pw.flush();

        response.setStatus( HttpServletResponse.SC_OK );
    }

    private void writeAllMappings( HttpServletResponse response ) throws IOException
    {
        PrintWriter pw = response.getWriter();
        pw.println(Messages.getMappingAirlineCount( airlineMap.size() ));
//        pw.println(Messages.getMappingCount( data.size() ));

//        for (Map.Entry<String, String> entry : this.data.entrySet()) {
//            pw.println(Messages.formatKeyValuePair(entry.getKey(), entry.getValue()));
//        }
        for (Map.Entry<String, Airline> entry : this.airlineMap.entrySet()) {
//            pw.println(Messages.formatKeyValuePair(entry.getKey(), entry.getValue()));
            pw.println(entry.getValue().print());
            pw.println();
//            Airline airline2 = entry.getValue();
//            System.out.println(airline2.print());

        }

        pw.flush();

        response.setStatus( HttpServletResponse.SC_OK );
    }

    private String getParameter(String name, HttpServletRequest request) {
      String value = request.getParameter(name);
      if (value == null || "".equals(value)) {
        return null;

      } else {
        return value;
      }
    }

}
