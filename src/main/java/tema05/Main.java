package tema05;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int number;
        Scanner myInput = new Scanner(System.in);

        System.out.print("Enter a number: ");
        number = myInput.nextInt();

        if(number < 0) {
            System.out.println("Enter a positive number!");
        } else {
            System.out.println("Factorial of " + number + " = " + factorial(number));
        }
    }

    public static long factorial(int number){
        if(number > 1) {
            return number * factorial(number - 1);
        } else {
            return 1;
        }

    }
}
