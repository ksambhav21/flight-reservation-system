package org.ideyalabs.seat.controller;

import org.ideyalabs.seat.entity.Seat;
import org.ideyalabs.seat.service.SeatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/seats-api/v1")
public class SeatController {

    @Autowired
    private SeatService seatService;

    private static final Logger logger = LoggerFactory.getLogger(SeatController.class);

    @PostMapping("/{flightId}")
    public Seat createSeat(@RequestBody Seat seat,@PathVariable Integer flightId) {
        logger.info("create seat controller hit");
        return seatService.createSeat(seat,flightId);
    }

    @GetMapping("/{id}")
    public Seat getSeatById(@PathVariable Long id) {

        logger.info("get seat by id controller hit");
        return seatService.getSeatById(id);
    }

    @GetMapping
    public List<Seat> getAllSeats() {

        logger.info("get all seats controller hit");
        return seatService.getAllSeats();
    }

//    @PutMapping("/{id}")
//    public Seat updateSeat(@PathVariable Long id, @RequestBody Seat seat) {
//        return seatService.updateSeat(id, seat);
//    }

    @DeleteMapping("/{id}")
    public void deleteSeat(@PathVariable Long id) {

        logger.info("delete seat controller hit");
        seatService.deleteSeat(id);
    }
}

