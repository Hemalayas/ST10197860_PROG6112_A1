/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.tv;

import java.util.Scanner;

public class App {
    private static final Scanner in = new Scanner(System.in);
    private static final SeriesStore store = new SeriesStore();
    private static final Series actions = new Series(in, store);

    public static void main(String[] args) {
        while (true) {
            launchScreen();                    // 1.1
            String key = in.nextLine().trim();
            if (!"1".equals(key)) { actions.ExitSeriesApplication(); return; }

            while (true) {
                printMenu();
                String c = in.nextLine().trim();
                switch (c) {
                    case "1": actions.CaptureSeries(); break;          // 1.2, 1.3
                    case "2": actions.SearchSeries(); break;           // 1.4
                    case "3": actions.UpdateSeries(); break;           // 1.6
                    case "4": actions.DeleteSeries(); break;           // 1.7
                    case "5": actions.SeriesReport(); break;           // 1.8
                    case "6": actions.ExitSeriesApplication(); return; // 1.11
                    default: System.out.println("Invalid option.");
                }
            }
        }
    }

    private static void launchScreen() {
        System.out.println("\nLATEST SERIES – 2025");
        System.out.println("********************************");
        System.out.print("Enter (1) to launch menu or any other key to exit: ");
    }

    private static void printMenu() {
        System.out.println("\nPlease select one of the following menu items:");
        System.out.println("(1) Capture a new series.");
        System.out.println("(2) Search for a series.");
        System.out.println("(3) Update series age restriction");
        System.out.println("(4) Delete a series.");
        System.out.println("(5) Print series report - 2025");
        System.out.println("(6) Exit Application.");
        System.out.print("> ");
    }
}

