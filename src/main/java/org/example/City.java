package org.example;

import java.util.List;

public class City {
    private String city;
    private List<Theatre> theatres;

    public City(String city, List<Theatre> theatres) {
        this.city = city;
        this.theatres = theatres;
    }

    public String getCity() {
        return city;
    }

    public List<Theatre> getTheatres() {
        return theatres;
    }
}
