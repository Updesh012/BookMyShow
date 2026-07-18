package org.example;

import java.time.LocalDateTime;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // Setup
        List<Seat> seats = List.of(
                new Seat("A1", 1, 1, SeatType.REGULAR),
                new Seat("A2", 1, 2, SeatType.REGULAR),
                new Seat("A3", 1, 3, SeatType.PREMIUM),
                new Seat("B1", 2, 1, SeatType.PREMIUM),
                new Seat("B2", 2, 2, SeatType.VIP)
        );

        Screen screen1 = new Screen(1, seats);
        Theatre pvr = new Theatre("T1", "PVR Ambience", "Gurugram", List.of(screen1));
        Movie movie = new Movie("M1", "Pushpa 3", "Action", 165);

        Show show = new Show("S1", movie, screen1,
                LocalDateTime.of(2026, 7, 5, 18, 30));

        User updesh = new User("U1", "Updesh", "updesh@mail.com");

        // Booking flow
        BookingService service = BookingService.getInstance();

        // User selects seats A1, A2
        Booking booking = service.createBooking(
                updesh, show,
                List.of("A1", "A2"),
                new UpiPayment("updesh@paytm")
        );

    }
}
