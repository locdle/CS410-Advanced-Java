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
              async.addFlight(airline, number, src, departTime, destination, arrival, new AsyncCallback<AbstractAirline>() {
                  @Override
                  public void onFailure(Throwable caught) {
                      Window.alert(caught.toString());
                  }

                  @Override
                  public void onSuccess(AbstractAirline result) {
                      flexTable.removeAllRows();
                      flexTable.setText(0, 0, "Airline Name ");
                      flexTable.setText(0, 1, "Flight Number");
                      flexTable.setText(0, 2, "Departing Airport");
                      flexTable.setText(0, 3, "Departing Date & Time");
                      flexTable.setText(0, 4, "Arriving Airport");
                      flexTable.setText(0, 5, "Arriving Date & Time");

                      Collection<AbstractFlight> flights = result.getFlights();

                      for(AbstractFlight flight:flights){
                          int row = flexTable.getRowCount() + 1;
                          flexTable.setText(row, 0, result.getName());
                          flexTable.setText(row, 1, Integer.toString(flight.getNumber()));
                          flexTable.setText(row, 2, flight.getSource());
                          flexTable.setText(row, 3, flight.getDepartureString());
                          flexTable.setText(row, 4, flight.getDestination());
                          flexTable.setText(row, 5, flight.getArrivalString());
                      }
                  }
              });
          }
      });
      verticalPanel.add(addFlight);



      VerticalPanel verticalPanel1 = new VerticalPanel();
      verticalPanel1.add(new Label("AIRLINE NAME"));
      final TextBox seachAirlineName = new TextBox();
      verticalPanel1.add(seachAirlineName);
      verticalPanel1.add(new Label("DEPARTING AIRPORT"));
      final TextBox searchSrc = new TextBox();
      verticalPanel1.add(searchSrc);
      verticalPanel1.add(new Label("ARRIVING AIRPORT"));
      final TextBox searchDest = new TextBox();
      verticalPanel1.add(searchDest);
      Button search = new Button("SEARCH");
      search.addClickHandler(new ClickHandler() {
          @Override
          public void onClick(ClickEvent event) {
              PingServiceAsync async = GWT.create(PingService.class);
              String name = seachAirlineName.getText();
              String src1 = searchSrc.getText();
              if (AirportNames.getName(src1.toUpperCase()) == null) {
                  Window.alert("airport code "+ src1 + " is not valid");
                  return;
              }
              String dest1 = searchDest.getText();
              if (AirportNames.getName(dest1.toUpperCase()) == null) {
                  Window.alert("airport code "+ dest1 + " is not valid");
                  return;
              }
              async.searchFlight(name, src1, dest1, new AsyncCallback<AbstractAirline>() {
                  @Override
                  public void onFailure(Throwable caught) {
                      Window.alert(caught.toString());
                  }

                  @Override
                  public void onSuccess(AbstractAirline result) {
                      if(result == null){
                          Window.alert("There are no flight matching in the server");
                      }
                    else {
                          if (result.getFlights().size() == 0) {
                              Window.alert("There are no flight matching in the server");
                          } else {
                              StringBuilder sb = new StringBuilder(result.toString());
                              Collection<AbstractFlight> flights = result.getFlights();
                              for (AbstractFlight flight : flights) {
                                  sb.append(flight);
                                  sb.append("\n");
                              }
                              Window.alert(sb.toString());
                          }
                      }

                  }
              });


          }
      });
      verticalPanel1.add(search);

      Button readMe = new Button("Help");
      readMe.addClickHandler(new ClickHandler() {
          @Override
          public void onClick(ClickEvent clickEvent) {
              Window.setTitle("Read Me");
              Window.alert("Loc Le - Advance Java - Project 5 \n" +
                      "This is a  README for this project airline \n" +
                      "The project will create airline and flight if user enter \n" +
                      "\t\t name:          the name of the airline \n" +
                      "\t\t flightNumber:  the flight number\n" +
                      "\t\t source:           three-letter code of departure airport\n" +
                      "\t\t departTime:    departure date and time (24-hour time)\n" +
                      "\t\t dest:          three-letter code of arrival airport\n" +
                      "\t\t arriveTime:    arrival date and time (24-hour time)\n" +
                      "\t\tand click on add flight button\n" +
                      "the project will search the flight for user if they enter\n" +
                      "\t\t name:          the name of the airline \n" +
                      "\t\t source:           three-letter code of departure airport\n" +
                      "\t\t dest:          three-letter code of arrival airport\n" +
                      "and click on search button");
          }
      });

      DecoratorPanel decoratorPanelForAddFlight = new DecoratorPanel();
      decoratorPanelForAddFlight.add(verticalPanel);
      DecoratorPanel decoratorPanel1 = new DecoratorPanel();
      decoratorPanel1.add(verticalPanel1);
      DecoratorPanel decoratorPanel2 = new DecoratorPanel();
      decoratorPanel2.add(flexTable);

      RootPanel rootPanel = RootPanel.get();
      rootPanel.add(readMe, 10, 35);
      rootPanel.add(decoratorPanelForAddFlight, 0, Window.getClientHeight()/10);
      rootPanel.add(decoratorPanel1, Window.getClientWidth()/6, Window.getClientHeight()/10);
      rootPanel.add(decoratorPanel2, Window.getClientWidth()/3, Window.getClientHeight()/10);
  }
}
