package org.example;

import java.time.LocalDateTime;

public class ShowSeat {
    private Seat seat;
    private SeatStatus seatStatus;
    private String lockedByUserId;
    private LocalDateTime lockTime;

    public ShowSeat (Seat seat) {
        this.seat = seat;
        this.seatStatus = SeatStatus.AVAILABLE;
    }

    public boolean isAvailable() {
        return seatStatus == SeatStatus.AVAILABLE;
    }

    public void lock(String userId) {
        this.seatStatus = SeatStatus.LOCKED;
        this.lockedByUserId = userId;
        this.lockTime = LocalDateTime.now();
    }

    public void book() {
        this.seatStatus = SeatStatus.BOOKED;
    }

    public void release() {
        this.seatStatus = SeatStatus.AVAILABLE;
        this.lockedByUserId = null;
        this.lockTime = null;
    }

    public SeatStatus getSeatStatus() {
        return seatStatus;
    }

    public String getLockedByUserId() {
        return lockedByUserId;
    }

    public Seat getSeat() {
        return seat;
    }
}
