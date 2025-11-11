package com.napier.sem.model;

/**
 * Represents a report entry showing information about a specific language.
 * <p>
 * Each {@code LanguageReport} object contains the language name,
 * number of speakers, and its percentage of the world population.
 * <br>
 * This model is populated by {@link com.napier.sem.dao.LanguageReportDAO}
 * and displayed in formatted reports by {@link com.napier.sem.helper.OutputHelper}.
 * </p>
 */
public class LanguageReport {
    /** The name of the language. */
    private String language;

    /** The number of speakers. */
    private int speakers;

    /** The percentage of the world's population who speak the language. */
    private double worldPercentage;
    /**
     * Constructs a new {@code LanguageReport} object.
     *
     * @param language         The name of the language.
     * @param speakers         The total number of speakers.
     * @param worldPercentage  The percentage of the world population who speak it.
     */
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
    /**
     * Returns a formatted string representation of the language report,
     * consistent with the table layout used in console reports.
     *
     * @return A formatted string with the language name, speakers, and world percentage.
     */
    @Override
    public String toString() {
        return String.format("%-20s %-20d %.2f%%", language, speakers, worldPercentage);
    }
}
