package org.example.sorting_app;

import java.util.Arrays;


/**
 * SortingApp is a simple application for sorting integers passed as command-line arguments.
 * <p>
 * The application accepts up to 10 numbers, sorts them in ascending order,
 * and prints the sorted result.
 * <p>
 * If the input arguments are invalid (e.g., more than 10 numbers, less than 5 numbers or non-integer values),
 * the application outputs an error message.
 * </p>
 *
 * @author Zakhar Sirko
 * @version 1.0
 */
public class SortingApp {
    /**
     * The main method, which serves as the entry point of the application.
     *
     * @param args an array of command-line arguments.
     */
    public static void main(String[] args) {
     sort(args);
    }

    /**
     * Sorts integers passed as command-line arguments and prints them in ascending order.
     * <ul>
     * <li>If no numbers are provided, a message is displayed asking for input.</li>
     * <li>If less than 4 numbers are entered, an error message is displayed.</li>
     * <li>If more than 10 numbers are entered, an error message is displayed.</li>
     * <li>If any input is invalid (non-integer), an error message is displayed.</li>
     * </ul>
     *
     * @param args an array of strings containing the numbers to be sorted.
     */
    public static void sort(String[] args){
        if (args.length == 0) {
            System.out.println("Please enter up to 10 and minimum 4 integer numbers");
            return;
        }
        if (args.length < 4) {
            System.out.println("Please enter up to 10 and minimum 4 integer numbers");
            return;
        }

        if (args.length > 10) {
            System.out.println("Entered more than 10 integer numbers");
            return;
        }

        try {
            int[] numbers = Arrays.stream(args)
                    .mapToInt(Integer::parseInt)
                    .toArray();

            Arrays.sort(numbers);

            System.out.println("Sorted number: " + Arrays.toString(numbers));
        } catch (NumberFormatException e) {
            System.out.println("Error: all number must be integer");
        }
    }

}
