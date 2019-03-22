package com.example.exempelCalendar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;

/**
 * <h1> MyCalendar </h1>
 *  <p> The program imitates functions of a simple calendar. A user can create new event and modify or remove the existing ones. </p>
 *  <p> The user can also search through events (by writing exactly the name of the event) or print an event-list. </p>
 *  <p> Extra functions: </p>
 *  <ul> <li> events should print out chronologically </li>
 *          <li> dates should have particular format they follow (dd-MM-yyyy) for optimal function </li>
 *          <li> user can print agenda only for upcoming events, not events from the past </li></ul>
 * @author  Lucie Saad
 * @version 1.0
 * */
public class Menu {

    private static Scanner scanner = new Scanner (System.in);
    private static DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private static Date dnes = new Date();
    private static Metoder methods = new Metoder();

    /**
     * The main method of the program, that saves user-written data and offers options(menu) to use them further.
     * The menu is based on switch - if user writes in any other than switch option(0-6), program finishes
     * @param args Unused.
     */
    public static void main (String [] args) {
        boolean quit = false;

        System.out.println("\nWelcome to MyCalendar!\n" +
                "Today's date is: " + dateFormat.format(dnes));

        while (!quit) {
            System.out.println("Write number (0-6):  ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 0:
                    showAlt();
                    break;

                case 1:
                    methods.showEventList();
                    break;

                case 2:
                    addNewEvent();
                    break;
                case 3:
                    modifyEvent();
                    break;
                case 4:
                    removeEvent();
                    break;
                case 5:
                    searchEvent();
                    break;
                case 6:
                    System.out.println("\n Closing MyCalendar...");
                    quit = true;
                    break;
            }
        }
    }

    /**
     * showAlt() prints out alternatives (menu)
     */
    private static void showAlt(){
                System.out.println(
                        "0  - Show alternatives\n" +
                        "1  - Show Event list\n" +
                        "2  - Create new event\n" +
                        "3  - Modify existing event\n" +
                        "4  - Remove existing event\n" +
                        "5  - Search events\n" +
                        "6  - Close MyCalendar");
    }

    /**
     * Option to add new event. The user has an option to chose to add more information or not, the event is going to be saved in both cases.
     */
    private static void addNewEvent(){
        System.out.println("Write date (dd-MM-yyyy): ");
        String date = scanner.nextLine();
        System.out.println("Write time (t.ex. 14:00): ");
        String hour = scanner.nextLine();
        System.out.println("Write your event: ");
        String activity = scanner.nextLine();
        System.out.println("Do you want to add more information? (yes/no):  ");
        String answer = scanner.nextLine();
        if (answer.equals("no")) {
            ExtraInfo newEvent = ExtraInfo.createEvent(date, hour, activity, "",""); // Vi skapar ett objekt direkt där vi kommer åt metoden createEvent i subklassen
            if (methods.addNewEvent(newEvent)) {
                System.out.println("New event was added: \nDate: " + date + ", \nTime: " + hour + ", \nEvent: " + activity);
            } else {
                System.out.println("Cannot be added, " + hour + " is already booked for different event.");
            }
        }
            else {
                System.out.println("How urgent is the event? (1-must be done, 2-middle, 3-eh...)");
                String urgency = scanner.nextLine();
                System.out.println("Write type of event (family/friends/work/school/household/other): ");
                String typeOfEvent = scanner.nextLine();
            ExtraInfo newEvent = ExtraInfo.createEvent(date, hour, activity, urgency, typeOfEvent);
            if (methods.addNewEvent(newEvent)) {
                System.out.println("New event was added: \nDate: " + date + ", \nTime: " + hour + ", \nEvent: " + activity + ", \nUrgency: " + urgency + ",\nType of event: " + typeOfEvent);
            } else {
                System.out.println("Cannot be added, " + hour + " is already booked for different event.");
            }
        }
    }

    /**
     * The option to modify existing event.
     *
     */
    private static void modifyEvent () {
        System.out.println("Write in event you want to modify:  ");
        String activity = scanner.nextLine();
        ExtraInfo existingEvent = methods.queryEvent(activity);
        if (existingEvent == null) {
            System.out.println("Event not found.");
            return;
        }

        System.out.print("Write in new date: ");
        String newDate = scanner.nextLine();
        System.out.print("Write in new hour: ");
        String newHour = scanner.nextLine();
        System.out.print("Write in new event: ");
        String newText = scanner.nextLine();
        System.out.println("Do you want to add extra information to the event? (yes/no) ");
        String answer = scanner.nextLine();
        if (answer.equals("no")) {
            ExtraInfo newerEvent = ExtraInfo.createEvent(newDate, newHour, newText, "", "");
            if (methods.modifyEvent(existingEvent, newerEvent)) {
                System.out.println("New event: \n Date: " + newDate + "\n Time " + newHour + "\n Event: " + newText);
            } else {
                System.out.println("Modification failed. Try again.");
            }
        } else {
            System.out.println("How urgent is the event? (1-must be done, 2-middle, 3-eh...)");
            String newUrgency = scanner.nextLine();
            System.out.println("Write type of event (family/friends/work/school/household/other): ");
            String newTypeOfEvent = scanner.nextLine();
            ExtraInfo newerEvent = ExtraInfo.createEvent(newDate, newHour, newText, newUrgency, newTypeOfEvent);
            if (methods.modifyEvent(existingEvent, newerEvent)) {
                System.out.println("\nDate: " + newDate + ", \nTime: " + newHour + ", \nEvent: " + newText + ", \nUrgency: " + newUrgency + ",\nType of event: " + newTypeOfEvent);
            } else {
                System.out.println("Cannot be updated, " + newHour + " is already booked for different event.");
            }
        }
    }

    /**
     * The option to remove the existing event.
     */
    private static void removeEvent() {
        System.out.println("Write in event you want to remove: ");
        String willRemove = scanner.nextLine();
        ExtraInfo existingEvent = methods.queryEvent(willRemove);
        if ((existingEvent) == null) {
            System.out.println("The event you want to remove has not been found. ");
            return;
        }

        if(methods.removeEvent(existingEvent)) {
            System.out.print("has been removed! ");
        } else {
            System.out.println("The event cannot be removed. ");
        }

    }

    /**
     * The option to search through events and display information about it. The user searches events (not dates/times)
     */
    private static void searchEvent () {
        System.out.println("Write the name of the event: ");
        String eventName= scanner.nextLine();
        Calend existingEvent = methods.queryEvent(eventName);
        if (existingEvent == null) {
            System.out.println("Event not found. ");
            return;
        }
        System.out.println("Event: " + existingEvent.getActivity() + " is planned for: " + existingEvent.getDate() + ", " + existingEvent.getHour());
    }
}
