/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.pt.b;

/**
 *
 * @author karen
 */
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Holds marks in a 2D int array (advanced arrays):
 * rows   -> students (aligned with StudentStore order)
 * cols   -> assessments per module flattened: modules * assessmentsPerModule
 *
 * Example: modules = 3, assessmentsPerModule = 4  =>  12 columns.
 */
public class GradeBook {
    private final StudentStore store;
    private final int modules;
    private final int assessmentsPerModule;
    private final int[][] marks; // [studentIndex][assessmentIndex] : 0..100

    public GradeBook(StudentStore store, int modules, int assessmentsPerModule) {
        this.store = store;
        this.modules = modules;
        this.assessmentsPerModule = assessmentsPerModule;
        // start empty; will grow as students are added
        this.marks = new int[100][modules * assessmentsPerModule]; // simple fixed capacity for first-year level
        // default -1 to indicate "no mark yet"
        for (int[] row : marks) Arrays.fill(row, -1);
    }

    private int idxOf(String studentId) {
        ArrayList<Student> all = store.all();
        for (int i = 0; i < all.size(); i++)
            if (all.get(i).getId().equalsIgnoreCase(studentId)) return i;
        return -1;
    }

    // set a mark (0..100) for a student, module, assessment (1-based indices in UI, converted to 0-based)
    public boolean setMark(String studentId, int moduleNo, int assessmentNo, int mark) {
        if (mark < 0 || mark > 100) return false;
        if (moduleNo < 1 || moduleNo > modules) return false;
        if (assessmentNo < 1 || assessmentNo > assessmentsPerModule) return false;

        int si = idxOf(studentId);
        if (si < 0) return false;

        int col = (moduleNo - 1) * assessmentsPerModule + (assessmentNo - 1);
        marks[si][col] = mark;
        return true;
    }

    // average for one module for one student
    public double moduleAverage(String studentId, int moduleNo) {
        int si = idxOf(studentId);
        if (si < 0 || moduleNo < 1 || moduleNo > modules) return -1;

        int start = (moduleNo - 1) * assessmentsPerModule;
        int end = start + assessmentsPerModule;

        int total = 0, count = 0;
        for (int c = start; c < end; c++) {
            if (marks[si][c] >= 0) { total += marks[si][c]; count++; }
        }
        return count == 0 ? -1 : (total * 1.0) / count;
    }

    // overall average for a student (all modules, all assessments)
    public double overallAverage(String studentId) {
        int si = idxOf(studentId);
        if (si < 0) return -1;

        int total = 0, count = 0;
        for (int c = 0; c < modules * assessmentsPerModule; c++) {
            if (marks[si][c] >= 0) { total += marks[si][c]; count++; }
        }
        return count == 0 ? -1 : (total * 1.0) / count;
    }

    // console report
    public void printReport() {
        System.out.println("\n=== STUDENT MARK REPORT ===");
        if (store.count() == 0) { System.out.println("No students."); return; }

        for (Student s : store.all()) {
            System.out.println("--------------------------------");
            System.out.println("STUDENT: " + s.getId() + " | " + s.getName() + " | " + s.getProgramme());
            for (int m = 1; m <= modules; m++) {
                double avg = moduleAverage(s.getId(), m);
                System.out.printf("Module %d Average: %s%n", m, avg < 0 ? "N/A" : String.format("%.2f", avg));
            }
            double overall = overallAverage(s.getId());
            System.out.printf("Overall Average: %s%n", overall < 0 ? "N/A" : String.format("%.2f", overall));
        }
        System.out.println("--------------------------------");
    }
}

