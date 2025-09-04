/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package za.ac.tv;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class SeriesStoreTest {

    private SeriesStore store;

    @Before
    public void setUp() {
        store = new SeriesStore();
        store.add(new SeriesModel("101","Extreme Sports",12,10));
        store.add(new SeriesModel("102","Bargain Hunters",10,10));
    }

    // TestSearchSeries() - supply series id to searching method, correct data returned
    @Test
    public void TestSearchSeries() {
        SeriesModel s = store.findById("101");
        assertNotNull(s);
        assertEquals("Extreme Sports", s.getTitle());
        assertEquals(12, s.getAgeRestriction());
        assertEquals(10, s.getEpisodes());
    }

    // TestSearchSeries_SeriesNotFound()
    @Test
    public void TestSearchSeries_SeriesNotFound() {
        SeriesModel s = store.findById("999");
        assertNull(s);
    }

    // TestUpdateSeries() - update age restriction
    @Test
    public void TestUpdateSeries() {
        boolean ok = store.updateAgeById("101", 18);
        assertTrue(ok);
        assertEquals(18, store.findById("101").getAgeRestriction());
    }

    // TestDeleteSeries()
    @Test
    public void TestDeleteSeries() {
        boolean deleted = store.deleteById("102");
        assertTrue(deleted);
        assertNull(store.findById("102"));
    }

    // TestDeleteSeries_SeriesNotFound()
    @Test
    public void TestDeleteSeries_SeriesNotFound() {
        boolean deleted = store.deleteById("888");
        assertFalse(deleted);
    }

    // TestSeriesAgeRestriction_AgeValid()
    @Test
    public void TestSeriesAgeRestriction_AgeValid() {
        SeriesModel s = new SeriesModel("110","Sample",10,5);
        boolean valid = (s.getAgeRestriction() >= 2 && s.getAgeRestriction() <= 18);
        assertTrue(valid);
    }

    // TestSeriesAgeRestriction_SeriesAgeInvalid()
    @Test
    public void TestSeriesAgeRestriction_SeriesAgeInvalid() {
        SeriesModel s = new SeriesModel("111","BadAge",32,5);
        boolean valid = (s.getAgeRestriction() >= 2 && s.getAgeRestriction() <= 18);
        assertFalse(valid);
    }
}

