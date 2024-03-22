package org.ideyalabs.flights.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ideyalabs.seats.entity.Seat;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer flightId;
    private String flightName;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private Integer capacity;
    private Integer availableSeats;
    private String source;
    private String destination;

}
