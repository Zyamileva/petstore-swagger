package commands;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CommandLineReader {

    private static final Scanner scanner = new Scanner(System.in);

    public static long readLong(String commandMessage) {
        long commandNumber = Long.MIN_VALUE;
        while (commandNumber == Long.MIN_VALUE) {
            System.out.println(commandMessage);
            try {
                commandNumber = scanner.nextLong();
                scanner.nextLine();
            } catch (InputMismatchException ex) {
                System.err.println("Input invalid. Only numbers allowed.");
                scanner.next();
            }
        }
        return commandNumber;
    }

    public static int readInt(String commandMessage) {
        int commandNumber = Integer.MIN_VALUE;
        while (commandNumber == Integer.MIN_VALUE) {
            System.out.println(commandMessage);
            try {
                commandNumber = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException ex) {
                System.err.println("Input invalid. Only numbers allowed.");
                scanner.next();
            }
        }
        return commandNumber;
    }
    public static String readLine(String commandMessage) {
        System.out.println(commandMessage);
        return scanner.nextLine();
    }
}