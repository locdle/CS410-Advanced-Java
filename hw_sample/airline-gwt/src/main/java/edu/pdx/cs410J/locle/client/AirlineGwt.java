package edu.pdx.cs410J.locle.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import edu.pdx.cs410J.AbstractFlight;
import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AirportNames;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * A basic GWT class that makes sure that we can send an airline back from the server
 */
public class AirlineGwt implements EntryPoint {
  public void onModuleLoad() {
    Button button = new Button("Ping Server");
    button.addClickHandler(new ClickHandler() {
        public void onClick( ClickEvent clickEvent )
        {
            PingServiceAsync async = GWT.create( PingService.class );
            async.ping( new AsyncCallback<AbstractAirline>() {

                public void onFailure( Throwable ex )
                {
                    Window.alert(ex.toString());
                }

                public void onSuccess( AbstractAirline airline )
                {
                    StringBuilder sb = new StringBuilder( airline.toString() );
                    Collection<AbstractFlight> flights = airline.getFlights();
                    for ( AbstractFlight flight : flights ) {
                        sb.append(flight);
                        sb.append("\n");
                    }
                    Window.alert( sb.toString() );
                }
            });
        }
    });

      HorizontalPanel panel = new HorizontalPanel();
      final TextBox name = new TextBox();
      Button airline = new Button("airline");
      airline.addClickHandler(new ClickHandler() {

          @Override
          public void onClick(ClickEvent event) {
              PingServiceAsync async = GWT.create(PingService.class);
              String airlineName = name.getText();
              async.pingName(airlineName, new AsyncCallback<AbstractAirline>() {
                  @Override
                  public void onFailure(Throwable caught) {
                      Window.alert(caught.toString());
                  }

                  @Override
                  public void onSuccess(AbstractAirline result) {
                      StringBuilder sb = new StringBuilder( result.toString() );
                      Collection<AbstractFlight> flights = result.getFlights();
                      for ( AbstractFlight flight : flights ) {
                          sb.append(flight);
                          sb.append("\n");
                      }
                      Window.alert( sb.toString() );
                  }
              });
          }
      });
      panel.add(name);
      panel.add(airline);


      final FlexTable flexTable = new FlexTable();
      flexTable.setText(0, 0, "Airline Name ");
      flexTable.setText(0, 1, "Flight Number");
      flexTable.setText(0, 2, "Departing Airport");
      flexTable.setText(0, 3, "Departing Date & Time");
      flexTable.setText(0, 4, "Arriving Airport");
      flexTable.setText(0, 5, "Arriving Date & Time");

      VerticalPanel verticalPanel = new VerticalPanel();
      verticalPanel.add(new Label("AIRLINE NAME"));
      final TextBox airlineName = new TextBox();
      verticalPanel.add(airlineName);

      verticalPanel.add(new Label("FLIGHT NUMBER"));
      final TextBox flightNumber = new TextBox();
      verticalPanel.add(flightNumber);

      verticalPanel.add(new Label("DEPARTING AIRPORT"));
      final TextBox source = new TextBox();
      verticalPanel.add(source);

      verticalPanel.add(new Label("DEPARTING DATE & TIME"));
      final TextBox depart = new TextBox();
      verticalPanel.add(depart);

      verticalPanel.add(new Label("ARRIVING AIRPORT"));
      final TextBox dest = new TextBox();
      verticalPanel.add(dest);

      verticalPanel.add(new Label("ARRIVING DATE & TIME"));
      final TextBox arrive = new TextBox();
      verticalPanel.add(arrive);

      Button addFlight = new Button("Add Flight");
      addFlight.addClickHandler(new ClickHandler() {
          @Override
          public void onClick(ClickEvent event) {
            PingServiceAsync async = GWT.create(PingService.class);
              final String airline = airlineName.getText();
              final String number = flightNumber.getText();
              try {
                  int num = Integer.parseInt(number);
              } catch (NumberFormatException e){
                  Window.alert("Flight number must be integer");
                  return;
              }
              final String src = source.getText();
              if (AirportNames.getName(src.toUpperCase()) == null) {
                  Window.alert("airport code "+ src + " is not valid");
                  return;
              }
              final String departTime = depart.getText();
              DateTimeFormat dateFormat = DateTimeFormat.getFormat("MM/dd/yyyy hh:mm a");
              try{
                  Date date = dateFormat.parse(departTime);
              } catch (Exception e) {
                  Window.alert("date and time are malformed " + departTime);
                  return;
              }
              final String destination = dest.getText();
              if (AirportNames.getName(destination.toUpperCase()) == null) {
                  Window.alert("airport code "+ destination + " is not valid");
                  return;
              }
              final String arrival = arrive.getText();
              try{
                  Date date = dateFormat.parse(arrival);
              } catch (Exception e) {
                  Window.alert("date and time are malformed " + arrival);
                  return;
              }
//              validDateAndTime(arrival);
              async.addFlight(airline, number, src, departTime, destination, arrival, new AsyncCallback<AbstractAirline>() {
                  @Override
                  public void onFailure(Throwable caught) {
                      Window.alert(caught.toString());
                  }

                  @Override
                  public void onSuccess(AbstractAirline result) {
                      int row = flexTable.getRowCount() + 1;

                      flexTable.setText(row, 0, airline);
                      flexTable.setText(row, 1, number);
                      flexTable.setText(row, 2, src);
                      flexTable.setText(row, 3, departTime);
                      flexTable.setText(row, 4, destination);
                      flexTable.setText(row, 5, arrival);

                  }
              });
          }
      });
      verticalPanel.add(addFlight);

      RootPanel rootPanel = RootPanel.get();
//      rootPanel.add(button);
//      rootPanel.add(panel, 100, 100);
      rootPanel.add(flexTable, 200, 100);
      rootPanel.add(verticalPanel, 0, 100);
  }
}
