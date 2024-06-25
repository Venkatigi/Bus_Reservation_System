package com.example.bus_ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/busReg")
public class BusController {

    private final BusServices busServices;

    @Autowired
    public BusController(BusServices busServices) {
        this.busServices = busServices;
    }

    @GetMapping
    public List<Bus> getBuses() {
        return busServices.getBuses();
    }

    @GetMapping(path = "/search")
    public List<Bus> getBusesBySourceDestinationAndDate(
            @RequestParam("source") String source,
            @RequestParam("destination") String destination,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return busServices.getBusesBySourceDestinationAndDate(source, destination, date);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void registerNewBus(@RequestBody Bus bus) {
        busServices.addNewBus(bus);
    }

    @DeleteMapping(path = "{busId}")
    public void deleteBus(@PathVariable("busId") Integer busId) {
        busServices.deleteBus(busId);
    }

    @PutMapping(path = "{busId}")
    public void updateBus(
            @PathVariable("busId") Integer busId,
            @RequestParam(required = false) String source,
            @RequestParam(required = false) String destination) {
        busServices.updateBus(busId, source, destination);
    }

    @PostMapping("/{busId}/book")
    public String bookSeat(
            @PathVariable int busId,
            @RequestBody List<String> seatNumbers) {
        try {
            return busServices.bookSeat(busId, seatNumbers);
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    @GetMapping("/{busId}/available-seats")
    public List<String> getAvailableSeats(@PathVariable int busId) {
        return busServices.getAvailableSeats(busId);
    }
}