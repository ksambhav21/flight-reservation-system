package org.ideyalabs.seats.dto;

import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ideyalabs.flights.entity.Flight;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatResponseDto {
    private Long seatId;
    private Set<Flight> flights;
}
