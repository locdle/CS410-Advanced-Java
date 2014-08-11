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
                    Window.alert("get here");
                  }
              });


          }
      });
      verticalPanel1.add(search);

      VerticalPanel verticalPanel2 = new VerticalPanel();
      verticalPanel2.add(new Label("AIRLINE NAME"));
      final TextBox seachAirlineNameInfo = new TextBox();
      verticalPanel2.add(seachAirlineNameInfo);
      verticalPanel2.add(new Label("DEPARTING AIRPORT"));
      final TextBox searchSrcInfo = new TextBox();
      verticalPanel2.add(searchSrcInfo);
      verticalPanel2.add(new Label("ARRIVING AIRPORT"));
      final TextBox searchDestInfo = new TextBox();
      verticalPanel2.add(searchDestInfo);
      Button search1 = new Button("SEARCH");
      search1.addClickHandler(new ClickHandler() {
          @Override
          public void onClick(ClickEvent event) {
              PingServiceAsync async = GWT.create(PingService.class);
              String name = seachAirlineNameInfo.getText();
              String src1 = searchSrcInfo.getText();
              if (AirportNames.getName(src1.toUpperCase()) == null) {
                  Window.alert("airport code " + src1 + " is not valid");
                  return;
              }
              String dest1 = searchDestInfo.getText();
              if (AirportNames.getName(dest1.toUpperCase()) == null) {
                  Window.alert("airport code " + dest1 + " is not valid");
                  return;
              }
              async.searchAFlight(name, src1, dest1, new AsyncCallback<AbstractFlight>() {
                  @Override
                  public void onFailure(Throwable caught) {
                      Window.alert(caught.toString());
                  }

                  @Override
                  public void onSuccess(AbstractFlight result) {
                      if(result != null) {
                          Window.alert(result.toString());
                      }
                      else{
                          Window.alert("There are no flight matching in the server");
                      }
                  }
              });
          }
      });
//      search1.addClickHandler(new ClickHandler() {
//          @Override
//          public void onClick(ClickEvent event) {
//              PingServiceAsync async = GWT.create(PingService.class);
//              String name = seachAirlineNameInfo.getText();
//              String src1 = searchSrcInfo.getText();
//              if (AirportNames.getName(src1.toUpperCase()) == null) {
//                  Window.alert("airport code " + src1 + " is not valid");
//                  return;
//              }
//              String dest1 = searchDestInfo.getText();
//              if (AirportNames.getName(dest1.toUpperCase()) == null) {
//                  Window.alert("airport code " + dest1 + " is not valid");
//                  return;
//              }
//              async.searchFlight(name, src1, dest1, new AsyncCallback<AbstractAirline>() {
//                  @Override
//                  public void onFailure(Throwable caught) {
//                      Window.alert(caught.toString());
//                  }
//
//                  @Override
//                  public void onSuccess(AbstractAirline result) {
//                      Window.alert("get here");
//                  }
//              });
//
//
//          }
//      });
      verticalPanel2.add(search1);

      Button readMe = new Button("Help");
      readMe.addClickHandler(new ClickHandler() {
          @Override
          public void onClick(ClickEvent clickEvent) {
              Window.setTitle("Read Me");
              Window.alert("Loc Le - Advance Java - Project 5 \n" +
                      "This is a  README for this project airline \n" +
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
                      "\t\t source:           three-letter code of departure airport\n" +
                      "\t\t departTime:    departure date and time (24-hour time)\n" +
                      "\t\t dest:          three-letter code of arrival airport\n" +
                      "\t\t arriveTime:    arrival date and time (24-hour time)");
          }
      });

      DecoratorPanel decoratorPanelForAddFlight = new DecoratorPanel();
      decoratorPanelForAddFlight.add(verticalPanel);
      DecoratorPanel decoratorPanel1 = new DecoratorPanel();
      decoratorPanel1.add(verticalPanel1);
      DecoratorPanel decoratorPanel2 = new DecoratorPanel();
      decoratorPanel2.add(flexTable);
      DecoratorPanel decoratorPanel3 = new DecoratorPanel();
      decoratorPanel3.add(verticalPanel2);


      RootPanel rootPanel = RootPanel.get();
      rootPanel.add(readMe, 10, 35);
      rootPanel.add(decoratorPanelForAddFlight, 0, Window.getClientHeight()/10);
      rootPanel.add(decoratorPanel1, Window.getClientWidth()/6, Window.getClientHeight()/10);
      rootPanel.add(decoratorPanel2, Window.getClientWidth()/3, Window.getClientHeight()/10);
      rootPanel.add(decoratorPanel3, Window.getClientWidth()/6, Window.getClientHeight()/2);
  }
}
