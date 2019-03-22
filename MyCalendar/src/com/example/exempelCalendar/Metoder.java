package com.example.exempelCalendar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

/**
 * Contains complete list of methods that bring functionality to the program.
 */
public class Metoder {

    private ArrayList <ExtraInfo> myCalend;

    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    Date today = new Date();
    String todaysDate = dateFormat.format(today);
    Date d = null;


    /**
     * Constructor for new ArrayList
     */
    public Metoder () {
        this.myCalend = new ArrayList <ExtraInfo>();
    }

    /**
     * method AddNewEvent is used to add events.
     * if condition check for existing events, specifically for existing dates and hours saved in same index
     * if both date and hour of a new event is the same if the existing event, event is not added
     * @param event parameter with all event information passed from ExtraInfo
     * @return boolean false if chosen time and date already exists, true otherwise
     */
    public boolean addNewEvent(ExtraInfo event) {
        //if date and time are the same
        for (int k=0; k<this.myCalend.size(); k++) {
        if((findDate(event.getDate()) >= 0)  && ((findHour (event.getHour()) >= 0)  && (findTime(event)==findTime(event)))) {
            return false;
        }}

        myCalend.add(event);
        return true;
    }

    /**
     * method showEventList() is used to print out all saved events. It does not print out anything if the arraylist is empty
     * the events are printed chronologically according to the date
     * it only prints out upcoming events, even if previous events are saved
     * @return String empty
     */
    public String showEventList () {

        Collections.sort(myCalend, ExtraInfo.DateComparator);


         for (int a = 0; a < this.myCalend.size(); a++) {


            try {
                d = dateFormat.parse(todaysDate);

                Date datum = new SimpleDateFormat("dd-MM-yyyy").parse(myCalend.get(a).getDate());

            if (datum.compareTo(d) > 0 || datum.compareTo(d) == 0 ) {

                    if (this.myCalend.get(a).getDate().equals("")) {System.out.print("");} else {System.out.print(this.myCalend.get(a).getDate() + ", ");}
                    if (this.myCalend.get(a).getHour().equals("")) {System.out.print("");} else {System.out.print(this.myCalend.get(a).getHour() + ", ");}
                    if (!this.myCalend.get(a).getUrgency().equals("")) {System.out.print(this.myCalend.get(a).getActivity() + ", ");} else {System.out.print(this.myCalend.get(a).getActivity() + ". \n");}
                    if (this.myCalend.get(a).getUrgency().equals("")) {System.out.print("");} else {System.out.print(this.myCalend.get(a).getUrgency() + ", ");}
                    if (this.myCalend.get(a).getTypeOfEvent().equals("")) {System.out.print("");} else {System.out.print(this.myCalend.get(a).getTypeOfEvent() + ".\n");}
                }

            } catch (Exception e) {
            }
            }

            String noAgenda = "";

        return noAgenda;
    }

    /**
     * modifyEvent() is used to update the existing event. If the event is found, it accesses its index and sets new info on the position
     * If event has not been found (can be caused by wrong spelling, or upper/lowercase sensitivity), it prints out not found
     * @param oldEvent existing event passed to the method
     * @param newEvent new event which will be set to corresponding event position
     * @return boolean   true if found
     */
    public boolean modifyEvent (ExtraInfo oldEvent, ExtraInfo newEvent) {
        int foundPosition = findTime(oldEvent);
        if (foundPosition < 0) {
            System.out.println("Event " + oldEvent.getActivity() + " cannot be found.");
            return false;
        }

        this.myCalend.set (foundPosition, newEvent);
        System.out.println("Event '" + oldEvent.getActivity() + "', " + oldEvent.getDate() + " has been updated.");
        return true;
    }

    /**
     * removeEvent() is used to remove existing event from the arraylist
     * @param event represents event information passed to the method
     * @return boolean false if not found, true if removed
     */
    public boolean removeEvent(ExtraInfo event) {
        int foundPosition = findTime(event);
        if(foundPosition <0) {
            System.out.println("'" + event.getActivity() +"', was not found.");
            return false;
        }
        this.myCalend.remove(foundPosition);
        System.out.println("'" + event.getActivity() + "' ");
        return true;
    }


    /**
     * findTime() is used to find index number of the existing event
     * @param event event info passed to the method
     * @return int index number of the event
     */
    private int findTime (ExtraInfo event) {return this.myCalend.indexOf(event);
    }

    /**
     * findHour() is used to compare new time value with existing time values (regardless of the date information)
     * @param eventHour time info passed to the method
     * @return int -1 if such time hasn't been assigned to an event yet
     */
    private int findHour (String eventHour) {
        for( int i=0; i<this.myCalend.size(); i++) {
            ExtraInfo event = this.myCalend.get(i);
            if(event.getHour().equals(eventHour)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * findDate() is used to compare new date value with existing date values (regardless of the time information)
     * @param eventDate date info passed to the method
     * @return int -1 if such date hasn't been assigned to an event yet
     */
    private int findDate (String eventDate) {
        for( int i=0; i<this.myCalend.size(); i++) {
            ExtraInfo event = this.myCalend.get(i);
            if(event.getDate().equals(eventDate)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * findEvent() is used to compare existing events(text) with the new event. The method is case- and spelling-sensitive
     * @param eventActivity String activity value passed to the method
     * @return int -1 if such event does not has not been found
     */
    private int findEvent (String eventActivity) {
        for( int i=0; i<this.myCalend.size(); i++) {
            ExtraInfo event = this.myCalend.get(i);
            if(event.getActivity().equals(eventActivity)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * queryEvent() is used to remove and modify the existing events (later on), uses findEvent() to find existing events
     * @param eventFound represents existing event
     * @return event as as object
     */
    public ExtraInfo queryEvent(String eventFound) {
        int position = findEvent(eventFound);
        if(position >=0) {
            return this.myCalend.get(position);
        }

        return null;
    }

}

