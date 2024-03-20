package org.ideyalabs.flights.dto;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightRequestDto {
    private String flightName;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private Integer capacity;
    private Integer availableSeats;
    private String source;
    private String destination;
}
