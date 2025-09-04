/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.pt.b;

/**
 *
 * @author karen
 */

// Child class (inherits from Person)
public class Student extends Person {
    // For report tags only (kept simple)
    private String programme;

    public Student(String id, String name, String programme) {
        super(id, name);
        this.programme = programme;
    }

    public String getProgramme() { return programme; }
    public void setProgramme(String programme) { this.programme = programme; }

    @Override
    public String toString() {
        return super.toString() + " | " + programme;
    }
}
