package tema06;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int price;
        double vat;

        Scanner myInput = new Scanner(System.in);
        System.out.print("Enter the price of a product: ");
        price = myInput.nextInt();

        vat = (double)19 / 100 * price;

        System.out.println("Price of the product with VAT: " +
                            calculatePrice(price, vat));
    }

    public static double calculatePrice(int basePrice, double vat){
        return basePrice + vat;
    }
}
