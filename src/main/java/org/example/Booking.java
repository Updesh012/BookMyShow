package org.example;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Booking {
    private String bookingId;
    private User user;
    private Show show;
    private List<ShowSeat> bookedSeats;
    private double totalAmount;
    private BookingStatus status;
    private LocalDateTime bookingTime;

    public Booking(User user, Show show, List<ShowSeat> seats, double amount) {
        this.bookingId = UUID.randomUUID().toString().substring(0, 8);
        this.user = user;
        this.show = show;
        this.bookedSeats = seats;
        this.totalAmount = amount;
        this.status = BookingStatus.PENDING;
        this.bookingTime = LocalDateTime.now();
    }

    public void confirm() { this.status = BookingStatus.CONFIRMED; }
    public void cancel() { this.status = BookingStatus.CANCELLED; }

    public String getBookingId() { return bookingId; }
    public BookingStatus getStatus() { return status; }
    public double getTotalAmount() { return totalAmount; }
    public Show getShow() { return show; }
    public List<ShowSeat> getBookedSeats() { return bookedSeats; }
}
