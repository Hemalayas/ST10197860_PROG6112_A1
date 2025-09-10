/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.tv;

import java.util.Scanner;

public class Series {
    private final Scanner in;
    private final SeriesStore store;

    public Series(Scanner in, SeriesStore store) {
        this.in = in;
        this.store = store;
    }

    // 1.2 + 1.3: capture + validation exactly as per brief
    public void CaptureSeries() {
        System.out.println("\nCAPTURE A NEW SERIES");
        System.out.println("====================");

        String id   = promptNonEmpty("Enter the series id: ");
        if (store.findById(id) != null) {
            System.out.println("Series with id already exists.");
            System.out.print("Enter (1) to launch menu or any other key to exit\n");
            return;
        }
        String name = promptNonEmpty("Enter the series name: ");
        int age     = promptAgeWithBriefRules("Enter the series age restriction: ");
        int eps     = promptInt("Enter the number of episodes for " + name + ": ", 1, Integer.MAX_VALUE);

        store.add(new SeriesModel(id, name, age, eps));
        System.out.println("Series processed successfully!!");
        System.out.print("Enter (1) to launch menu or any other key to exit\n");
    }

    // 1.4: search by SeriesId, show all fields with labels
    public void SearchSeries() {
        String id = promptNonEmpty("\nEnter the series id to search: ");
        SeriesModel s = store.findById(id);
        if (s == null) {
            System.out.println("\n--------------------------------");
            System.out.println("Series with Series Id: " + id + " was not found!");
            System.out.println("--------------------------------");
        } else {
            printOne(s);
        }
        System.out.print("Enter (1) to launch menu or any other key to exit\n");
    }

    // 1.6: update series name, age restriction, and number of episodes
    public void UpdateSeries() {
        String id = promptNonEmpty("\nEnter the series id to update: ");
        SeriesModel s = store.findById(id);
        if (s == null) { System.out.println("Series not found."); return; }

        String name = promptNonEmpty("Enter the series name: ");
        int age     = promptAgeWithBriefRules("Enter the age restriction: ");
        int eps     = promptInt("Enter the number of episodes: ", 1, Integer.MAX_VALUE);

        s.setTitle(name);
        s.setAgeRestriction(age);
        s.setEpisodes(eps);

        System.out.print("Enter (1) to launch menu or any other key to exit\n");
    }

    // 1.7: delete with confirmation "Yes (y) to delete."
    public void DeleteSeries() {
        String id = promptNonEmpty("\nEnter the series id to delete: ");
        System.out.print("Are you sure you want to delete series " + id + " from the system? Yes (y) to delete.\n");
        String confirm = in.nextLine().trim();
        if (confirm.equalsIgnoreCase("y")) {
            boolean ok = store.deleteById(id);
            if (ok) {
                System.out.println("\n--------------------------------");
                System.out.println("Series with Series Id: " + id + " WAS deleted!");
                System.out.println("--------------------------------");
            } else {
                System.out.println("Series not found.");
            }
        } else {
            System.out.println("Delete cancelled.");
        }
        System.out.print("Enter (1) to launch menu or any other key to exit\n");
    }

    // 1.8: print report from memory collection
    public void SeriesReport() {
        System.out.println("\n================================");
        if (store.all().isEmpty()) {
            System.out.println("No series captured.");
        } else {
            int n = 1;
            for (SeriesModel s : store.all()) {
                System.out.println("Series " + n++);
                System.out.println("--------------------------------");
                printLabeled(s);
                System.out.println("--------------------------------");
            }
        }
        System.out.print("Enter (1) to launch menu or any other key to exit\n");
    }

    public void ExitSeriesApplication() {
        System.out.println("Goodbye.");
    }

    // ---------- helpers ----------
    private String promptNonEmpty(String msg) {
        while (true) {
            System.out.print(msg);
            String s = in.nextLine().trim();
            if (!s.isEmpty()) return s;
            System.out.println("Value required.");
        }
    }

    private int promptInt(String msg, int min, int max) {
        while (true) {
            System.out.print(msg);
            String raw = in.nextLine().trim();
            try {
                int v = Integer.parseInt(raw);
                if (v < min || v > max) throw new NumberFormatException();
                return v;
            } catch (Exception e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

   
    private int promptAgeWithBriefRules(String msg) {
        while (true) {
            System.out.print(msg);
            String raw = in.nextLine().trim();
            int val;
            try {
                val = Integer.parseInt(raw);
            } catch (Exception e) {
                System.out.println("You have entered a incorrect series age!!!");
                System.out.print("Please re-enter the series age >> ");
                continue;
            }
            if (val < 2 || val > 18) {
                System.out.println("You have entered a incorrect series age!!!");
                System.out.print("Please re-enter the series age >> ");
                continue;
            }
            return val;
        }
    }

    private void printOne(SeriesModel s) {
        System.out.println("\n--------------------------------");
        printLabeled(s);
        System.out.println("--------------------------------");
    }

    private void printLabeled(SeriesModel s) {
        System.out.println("SERIES ID: " + s.getId());
        System.out.println("SERIES NAME: " + s.getTitle());
        System.out.println("SERIES AGE RESTRICTION: " + s.getAgeRestriction());
        System.out.println("NUMBER OF EPISODES: " + s.getEpisodes());
    }
}
