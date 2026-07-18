package org.example;

public class Seat {

    private String seatId;
    private int row;
    private int number;
    private SeatType seatType;

    public Seat(String seatId, int row, int number, SeatType seatType) {
        this.seatId = seatId;
        this.row = row;
        this.number = number;
        this.seatType = seatType;
    }

    public String getSeatId(){return seatId;};

    public int getRow() { return row;}

    public int getNumber() { return number; }

    public SeatType getSeatType() { return seatType; }
}
