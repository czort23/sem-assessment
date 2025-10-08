package com.napier.sem.model;

public class LanguageReport {
    private String language;
    private int speakers;
    private double worldPercentage;

    public LanguageReport(String language, int speakers, double worldPercentage) {
        this.language = language;
        this.speakers = speakers;
        this.worldPercentage = worldPercentage;
    }

    // Getters and setters
    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }
    public int getSpeakers() { return speakers; }
    public void setSpeakers(int speakers) { this.speakers = speakers; }
    public double getWorldPercentage() { return worldPercentage; }
    public void setWorldPercentage(double worldPercentage) { this.worldPercentage = worldPercentage; }

    @Override
    public String toString() {
        return String.format("%-20s %-20d %.2f%%", language, speakers, worldPercentage);
    }
}
