/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package za.ac.pt.b;

import org.junit.Test;
import static org.junit.Assert.*;

public class StudentStoreTest {

    @Test
    public void addFindDeleteFlow() {
        StudentStore ss = new StudentStore();
        ss.add(new Student("S1","Kai","BSc"));
        ss.add(new Student("S2","Lerato","BCom"));

        assertEquals(2, ss.count());
        assertNotNull(ss.findById("S1"));
        assertTrue(ss.deleteById("S2"));
        assertNull(ss.findById("S2"));
        assertEquals(1, ss.count());
    }
}
