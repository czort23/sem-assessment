package com.napier.sem.model;

/**
 * Represents a language report.
 */
public class LanguageReport {
    /** The name of the language. */
    private String language;

    /** The number of speakers. */
    private int speakers;

    /** The percentage of the world's population who speak the language. */
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
