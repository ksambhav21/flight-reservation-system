package org.ideyalabs.reservation.controller;

import jakarta.validation.Valid;
import org.ideyalabs.flights.dto.FlightResponseDto;
import org.ideyalabs.flights.service.FlightService;
import org.ideyalabs.reservation.dto.ReservationRequestDto;
import org.ideyalabs.reservation.dto.ReservationResponseDto;
import org.ideyalabs.reservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/reservation-api/v1")
public class ReservationController {

    private ReservationService reservationService;


    private FlightService flightService;

    @Autowired
    public ReservationController(ReservationService reservationService, FlightService flightService){
        this.flightService=flightService;
        this.reservationService=reservationService;
    }

    @GetMapping("/reservations/passenger/{id}")
    public List<ReservationResponseDto> getAllReservationsByPassenger(@PathVariable("id") Integer passengerId){
        return reservationService.getAllReservationsByPassenger(passengerId);
    }

    @GetMapping("/search/flight/arrival/{arrivalTime}")
    public List<FlightResponseDto> getAllFlightsByArrivalTime(@PathVariable("arrivalTime") LocalDateTime arrivalTime){
        return flightService.getFlightByArrivalTime(arrivalTime);
    }

    @GetMapping("/search/flight/departure/{departureTime}")
    public List<FlightResponseDto> getAllFlightsByDepartureTime(@PathVariable("departureTime") LocalDateTime departureTime){
        return flightService.getFlightByDepartureTime(departureTime);
    }
    @GetMapping("/search/flight/source/{source}")
    public List<FlightResponseDto> getAllFlightsBySource(@PathVariable("source") String source){
        return flightService.getFlightBySource(source);
    }
    @GetMapping("/search/flight/destination/{destination}")
    public List<FlightResponseDto> getAllFlightsByDestination(@PathVariable("destination") String destination){
        return flightService.getFlightByDestination(destination);
    }

    @GetMapping("/reservations/flight/{id}")
    public List<ReservationResponseDto> getAllReservationsByFlight(@PathVariable("id") Integer flightId){
        return reservationService.getAllReservationsByFlight(flightId);
    }

    @GetMapping("/reservations/{id}")
    public ReservationResponseDto getReservationById(@PathVariable("id") Integer id){
        return reservationService.getReservationById(id);
    }

    @PostMapping("/reservations/passenger/{passengerId}/flight/{flightId}/")
    public ReservationResponseDto bookReservationByPassenger(@Valid @RequestParam("flightId") Integer flightId, @RequestParam("passengerId") Long passengerId, @RequestParam("seatId") Long seatId, @RequestBody ReservationRequestDto reservationRequestDto){
        return reservationService.bookReservationByPassenger(passengerId, flightId, seatId, reservationRequestDto.getReservationTime());
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
