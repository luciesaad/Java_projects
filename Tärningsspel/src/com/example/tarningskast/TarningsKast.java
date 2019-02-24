package com.example.tarningskast;

import java.util.Arrays;
import java.util.Scanner;

public class TarningsKast {
    public static void main(String args[]) {

        Scanner scan = new Scanner(System.in);
        int antalSidor = 6; //kast med 6 sidor
        int[] summaSpelare;
        summaSpelare = new int[6];
        String answer; //för do-while loop

        int[] highScore;
        highScore = new int[3];
        int h;

        System.out.println("Welcome to the Swenglish tärningsspel!");

        System.out.print("Hur många spelare? (2-5):  ");
        int antalSpelare = scan.nextInt();

        System.out.print("Välj antal kast: ");
        int antalKast = scan.nextInt();
        System.out.println();

        //do-while loop som gör att man ska kunna köra nytt spel om man vill (ja/nej)
        do {

            int i, t;

            //yttre loop för spelare (t)
            for (t = 1; t <= antalSpelare; ++t) {
                System.out.println("Spelare " + t);

                //innre loop för kast (i)
                for (i = 1; i <= antalKast; ++i) {
                    int randomSpelare = (int) (1 + antalSidor * Math.random());
                    summaSpelare[t] += randomSpelare; //sparas summa av spelares alla kast till array
                    System.out.println("Tärningskast " + i + " Poäng: " + randomSpelare);
                }

                System.out.println("Sum för spelare " + t + " är:  " + summaSpelare[t]);
                System.out.println();
            }
            // If-satser att se vem som har vunnit
            if (summaSpelare[1] > summaSpelare[2] && summaSpelare[1] > summaSpelare[3] && summaSpelare[1] > summaSpelare[4] && summaSpelare[1] > summaSpelare[5]) {
                System.out.println("Spelare 1 har vunnit!");
            } else if (summaSpelare[2] > summaSpelare[1] && summaSpelare[2] > summaSpelare[3] && summaSpelare[2] > summaSpelare[4] && summaSpelare[2] > summaSpelare[5]) {
                System.out.println("Spelare 2 har vunnit!");
            } else if (summaSpelare[3] > summaSpelare[1] && summaSpelare[3] > summaSpelare[2] && summaSpelare[3] > summaSpelare[4] && summaSpelare[3] > summaSpelare[5]) {
                System.out.println("Spelare 3 har vunnit!");
            } else if (summaSpelare[4] > summaSpelare[1] && summaSpelare[4] > summaSpelare[2] && summaSpelare[4] > summaSpelare[3] && summaSpelare[4] > summaSpelare[5]) {
                System.out.println("Spelare 4 har vunnit!");
            } else if (summaSpelare[5] > summaSpelare[1] && summaSpelare[5] > summaSpelare[2] && summaSpelare[5] > summaSpelare[3] && summaSpelare[5] > summaSpelare[4]) {
                System.out.println("Spelare 5 har vunnit!");
            } else {
                System.out.println("Det är oavgjort. Spela igen!"); //"else" körs om resultat är lika
            }

            //compares values and decides which is the highest to be claimed the high score in a particular game
            int max, j;
            max = summaSpelare[0];
            for (j = 1; j < 6; j++) {
                if (summaSpelare[j] > max) max = summaSpelare[j];
            }
            System.out.println("The highscore in this game is: " + max);

            /*saves max values of each game into array highScore AND rewrites with new values if higher.
            Index [0] now always represents the lowest of all high scores, [2] holds the highest. */


            if (highScore[0] == 0 || max < highScore[2] && max > highScore[0] && max < highScore [1] || max > highScore [1] && max < highScore [2]) {
                highScore[0] = max;
            } else if (highScore[0] > 0 && highScore[1] == 0 || max < highScore[2] && max > highScore[0]) {
                highScore[0]=highScore [1];
                highScore[1] = max;
            } else if (highScore[0] > 0 && highScore[1] > 0 && max > highScore[0] && max > highScore[1] || max > highScore[2]) {
                highScore [0]=highScore[1];
                highScore [1]=highScore[2];
                highScore[2] = max;
            }
            // sorterar alla värden från minst upp
            for (h = 0; h < highScore.length; h++) {
                Arrays.sort(highScore);
            }

            //sets the summa scores (än så länge) back to zero
            summaSpelare[1] = 0;
            summaSpelare[2] = 0;
            summaSpelare[3] = 0;
            summaSpelare[4] = 0;
            summaSpelare[5] = 0;

            System.out.println();
            System.out.println("Spela igen? (ja/nej): "); //möjlighet att spela igen
            answer = scan.next();

        } //änd do loop
        while (answer.equals("ja"));
        //körs om answer.equals "nej"
        System.out.println("Bra spelat! Congrats to all winners and players with following high scores!");
        if (highScore[0] != 0) {
            System.out.print("3rd best high score: " + highScore[0] + "\n");
        }
        if (highScore[1] != 0) {
            System.out.print("2nd best high score: " + highScore[1] + "\n");
        }
        if (highScore[2] != 0) {
            System.out.print("1st best high score: " + highScore[2] + "\n");
        }
        System.out.println("Vi ses nästa gång!");
    }
}
