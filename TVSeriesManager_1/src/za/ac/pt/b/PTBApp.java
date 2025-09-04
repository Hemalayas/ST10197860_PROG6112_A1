/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.pt.b;

/**
 *
 * @author karen
 */
import java.util.Scanner;

public class PTBApp {
    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        StudentStore store = new StudentStore();
        // 3 modules × 4 assessments each -> 12 columns in 2D array
        GradeBook gb = new GradeBook(store, 3, 4);

        while (true) {
            printMenu();
            String c = in.nextLine().trim();
            switch (c) {
                case "1": addStudent(store); break;
                case "2": enterMark(store, gb); break;
                case "3": gb.printReport(); break;
                case "4": System.out.println("Bye."); return;
                default : System.out.println("Invalid.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n=== PART 3: GradeBook ===");
        System.out.println("1) Add student");
        System.out.println("2) Enter mark");
        System.out.println("3) Print report");
        System.out.println("4) Exit");
        System.out.print("> ");
    }

    private static void addStudent(StudentStore store) {
        System.out.print("Student ID: ");
        String id = in.nextLine().trim();
        if (store.findById(id) != null) { System.out.println("ID exists."); return; }

        System.out.print("Name: ");
        String name = in.nextLine().trim();
        System.out.print("Programme: ");
        String prog = in.nextLine().trim();

        store.add(new Student(id, name, prog));
        System.out.println("Added.");
    }

    private static void enterMark(StudentStore store, GradeBook gb) {
        System.out.print("Student ID: ");
        String id = in.nextLine().trim();
        if (store.findById(id) == null) { System.out.println("Not found."); return; }

        int module = promptInt("Module number (1-3): ", 1, 3);
        int assess = promptInt("Assessment number (1-4): ", 1, 4);
        int mark   = promptInt("Mark (0-100): ", 0, 100);

        boolean ok = gb.setMark(id, module, assess, mark);
        System.out.println(ok ? "Saved." : "Failed.");
    }

    private static int promptInt(String msg, int min, int max) {
        while (true) {
            System.out.print(msg);
            try {
                int v = Integer.parseInt(new java.util.Scanner(System.in).nextLine().trim());
                if (v < min || v > max) throw new NumberFormatException();
                return v;
            } catch (Exception e) {
                System.out.println("Enter a number between " + min + " and " + max + ".");
            }
        }
    }
}

