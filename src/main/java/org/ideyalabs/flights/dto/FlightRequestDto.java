package org.ideyalabs.flights.dto;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class FlightRequestDto {
    private String flightName;
    private LocalDateTime DepartureTime;
    private LocalDateTime ArrivalTime;
    private Integer capacity;
    private Integer availableSeats;
    private String source;
    private String destination;
}
