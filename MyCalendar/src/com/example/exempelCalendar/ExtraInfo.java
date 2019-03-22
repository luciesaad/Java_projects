


package com.example.exempelCalendar;
import java.util.Comparator;

/**
 * Subclass ExtraInfo extends Calend.
 * User can store additional information about his event, namely
 * urgency of the event and type of event.
 ***/
public class ExtraInfo extends Calend{
    private String urgency;
    private String typeOfEvent;

    /**
     * Constructor for subclass
     * @param date date value taken from superclass
     * @param hour hour value taken from superclass
     * @param activity text value taken from superclass
     * @param urgency  urgency value extending original superclass
     * @param typeOfEvent type of event value extending original superclass
     */
    public ExtraInfo(String date, String hour, String activity, String urgency, String typeOfEvent) {
        super (date, hour, activity);
        this.urgency = urgency;
        this.typeOfEvent = typeOfEvent;
    }

    /**
     * Method getUrgency() is used to return urgency information
     * @return urgency
     */
    public String getUrgency() {return urgency;}

    /**
     * Method getTypeOfEvent() is used to return type information
     * @return typeOfEvent
     */
    public String getTypeOfEvent () {return typeOfEvent;}

    /**
     * Method createEvent() is used to create and return coplete compilation of information about an event
     * @param date represents String date
     * @param hour represents String hour
     * @param activity represents String activity
     * @param urgency represents String urgency
     * @param typeOfEvent represents String typeOfEvent
     * @return date, hour, activity, urgency, and typeOfEvent information
     */
    public static ExtraInfo createEvent (String date, String hour, String activity, String urgency, String typeOfEvent) {
        return new ExtraInfo(date,hour,activity,urgency,typeOfEvent);
    }

    /**
     * DateComparator creates new arraylist used to compare elements (dates) of the arraylist and sort it chronologically
     */
    public static Comparator<ExtraInfo> DateComparator = new Comparator<ExtraInfo>() {
        /**
         * method compare() is used to compare dates in the arraylist
         * @param e1 date1
         * @param e2 date2
         * @return comparison of 2 dates to sort it in ascending order
         */
        public int compare(ExtraInfo e1, ExtraInfo e2) {
            String EventDate1 = e1.getDate();
            String EventDate2 = e2.getDate();

            return EventDate1.compareTo(EventDate2);
        }
    };
}
