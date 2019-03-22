package com.example.fn1arrays;

import java.util.Arrays;
import java.util.Scanner;

public class Fn1Arrays {
        public static void main(String[] args) {

            char[] charArray1 = {'s', 't', 'r', 'i', 'n', 'g'};
            char[] charArray2 = {'s', 'i', 'n', 'g'};
            int numChars1 = charArray1.length - 1; //represents the length of array1
            int numChars2 = charArray2.length - 1; //represents the length of array2
            String string = "";

            allFunctions(charArray1,charArray2, numChars1, numChars2, string); {
            }
        }
        // all functions input
        public static void allFunctions(char[] allChars1, char[] allChars2, int numChars1, int numChars2, String string) {

            System.out.println("Original array 1" + Arrays.toString(allChars1));
            System.out.println("Original array 2" + Arrays.toString(allChars2));
            System.out.println(detectTInArray(allChars1, numChars1, string) + "\n" + detectTInArray(allChars2, numChars2, string));

            System.out.println("uppgift d) ");
            compareCharArrays(removeDuplicate(allChars1, numChars1, string), removeDuplicate(allChars1, numChars2, string));
            System.out.println("Uppgift a) ");
            System.out.println(changeToT(allChars1, numChars1) + "\n" + changeToT(allChars1, numChars2));
            System.out.println("Uppgift b) ");
            System.out.println(toCapital(allChars1, numChars1)  + "\n" + toCapital(allChars2, numChars2));
            System.out.println("Uppgift c) ");
            System.out.println(removeDuplicate(allChars1, numChars1, string) + "\n" + removeDuplicate(allChars1, numChars1, string));
            System.out.println("uppgift e) ");
            inputCharArray();
        }

        //Grunduppgift - returns true if array contains 't' using recursion. Calls methods toCapital and removeDuplicate
        public static boolean detectTInArray(char[] allChars, int numChars, String string) {
            if (numChars > 0) {
                if (allChars[numChars] == 't') {
                    return true;
                }
                return
                        detectTInArray(allChars, numChars - 1, string);
            }
            toCapital(allChars, numChars);
            removeDuplicate(allChars, numChars, string);
            return false;
        }


        //Uppgift a)
        // method changeToT uses recursion to find 'r' in array and overwrite it to 't'
        public static String changeToT(char[] allCharsA, int numChars) {

            if (numChars > 0) {
                if (allCharsA[numChars] == 'r') {
                    allCharsA[numChars] = 't';
                }
                changeToT(allCharsA, numChars - 1);
            }

            return Arrays.toString(allCharsA);
        }

        //Uppgift b)
        // method toCapital uses recursion to turn existing ts to capital Ts
        public static String toCapital(char[] allCharsB, int numChars) {
            if (numChars > 0) {
                if (allCharsB[numChars] == 't') {
                    allCharsB[numChars] = Character.toUpperCase(allCharsB[numChars]);
                }
                toCapital(allCharsB, numChars - 1);
            }
            return Arrays.toString(allCharsB);
        }

        //uppgift c)
        private static String removeDuplicate(char[] array, int numChars, String string) {
            String localArray=string;
            String stringified;
            if (localArray.indexOf(array[numChars]) == -1) {   // check if a char already exists, if not then return -1
                localArray = localArray + array[numChars];
                if (numChars-1 == -1) {                         // after recursion goes through all elements, following code runs

                    StringBuilder result= new StringBuilder();
                    result.append(localArray);                  // append a string into StringBuilder


                    result = result.reverse();                  // reverse StringBuilder result and
                    stringified = result.toString();            // cast it to String

                    return Arrays.toString(stringified.toCharArray());  //returns result of method removeDuplicate

                }
            }return removeDuplicate(array, numChars - 1, localArray); //recursion
        }

    //uppgift d)
    // method compareCharArrays compares char arrays and desides if they are identical or not
    public static void compareCharArrays(String firstChar, String secondChar){
        if(firstChar.equals(secondChar)){
            System.out.println("Array 1 and Array 2 are identical");
        }
        else {
            System.out.println("Array 1 and Array 2 are NOT identical");
        }
    }

    //uppgift e)
    // method inputCharArray takes in an input from the user and saves it to a String array of length 3
    // method returns if input is empty or shorter than 3
    // method asks for new input och length 1 that will rewrite one of the existing array elements on chosen index

    public static void inputCharArray() {
            Scanner scan = new Scanner(System.in);
            char[] charArrayInput;
            int length;
            int counter=0;  //sets counter to 0

            System.out.println("Add input for new array: ");
            String input = scan. nextLine();

            length = input.length();

            if (length >= 3) { // if input is longer or == 3
                String newChar;
                String[] sendString={};
                charArrayInput = input.substring(0, 3).toCharArray(); // input > 3 is 'cut' to safe just 3 first chars
                System.out.println("Your input has been saved as: " + Arrays.toString(charArrayInput));

                do {
                    System.out.println("Write a new character: ");
                    newChar = scan.nextLine();                      //saves new input to newChar

                    if(newChar.length()>1 || newChar.isEmpty()){   // if input is longer than 1 or is empty
                        return;
                    }
                    else if (newChar.length()==1) {             // if input has desired length (1)
                        counter++;                              //counter increases with every do loop

                        sendString = inputStringArray(sendString, counter, newChar);   // assigns the result of a method inputStringArray to sendString. Important!
                        System.out.println("\nWhich position in the array? (0-2): ");
                        int position = scan.nextInt();
                        if(position==0 || position==1 || position ==2){  // if chosen position exists in array
                            addContent(charArrayInput, newChar, position);  // calls addContent method
                            System.out.println("uppgift f) \n" + Arrays.toString(sendString)); //prints var sendstring (with result of method see up)
                        }
                    }
                }
                while(newChar.length()>0); //do loop runs as long the new input isn't empty

            }
            else if(length < 3 || length==0){   //if first input is shorter than 3 or is empty
                return;
            }
        }

        // part of uppgift e) that creates new arrays with newChar input
        public static char[] addContent(char[] charArray, String newChar, int position) {
            char [] newArray = newChar.toCharArray();
            charArray[position]=newArray[0]; // assigns new input value to the chosen index in array


            System.out.println(Arrays.toString(charArray));
            return charArray;
        }

        //uppgift f)
        // method makes a new array of all newly input chars. Array.length grows with the number of elements it takes in
        public static String[] inputStringArray(String[] sendStringArray, int counter, String newChar) {
            String[] firstArray = sendStringArray;

            String[] secondArray = Arrays.copyOf(firstArray,counter); // creates a copy of the firstArray
            secondArray[counter-1] = newChar;

            sendStringArray = secondArray;

            System.out.println("Uppgift g) ");

            printAsString(sendStringArray, counter); //calls for method in uppgift g) printAsString
            return sendStringArray;

        }

        //uppgift g)
        public static String printAsString(String[] sendStringArray, int counter) {
            if (counter > 0) {
                System.out.print(sendStringArray[counter-1]);
                printAsString(sendStringArray, counter - 1);
            }
            return Arrays.toString(sendStringArray);

        }
    }
