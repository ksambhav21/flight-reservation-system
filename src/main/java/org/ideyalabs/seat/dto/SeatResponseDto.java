package org.ideyalabs.seat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ideyalabs.flights.entity.Flight;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatResponseDto {
    private Long seatId;
    private Set<Flight> flights;
}
