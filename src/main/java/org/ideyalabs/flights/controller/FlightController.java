package org.ideyalabs.flights.controller;


import org.ideyalabs.flights.dto.FlightRequestDto;
import org.ideyalabs.flights.dto.FlightResponseDto;
import org.ideyalabs.flights.entity.Flight;
import org.ideyalabs.flights.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/flight-api/v1")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @GetMapping("/flights")
    public List<FlightResponseDto> getAllFlights() {
        return flightService.getAllFlights();
    }

    @GetMapping("/flight/{id}")
    public FlightResponseDto getFlightById(@PathVariable("id") Integer id) {
        return flightService.getFlightById(id);
    }

    @PostMapping("/flight")
    public FlightResponseDto addFlight(@RequestBody FlightRequestDto flight) {
        return flightService.addFlight(flight);
    }

    @PutMapping("/flight/{id}")
    public FlightResponseDto updateFlight(@PathVariable("id") Integer id, @RequestBody FlightRequestDto flight) {
        return flightService.updateFlight(id, flight);
    }

    @DeleteMapping("/flight/{id}")
    public String deleteFlight(@PathVariable("id") Integer id) {
        return flightService.deleteFlight(id);
    }
}
