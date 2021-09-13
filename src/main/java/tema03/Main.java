package tema03;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner myInput = new Scanner(System.in);
        System.out.print("Introduceti numele: ");
        String insertName = myInput.nextLine();

        if (!insertName.isEmpty()) {
            System.out.println("Hello, " + insertName + "!");
        } else {
            System.out.println("Hello, stranger.");
        }

    }
}
