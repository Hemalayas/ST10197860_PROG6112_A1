/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package za.ac.pt.b;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GradeBookTest {

    private StudentStore store;
    private GradeBook gb;

    @Before
    public void setup() {
        store = new StudentStore();
        store.add(new Student("S1","Asha","BSc"));
        store.add(new Student("S2","Ben","BCom"));
        gb = new GradeBook(store, 3, 4); // 3 modules, 4 assessments each
    }

    @Test
    public void setMarkAndComputeAverages() {
        assertTrue(gb.setMark("S1", 1, 1, 80));
        assertTrue(gb.setMark("S1", 1, 2, 70));
        assertTrue(gb.setMark("S1", 1, 3, 90));
        assertTrue(gb.setMark("S1", 1, 4, 60));
        double m1 = gb.moduleAverage("S1", 1);
        assertEquals(75.0, m1, 0.001);

        // overall after only module 1 filled = same as m1
        assertEquals(75.0, gb.overallAverage("S1"), 0.001);
    }

    @Test
    public void rejectOutOfRangeMarks() {
        assertFalse(gb.setMark("S1", 1, 1, -3));
        assertFalse(gb.setMark("S1", 1, 1, 101));
    }

    @Test
    public void invalidStudentOrIndexReturnsFalse() {
        assertFalse(gb.setMark("Nope", 1, 1, 50));
        assertFalse(gb.setMark("S1", 4, 1, 50)); // module > 3
        assertFalse(gb.setMark("S1", 1, 5, 50)); // assessment > 4
    }
}
