/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package za.ac.tv;

import java.util.ArrayList;

public class SeriesStore {
    private final ArrayList<SeriesModel> list = new ArrayList<>();

    public void add(SeriesModel s) { list.add(s); }
    public ArrayList<SeriesModel> all() { return list; }
    public int count() { return list.size(); }

    // search by SeriesId (per brief 1.4)
    public SeriesModel findById(String id) {
        for (SeriesModel s : list) if (s.getId().equalsIgnoreCase(id)) return s;
        return null;
    }

    public boolean deleteById(String id) {
        SeriesModel s = findById(id);
        return s != null && list.remove(s);
    }

    public boolean updateAgeById(String id, int newAge) {
        SeriesModel s = findById(id);
        if (s == null) return false;
        s.setAgeRestriction(newAge);
        return true;
    }
}
