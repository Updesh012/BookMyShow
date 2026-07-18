package org.example;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class BookingService {
    private static volatile BookingService instance;
    private Map<String, Booking> bookings = new ConcurrentHashMap<>();

    private static final Map<SeatType, Double> PRICING = Map.of(
            SeatType.REGULAR, 200.0,
            SeatType.PREMIUM, 350.0,
            SeatType.VIP, 500.0
    );

    private BookingService() {};

    public static BookingService getInstance() {
        if (instance == null) {
            synchronized (BookingService.class) {
                if (instance == null) instance = new BookingService();
            }
        }

        return instance;
    }

    public Booking createBooking(User user, Show show, List<String> seatIds, PaymentStrategy paymentStrategy) {
        // Step 1: Lock seats (atomic, synchronized in Show)
        boolean locked = show.lockSeats(seatIds, user.getUserId());
        if (!locked) {
            throw new RuntimeException("Seats are not available");
        }

        // Step 2: Calculate total amount
        List<ShowSeat> selectedSeats = show.getAvailableSeats().stream()
                .filter(ss -> seatIds.contains(ss.getSeat().getSeatId()))
                .collect(Collectors.toList());

        // Recollect locked seats
        selectedSeats = seatIds.stream()
                .flatMap(id -> show.getAvailableSeats().stream()) // already locked
                .collect(Collectors.toList());

        double total = 0;
        for (String seatId : seatIds) {
            // Simplified — in real code, look up seat type from Show
            total += PRICING.getOrDefault(SeatType.REGULAR, 200.0);
        }

        // Step 3: Process payment (Strategy Pattern)
        boolean paid = paymentStrategy.pay(total);
        if (!paid) {
            show.releaseSeats(seatIds); // Release if payment fails!
            throw new RuntimeException("Payment failed!");
        }

        // Step 4: Confirm booking
        show.confirmSeats(seatIds);
        Booking booking = new Booking(user, show, List.of(), total);
        booking.confirm();
        bookings.put(booking.getBookingId(), booking);

        System.out.println("Booking confirmed: " + booking.getBookingId()
                + " | " + seatIds.size() + " seats | ₹" + total);
        return booking;
    }

    public void cancelBooking(String bookingId) {
        Booking booking = bookings.get(bookingId);
        if (booking == null) throw new IllegalArgumentException("Invalid booking");

        booking.cancel();
        // Release seats back to available
        List<String> seatIds = booking.getBookedSeats().stream()
                .map(ss -> ss.getSeat().getSeatId())
                .collect(Collectors.toList());
        booking.getShow().releaseSeats(seatIds);

        System.out.println("Booking " + bookingId + " cancelled. Refund: ₹"
                + booking.getTotalAmount());
    }

}
