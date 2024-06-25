package com.example.bus_ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BusServices {

    private final BusRepository busRepository;

    @Autowired
    public BusServices(BusRepository busRepository) {
        this.busRepository = busRepository;
    }

    public List<Bus> getBuses() {
        return busRepository.findAll();
    }

    public void addNewBus(Bus bus) {
        busRepository.save(bus);
    }

    public List<Bus> getBusesBySourceDestinationAndDate(String source, String destination, LocalDate date) {
        return busRepository.findBySourceAndDestinationAndDate(source, destination, date);
    }

    public void deleteBus(Integer busId) {
        boolean exists = busRepository.existsById(busId);
        if (!exists) {
            throw new IllegalStateException("bus with id " + busId + " does not exist");
        }
        busRepository.deleteById(busId);
    }

    public void updateBus(Integer busId, String source, String destination) {
        Bus bus = busRepository.findById(busId)
                .orElseThrow(() -> new IllegalStateException("bus with id " + busId + " does not exist"));

        if (source != null && source.length() > 0 && !bus.getSource().equals(source)) {
            bus.setSource(source);
        }

        if (destination != null && destination.length() > 0 && !bus.getDestination().equals(destination)) {
            bus.setDestination(destination);
        }

        busRepository.save(bus);
    }

    public String bookSeat(int busId, List<String> seatNumbers) {
        // Implement seat booking logic here
        // This is a placeholder implementation
        return "Seats " + seatNumbers + " booked for bus " + busId;
    }

    public List<String> getAvailableSeats(int busId) {
        // Implement logic to get available seats
        // This is a placeholder implementation
        return List.of("Seat1", "Seat2", "Seat3");
    }
}