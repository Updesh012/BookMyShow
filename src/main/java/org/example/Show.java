package org.example;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class Show {
    private String showId;
    private Movie movie;
    private Screen screen;
    private LocalDateTime startTime;
    private List<ShowSeat> showSeats;

    public Show(String showId, Movie movie, Screen screen, LocalDateTime startTime) {
        this.showId = showId;
        this.movie = movie;
        this.screen = screen;
        this.startTime = startTime;

        // Create ShowSeat for each physical seat in the screen
        this.showSeats = screen.getSeats().stream()
                .map(ShowSeat::new)
                .collect(Collectors.toList());
    }

    public List<ShowSeat> getAvailableSeats() {
        return showSeats.stream()
                .filter(ShowSeat::isAvailable)
                .collect(Collectors.toList());
    }

    public synchronized boolean lockSeats(List<String> seatIds, String userId) {
        // First verify ALL requested seats are available
        List<ShowSeat> toLock = showSeats.stream()
                                        .filter(ss -> seatIds.contains(ss.getSeat().getSeatId()))
                                        .toList();

        // If any seat is not available, fail the entire request
        boolean allAvailable = toLock.stream().allMatch(ShowSeat::isAvailable);
        if (!allAvailable) {
            return false;
        }

        toLock.forEach(ss -> ss.lock(userId));
        return true;
    }

    public void confirmSeats(List<String> seatIds) {
        showSeats.stream()
                .filter(ss -> seatIds.contains(ss.getSeat().getSeatId()))
                .forEach(ShowSeat::book);
    }

    public void releaseSeats(List<String> seatIds) {
        showSeats.stream()
                .filter(ss -> seatIds.contains(ss.getSeat().getSeatId()))
                .forEach(ShowSeat::release);
    }

    // Getters
    public Movie getMovie() { return movie; }
    public String getShowId() { return showId; }
    public LocalDateTime getStartTime() { return startTime; }

}
