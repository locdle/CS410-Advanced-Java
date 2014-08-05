package edu.pdx.cs410J.locle.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import edu.pdx.cs410J.AbstractFlight;
import edu.pdx.cs410J.AbstractAirline;

import java.util.Collection;

/**
 * A basic GWT class that makes sure that we can send an airline back from the server
 */
public class AirlineGwt implements EntryPoint {
    public void onModuleLoad() {
        Button button = new Button("Ping Server");
        button.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent clickEvent) {
                PingServiceAsync async = GWT.create(PingService.class);
                async.ping(new AsyncCallback<AbstractAirline>() {

                    public void onFailure(Throwable ex) {
                        Window.alert(ex.toString());
                    }

                    public void onSuccess(AbstractAirline airline) {
                        StringBuilder sb = new StringBuilder(airline.toString());
                        Collection<AbstractFlight> flights = airline.getFlights();
                        for (AbstractFlight flight : flights) {
                            sb.append(flight);
                            sb.append("\n");
                        }

                        Window.alert(sb.toString());
                    }
                });
            }
        });

        Button addFlight = new Button("ADD FLIGHT");
        VerticalPanel verticalPanel = new VerticalPanel();
        verticalPanel.add(new Label("AIRLINE NAME"));
        verticalPanel.add(new TextBox());
        verticalPanel.add(new Label("FLIGHT NUMBER"));
        verticalPanel.add(new TextBox());
        verticalPanel.add(new Label("DEPARTING AIRPORT"));
        verticalPanel.add(new TextBox());
        verticalPanel.add(new Label("DEPARTING DATE & TIME"));
        verticalPanel.add(new TextBox());
        verticalPanel.add(new Label("ARRIVING AIRPORT"));
        verticalPanel.add(new TextBox());
        verticalPanel.add(new Label("ARRIVING DATE & TIME"));
        verticalPanel.add(new TextBox());
        verticalPanel.add(addFlight);


        Button search = new Button("SEARCH");
        VerticalPanel verticalPanel1 = new VerticalPanel();
        verticalPanel1.add(new Label("AIRLINE NAME"));
        verticalPanel1.add(new TextBox());
        verticalPanel1.add(new Label("DEPARTING AIRPORT"));
        verticalPanel1.add(new TextBox());
        verticalPanel1.add(new Label("ARRIVING AIRPORT"));
        verticalPanel1.add(new TextBox());
        verticalPanel1.add(search);

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
                        "\t\t src:           three-letter code of departure airport\n" +
                        "\t\t departTime:    departure date and time (24-hour time)\n" +
                        "\t\t dest:          three-letter code of arrival airport\n" +
                        "\t\t arriveTime:    arrival date and time (24-hour time)");
            }
        });

        RootPanel rootPanel = RootPanel.get();
//        rootPanel.add(button);
        rootPanel.add(readMe, 0, 0);
        rootPanel.add(verticalPanel, 0, 50);
        rootPanel.add(verticalPanel1, 200, 50);
    }
}
