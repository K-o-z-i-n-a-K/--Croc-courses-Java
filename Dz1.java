package com.company;

/**
 * First homework for CROC
 * @version 1
 * @author Kozina Kristina
 */

public class Dz1 {

    public static void main(String[] args) {
        System.out.print(" ");
        for (int i=1; i<101; i++){
            if (i % 3 != 0 && i % 5 != 0) {
                System.out.print(i);
            }
            else {
                if (i % 3 == 0) {
                    System.out.print("Fizz");
                }
                if (i % 5 == 0) {
                    System.out.println("Buzz");
                }
            }
            System.out.print(" ");
        }


    }
}
