package org.ideyalabs.reseravtion.controller;

import org.ideyalabs.reseravtion.dto.ReservationRequestDto;
import org.ideyalabs.reseravtion.dto.ReservationResponseDto;
import org.ideyalabs.reseravtion.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservation-api/v1")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @GetMapping("/reservations/passenger/{id}")
    public List<ReservationResponseDto> getAllReservationsByPassenger(@PathVariable("id") Integer passengerId){
        return reservationService.getAllReservationsByPassenger(passengerId);
    }

    @GetMapping("/reservations/flight/{id}")
    public List<ReservationResponseDto> getAllReservationsByFlight(@PathVariable("id") Integer flightId){
        return reservationService.getAllReservationsByFlight(flightId);
    }

    @GetMapping("/reservations/{id}")
    public ReservationResponseDto getReservationById(@PathVariable("id") Integer id){
        return reservationService.getReservationById(id);
    }

    @PostMapping("/reservations/passenger/{passengerId}/flight/{flightId}")
    public ReservationResponseDto bookReservationByPassenger(@PathVariable("passengerId") Integer passengerId, @PathVariable("flightId") Integer flightId, @RequestBody ReservationRequestDto reservationRequestDto){
        return reservationService.bookReservationByPassenger(passengerId, flightId, reservationRequestDto.getReservationTime());
    }

    @PutMapping("/reservations/{id}")
    public ReservationResponseDto updateReservationById(@PathVariable("id") Integer id, @RequestBody ReservationRequestDto reservationRequestDto){
        return reservationService.updateReservationById(id, reservationRequestDto);
    }

    @DeleteMapping("/reservations/{id}")
    public String deleteReservationById(@PathVariable("id") Integer id){
        return reservationService.deleteReservationById(id);
    }
}
