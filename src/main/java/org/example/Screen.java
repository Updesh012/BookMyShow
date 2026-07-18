package org.example;

import java.util.List;

public class Screen {
    private int screenNumber;
    private List<Seat> seats;

    public Screen(int screenNumber, List<Seat> seats) {
        this.screenNumber = screenNumber;
        this.seats = seats;
    }

    public int getScreenNumber() {
        return screenNumber;
    }

    public List<Seat> getSeats() {
        return seats;
    }
}
