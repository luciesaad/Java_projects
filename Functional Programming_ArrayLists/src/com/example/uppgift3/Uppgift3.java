package com.example.uppgift3;

import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Uppgift3 {
    private static ArrayList<String> stringList = new ArrayList<String>(); //string arraylist
    private static ArrayList<Integer> intList = new ArrayList<Integer>(); // fibonacci int arraylist
    private static LocalDate date = LocalDate.of(2019,1,1); // holds set value of a local date
    private static JFrame frame = new JFrame(""); //parent frame for the input dialogs

    public static void main(String[] args) {    //main method
        boolean quit = false;

        //array of menu options
        Object[] options = {"Print array",
                "Add text",
                "Search",
                "Change",
                "Delete",
                "Sort",
                "Count words",
                "Encrypt",
                "Decrypt",
                "Exit"};

        while (!quit) { //runs as long as the user doesn't exit
            int choice = JOptionPane.showOptionDialog(frame,    //menu input dialog
                    "What would you like to do?",
                    "Menu",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,                    //button titles
                    options[1]);                //default button (works with enter directly)

            switch (choice) {
                case 0:
                    System.out.println("Current array: " + getStringList());
                    break;
                case 1:
                    addText();
                    System.out.println("Your text was saved on "+ getLocalDate() + "\n");
                    break;
                case 2:
                   String search = JOptionPane.showInputDialog(frame,
                            "Enter text: ",
                            "Search text",
                            JOptionPane.QUESTION_MESSAGE);

                    if (contains(getStringList().size() - 1, search)) { //if match is found in the array
                        System.out.println(search + " was found.");
                    } else {
                        System.out.println(search + " was not found.");
                    }
                    break;
                case 3:
                    String change = JOptionPane.showInputDialog(frame,
                            "Enter text you want to make changes on: ",
                            "Change text",
                            JOptionPane.QUESTION_MESSAGE);

                    if (contains(getStringList().size() - 1, change)) { //if a match is found in the array
                        changeText(change);
                    }
                    break;
                case 4:
                    String delete = JOptionPane.showInputDialog(frame,
                            "Enter text you want to delete: ",
                            "Delete text",
                            JOptionPane.QUESTION_MESSAGE);

                    if (contains(getStringList().size() - 1, delete)) { //if a match is found in the array
                        deleteText(delete);
                        System.out.println(delete + " was deleted.");
                    } else {
                        System.out.println(delete + " was not found.");
                    }
                    break;
                case 5:
                    Object[] sortMenu = {
                            "Ascending",
                            "Descending",
                            "Length"
                    };

                    int sortChoice = JOptionPane.showOptionDialog(frame,    //sort input dialog
                            "Which sort?",
                            "Sort options",
                            JOptionPane.YES_NO_CANCEL_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            sortMenu,                    //button titles
                            sortMenu[0]);               //first button as default

                    switch(sortChoice){
                        case 0:
                            sortAlph();
                            break;
                        case 1:
                            sortAlph2();
                            break;
                        case 2:
                            sortLength();
                    }
                    break;
                case 6:
                    System.out.println("The arrayList contains " + countWords(getStringList().size()-1, 0) + " words.");
                    break;
                case 7:
                    encrypt();
                    break;
                case 8:
                    decrypt();
                    break;
                case 9:
                    System.out.println("Closing the program...");
                    quit=true;             //negates the condition for the while loop
                    System.exit(0); //exits all input dialogs
                    break;
            }
        }
    }

    //method addText() adds new input to the string array(if input isn't empty)
    private static void addText() {
        String inputText;
        do {
            inputText = JOptionPane.showInputDialog(frame,
                    "Enter text: ",
                    "Adding text",
                    JOptionPane.QUESTION_MESSAGE);

            if (!inputText.equals("")) { //if not empty string, add input to getStringList()
                if (contains(getStringList().size() - 1, inputText)){
                    System.out.println(inputText + " already exist");
                    return;
                }
                getStringList().add(inputText);

                System.out.println("The current string array: " + getStringList());

                makeIntArray();
                isEven();
            }
        }
        while (inputText.equals("")); //do loop runs till it gets a value(cannot enter an empty value)
    }

    //method makeIntArray() increments the int array with a fibonacci number with each text input
    private static void makeIntArray(){
        if (getIntList().size() > 1) { //if size >1, add a sum of the last two nums in the int array (fibonacci principal)
            getIntList().add(getIntList().get(getIntList().size() - 1) + getIntList().get(getIntList().size() - 2));
        } else { //else add 1
            getIntList().add(1);
        }

        System.out.print("The current fibonacci array: " + getIntList().toString());
    }

    //method isEven() defines if the number is even or odd
    private static void isEven(){
        if ((getIntList().get(getIntList().size() - 1) % 2 == 0) && getIntList().size() > 1) { //if last added num%2==0 -> is even
            System.out.println(" " + getIntList().get(getIntList().size() - 1) + " is even.");
        } else {
            System.out.println(" " + getIntList().get(getIntList().size() - 1) + " is odd.");
        }
    }

    /* method contains() uses recursion to search for input string, returns true if a match was found
     * used in functions search, change and delete
     */
    private static boolean contains(int size, String search) {
        if (size != -1) {        //recursion stops with this condition
            if (getStringList().get(size).equals(search)) {
                return true;
            }
            if (contains(size - 1, search)) {
                return true;
            }
        }
        return false; //if match was not found
    }

    //method deleteText() removes an existing text
    private static void deleteText(String delete) {
        getStringList().remove(delete);
    }

    //method changeText() recieves the string value to be changed and changes it
    private static void changeText(String change) {
        String changeTo = JOptionPane.showInputDialog(frame,
                "Enter changes: ",
                "Make changes",
                JOptionPane.QUESTION_MESSAGE);

        setStringList(getStringList().indexOf(change), changeTo);
        System.out.println(change + " was changed to " + changeTo);
    }

    /* method sortAlph() uses bubble sort to sort string array elements ascending alphabetically
     * int i, j are of local significance only */
    private static void sortAlph() {
        for (int i = 0; i < getStringList().size() - 1; i++) {
            for (int j = i + 1; j < getStringList().size(); j++) {
                if (getStringList().get(i).compareTo((getStringList().get(j))) > 0) {
                    String temp = getStringList().get(i);
                    setStringList(i, getStringList().get(j));
                    setStringList(j, temp);
                }
            }
        }
        System.out.println("After ascending sort: " + getStringList()); //print sorted list
    }

    /* method sortAlph2() uses bubble sort to sort string array elements descending alphabetically
     * int i, j are of local significance only */
    private static void sortAlph2(){

        for (int i = 0; i < getStringList().size() - 1; i++) {
            for (int j = i + 1; j < getStringList().size(); j++) {
                if (getStringList().get(i).compareTo((getStringList().get(j))) <= 0) {
                    String temp = getStringList().get(i);
                    setStringList(i, getStringList().get(j));
                    setStringList(j, temp);
                }
            }
        }
        System.out.println("After descending sort: " + getStringList());
    }

    // This method sorts the string array in regards to the length of the strings
    private static void sortLength(){
        getStringList().sort((s1, s2) -> s2.length() - s1.length());
        System.out.println("After length sort: " + getStringList());
    }

    // method countWords() uses recursion to count the number of words in the string arraylist based on the occurance of white space
    private static int countWords(int strings, int words){
      if (strings != -1) { //stops recursion
        for (int i = 0; i < getStringList().get(strings).length() - 1; i++) {

            if (getStringList().get(strings).charAt(i) == ' ' || getStringList().get(strings).charAt(i) == '\n'
                    || getStringList().get(strings).charAt(i) == '\t') {
                //the following code considers repeated white spaces as one
                if (getStringList().get(strings).charAt(i -1) != ' ' && getStringList().get(strings).charAt(i -1) != '\n'
                        && getStringList().get(strings).charAt(i - 1) != '\t') {//if the two latest char is not a whitespace add 1 to words
                    words++;
                }
            }
        }

        words += countWords(strings - 1, 0); //recursive call where each recursion increments words
        words++;//always adds 1 to words after recursion ends
    }
    return words;
}
    //encrypts every string in the arraylist
    private static void encrypt(){
        for (int i =0; i < getStringList().size(); i++ ){//Loops for every string in the arraylist
            char[] encrypt = getStringList().get(i).toCharArray();//Gets the current string from the arraylist
            for (int j = 0; j < getStringList().get(i).length(); j++){//Loops for everey character in the string

                if (j == 0){
                    encrypt[j] = (char) (encrypt[j] + 20);
                }
                else {
                    encrypt[j] = (char) (encrypt[j] + 24);
                }
            }
            setStringList(i, new String(encrypt));//The new String replaces the old one
        }
    }
    //Decrypt every string in the arraylist
    private static void decrypt(){

        for (int i =0; i < getStringList().size(); i++ ){//Loops for every string in the arraylist
            char[] decrypt = getStringList().get(i).toCharArray();//Gets the current string from the arraylist
            for (int j = 0; j < getStringList().get(i).length(); j++){//Loops for everey character in the string

                if (j == 0){
                    decrypt[j] = (char) (decrypt[j] - 20);
                }
                else {
                    decrypt[j] = (char) (decrypt[j] - 24);
                }
            }
            setStringList(i, new String(decrypt));//The new String replaces the old one
        }
    }
    //get method for the string arraylist
    private static ArrayList<String> getStringList(){
        return stringList;
    }
    //set method for the string arraylist, with arguments for index and new input to be set in the array
    private static void setStringList(int index, String newString){
        stringList.set(index, newString);
    }
    //get method for the int list
    private static ArrayList<Integer> getIntList(){
        return intList;
    }
    //get method for obtaining the current date info
    private static String getLocalDate(){
        return String.valueOf(date.now());
    }
}


