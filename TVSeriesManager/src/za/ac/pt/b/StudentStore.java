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

public class StudentStore {
    private final ArrayList<Student> list = new ArrayList<>();

    public void add(Student s) { list.add(s); }
    public int count() { return list.size(); }
    public ArrayList<Student> all() { return list; }

    public Student findById(String id) {
        for (Student s : list) if (s.getId().equalsIgnoreCase(id)) return s;
        return null;
    }

    public boolean deleteById(String id) {
        Student s = findById(id);
        return s != null && list.remove(s);
    }
}
