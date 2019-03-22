package com.example.primtal;

        import javax.swing.*;
        import java.util.ArrayList;

public class Primtal {

    private static ArrayList<Integer> primNumList = new ArrayList<Integer>(); //chosen data structure for the project
    private static JFrame frame = new JFrame(""); //parent for the input dialogs
    private static int notPrim = 0;

    //main method
    public static void main(String[] args) {
        boolean quit = false;
        int sendInt = 0;
        ArrayList<Integer> arrayCopy = primNumList; //local copy of arrayList that is sent as parameter

        Object[] options = {"Add number",
                "Sort numbers",
                "Search",
                "Generate primes",
                "Exit program"};

        while (!quit) { //as long as the user doesn't chose exit program
            int choice = JOptionPane.showOptionDialog(frame,
                    "What would you like to do?",
                    "PrimeNumbers Menu",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,                    //button titles
                    options[0]);                //default button (works with enter directly)

            switch (choice) {
                case 0:
                    isPrime(obtainInput());
                    if(primNumList.size()>0) { //do not inform about the array, if it's empty
                        System.out.println("The current array: " + primNumList);
                    }
                    if (notPrim>0){ //print text only if you the input is a prime number
                        System.out.println(notPrim + " is not a prime number");
                    }
                    break;

                case 1:
                    System.out.println("Array before sort: " + primNumList + "\n" +
                            "Array after sort: " + sortArray(arrayCopy)); //sends in the copy of the latest array of prime nums as argument
                    break;

                case 2:
                    String searchForInt = JOptionPane.showInputDialog(frame,
                            "Enter a number you want to find: ",
                            "Search for Number",
                            JOptionPane.QUESTION_MESSAGE);
                    searchArray(searchForInt); //the searched value and the sorted array are sent as parametes
                    break;

                case 3:
                    System.out.println("The following array was generated: " + genNextPrim());
                    break;

                case 4:
                    System.out.println("Closing...");
                    quit = true; //the condition for the while loop changes here
                    System.exit(0); //exits input dialog windows
                    break;
            }
        }
    }

    //method obtainInput() opens a dialog window with the user, checks for correct type, and returns value(int)
    //the return value is passed as parameter in isPrime()
    private static int obtainInput() {
        int isInteger = 0;
        boolean throwsExc = true;

        do { //do loop will run until it gets a value of type int

            String inputPrimeNum = JOptionPane.showInputDialog(frame,
                    "Enter a number: ",
                    "Adding a Number",
                    JOptionPane.QUESTION_MESSAGE);

            if (inputPrimeNum == null) { //if the user presses cancel
                JOptionPane.showMessageDialog(frame, "Canceling...", "Cancelled", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            } else {
                try {
                    isInteger = Integer.parseInt(inputPrimeNum); //parse String input to integer
                    throwsExc = false;

                } catch (NumberFormatException e) { //if parse throws exception -> error feedback
                    JOptionPane.showMessageDialog(frame, "Please enter an integer!", "Incorrect input value", JOptionPane.ERROR_MESSAGE);
                }

            }
        }
        while (throwsExc); //while exception is thrown (input cannot be parsed to integer)
        return isInteger; // returns input value
    }

    //method checks if the obtained number is a prime num, if yes, calls addPrimNum() and returns true
    private static boolean isPrime(int inputNum) {
        if (inputNum % 2 == 0 && inputNum != 2) {//the only prime num/2 is 2 -> other even nums are NOT prime nums
            notPrim = inputNum; //notPrim value changes, user will get the message
            return false;
        }
        for (int i = 3; i * i <= inputNum; i += 2) { //check the odd numbers till sqr root of the num is <= num
            if (inputNum % i == 0) {// is dividable by i
                notPrim = inputNum;
                return false;
            }
        }
        notPrim=0; //notPrim remains 0 if prime number is found (won't get the message saying contrary)
        addPrimNum(inputNum); //run method that adds the prime number to an array
        return true; //is prime num
    }

    //method addPrimNum adds the prime num to the array list,
    // calls countSum() method with each addition to the array
    private static ArrayList addPrimNum(int inputNum) {

        for (int j = 0; j < primNumList.size(); j++) {
            if (primNumList.get(j) == inputNum) {
                System.out.println("The given input already exists");
                return primNumList;
            }
            else if (inputNum==0) {
                return primNumList;
            }
        }
        primNumList.add(inputNum); //adds a value to the
        countSum(inputNum); //calls sum method

        return primNumList;
    }

    //countSum() method counts the sum of existing elements in the array
    private static void countSum(int inputNum){
        int sum = 0;

        for (int k = 0; k < primNumList.size(); k++) {
            sum += primNumList.get(k);
        }
        if (sum != inputNum) { //in case array.size is 1, sum and inputNum would be same -> would write that given input already exists
            isPrime(sum);
        }
    }

    //method sortArray() uses QuickSort algorithm, where the 'middle' element is set as pivot
    private static ArrayList sortArray(ArrayList <Integer> passedArray) {

        if(passedArray.size() <= 1){ //sorting in array list of size 1 or smaller won't continue
            return passedArray;
        }

        int middle = passedArray.size()/2;
        int pivot = passedArray.get(middle);                            //sets "middle" element as pivot

        ArrayList<Integer> smaller = new ArrayList<Integer>();          //array for lower nums
        ArrayList<Integer> greater = new ArrayList<Integer>();          //array for higher nums

        for (int n = 0; n < passedArray.size(); n++) {
            if (passedArray.get(n) <= pivot) {                          //n < than pivot -> saved in array smaller
                if (n == middle) {
                    continue;
                }
                smaller.add(passedArray.get(n));
            } else {                                                    // n > than pivot -> array greater
                greater.add(passedArray.get(n));
            }
        }

        return integrate(sortArray(smaller), pivot, sortArray(greater));  //call for integrate() -> return result
    }

    //method integrate() connects arrays and the pivot element
    private static ArrayList integrate(ArrayList<Integer> smaller, int pivot, ArrayList<Integer> greater) {
        ArrayList<Integer> sortedList = new ArrayList<Integer>();  // new arraylist that will collect all parts

        for (int l = 0; l < smaller.size(); l++) {    //adds array with smaller numbers
            sortedList.add(smaller.get(l));
        }

        sortedList.add(pivot);                        //adds pivot value

        for (int m = 0; m < greater.size(); m++) {    //adds array with greater numbers
            sortedList.add(greater.get(m));
        }
        primNumList=sortedList;                       //global array takes result of the local copy array
        return primNumList;                            //returns new, sorted list
    }

//method searchArray() compares searched value to all elements in the existing array
    private static void searchArray (String searchForInt) {
        int match = 0;

        for (int i=0; i<primNumList.size(); i++) {
            if (primNumList.get(i) == Integer.parseInt(searchForInt)) {
                match = primNumList.get(i);
            }
        }
        if(match==0) {
            System.out.print(searchForInt + " was not found. ");
            //call getClosestValue() to find the closest value to the searched number
            System.out.println("The closest value to your number is: " + getClosestValue(Integer.parseInt(searchForInt)));
        }
        else {
            System.out.println(searchForInt + " was found. ");
        }
    }

    //method getClosestValue() searches for the closest value to the given input from existing array
    private static int getClosestValue(int searchForInt)  {

        if(searchForInt < primNumList.get(0)) { //if given input is smaller than the lowest value, return the lowest value
            return primNumList.get(0);
        }
        if(searchForInt > primNumList.get(primNumList.size()-1)) { //if input is higher than the highest value, return the highest value
            return primNumList.get(primNumList.size()-1);
        }

        int low = 0;                            //low and high represent the start and end index in the array
        int high = primNumList.size() - 1;

        while (low <= high) {
            int middle = (high + low) / 2;                 //get middle value (average, in case of decimals rounded down(int value))

            if (searchForInt < primNumList.get(middle)) { //if input is lower than the middle value
                high = middle - 1;                        // middle-1 becomes the new 'end' point
            } else if (searchForInt > primNumList.get(middle)) {    //if input is higher than the middle value
                low = middle + 1;                                       //middle+1 becomes the new 'start' point
            } else {
                return primNumList.get(middle);           //returns middle value if it equals the input value (at some point of the while loop)
            }
        }
       //returns the closest value to the searched number
        return (primNumList.get(low) - searchForInt) < (searchForInt - primNumList.get(high)) ? primNumList.get(low) : primNumList.get(high);
    }

    //method genNextPrim() generates a chosen amount of prime numbers following the highest value in the array
    private static ArrayList genNextPrim() {
        sortArray(primNumList); //sort first
        int lastIndex = primNumList.get(primNumList.size()-1);
        int thisMany;
        int times = 0;
        boolean throwsExc = true;

        System.out.println("What you began with: "+ sortArray(primNumList));

        do { //do loop until value of integer is entered
            String howMany = JOptionPane.showInputDialog(frame,
                    "Enter number of prime numbers you want to generate: ",
                    "Generate prime",
                    JOptionPane.QUESTION_MESSAGE);
            try {
                thisMany = Integer.parseInt(howMany); //parse String input to integer
                times = thisMany;
                throwsExc = false;

            } catch (NumberFormatException e) { //if parse throws exception -> error feedback
                JOptionPane.showMessageDialog(frame, "Please enter an integer!", "Incorrect input value", JOptionPane.ERROR_MESSAGE);
            }
        } while (throwsExc); //ask for a correct input until you get it


        while(true) {    //infinite loop principal
            if(lastIndex==2) { //increment by 2 wouldn't work in this case
                lastIndex=3;
            }
            lastIndex +=2;      //start from the highest prime num in array and check all prime nums (odd nums)

            if(isPrime(lastIndex)){     //if found num is prime num and is added, times decreases
                times--;
            }
            if(times==0){
                break;                              //when a chosen number of prime nums is found, break
            }
        }

        sortArray(primNumList); //sort before print
        return primNumList;
    }
}