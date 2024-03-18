package org.ideyalabs.flights.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class FlightResponseDto {
    private Integer flightId;
    private String flightName;
    private String flightNumber;
    private LocalDateTime DepartureTime;
    private LocalDateTime ArrivalTime;
    private Integer capacity;
    private Integer availableSeats;
    private String source;
    private String destination;
}
