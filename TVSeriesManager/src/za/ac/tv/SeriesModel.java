/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.tv;

// model for a TV series (fields per brief)
public class SeriesModel {
    private String id;
    private String title;
    private int ageRestriction;
    private int episodes;

    public SeriesModel(String id, String title, int ageRestriction, int episodes) {
        this.id = id;
        this.title = title;
        this.ageRestriction = ageRestriction;
        this.episodes = episodes;
    }

    // getters
    public String getId() { return id; }
    public String getTitle() { return title; }
    public int getAgeRestriction() { return ageRestriction; }
    public int getEpisodes() { return episodes; }

    // setters (used in UpdateSeries)
    public void setTitle(String title) { this.title = title; }
    public void setAgeRestriction(int ageRestriction) { this.ageRestriction = ageRestriction; }
    public void setEpisodes(int episodes) { this.episodes = episodes; }

    @Override
    public String toString() {
        return id + " | " + title + " | Age:" + ageRestriction + " | Episodes:" + episodes;
    }

    // optional CSV helpers (not required but handy)
    public String toCsv() {
        return id + "," + title + "," + ageRestriction + "," + episodes;
    }
    public static SeriesModel fromCsv(String line) {
        String[] p = line.split(",");
        if (p.length != 4) return null;
        try {
            return new SeriesModel(p[0], p[1], Integer.parseInt(p[2]), Integer.parseInt(p[3]));
        } catch (Exception e) {
            return null;
        }
    }
}

