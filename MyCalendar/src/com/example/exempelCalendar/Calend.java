



package com.example.exempelCalendar;

/**
 * Superclass Calend
 * includes date, hour, activity of type String. **/
public class Calend {
    private String date;
    private String hour;
    private String activity;



    /**
     * Constructor for Calend
     * @param date holds value for date
     * @param hour holds value of hour
     * @param activity holds value of activity
     */
    public Calend (String date, String hour, String activity) {
        this.date = date;
        this.hour = hour;
        this.activity = activity;

    }


    /**
     * method getDate () is used to return date information
     * @return value of date
     */
    public String getDate() {return date;}

    /**
     * method getHour() is used to return hour information
     * @return value of hour
     */
    public String getHour() {return hour;}

    /**
     * method getActivity() is used to return the actual event info
     * @return value of the event user wants to safe
     */
    public String getActivity() {return activity;}

}
