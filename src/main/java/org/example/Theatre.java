package org.example;

import java.util.List;

public class Theatre {
    private String theatreId;
    private String name;
    private String address;
    private List<Screen> screens;

    public Theatre(String theatreId, String name, String address, List<Screen> screens) {
        this.theatreId = theatreId;
        this.name = name;
        this.address = address;
        this.screens = screens;
    }

    public String getTheatreId() {
        return theatreId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public List<Screen> getScreens() {
        return screens;
    }
}
