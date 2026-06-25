package utils;

import java.util.Scanner;

public class InputHelper {

    public static int readInt(Scanner sc, String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(sc.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Invalid integer. Try again.");
            }
        }
    }

    public static double readDouble(Scanner sc, String message) {
        while (true) {
            try {
                System.out.print(message);
                return Double.parseDouble(sc.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Invalid number. Try again.");
            }
        }
    }

    public static String readString(Scanner sc, String message) {
        System.out.print(message);
        return sc.nextLine().trim();
    }
}
