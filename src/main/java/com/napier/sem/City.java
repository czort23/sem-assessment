package com.napier.sem;

public class City {
    public int id;
    public String name;
    public String countryCode;
    public String district;
    public int population;

    @Override
    public String toString() {
        return "ID: " + id + "\nName: " + name + "\nCountry Code: " + countryCode
                + "\nDistrict: " + district + "\nPopulation: " + population;
    }
}
