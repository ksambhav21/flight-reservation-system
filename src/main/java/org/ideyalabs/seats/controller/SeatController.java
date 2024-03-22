package org.ideyalabs.seats.controller;

import org.ideyalabs.seats.entity.Seat;
import org.ideyalabs.seats.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/seats-api/v1")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @PostMapping("/{flightId}")
    public Seat createSeat(@RequestBody Seat seat,@PathVariable Integer flightId) {
        return seatService.createSeat(seat,flightId);
    }

    @GetMapping("/{id}")
    public Seat getSeatById(@PathVariable Long id) {
        return seatService.getSeatById(id);
    }

    @GetMapping
    public List<Seat> getAllSeats() {
        return seatService.getAllSeats();
    }

//    @PutMapping("/{id}")
//    public Seat updateSeat(@PathVariable Long id, @RequestBody Seat seat) {
//        return seatService.updateSeat(id, seat);
//    }

    @DeleteMapping("/{id}")
    public void deleteSeat(@PathVariable Long id) {
        seatService.deleteSeat(id);
    }
}

