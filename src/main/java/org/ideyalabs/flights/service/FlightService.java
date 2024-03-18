package org.ideyalabs.flights.service;

import org.ideyalabs.flights.dto.FlightRequestDto;
import org.ideyalabs.flights.dto.FlightResponseDto;
import org.ideyalabs.flights.entity.Flight;

import java.util.List;

public interface FlightService {
    List<FlightResponseDto> getAllFlights();
    FlightResponseDto getFlightById(Integer id);
    FlightResponseDto addFlight(FlightRequestDto flightRequestDto);
    FlightResponseDto updateFlight(Integer id, FlightRequestDto flightRequestDto);
    String deleteFlight(Integer id);
}
