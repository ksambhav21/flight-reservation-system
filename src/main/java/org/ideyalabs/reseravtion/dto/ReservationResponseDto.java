package org.ideyalabs.reseravtion.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ideyalabs.flights.entity.Flight;
import org.ideyalabs.passenger.entity.Passenger;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationResponseDto {
    private Long reservationId;
    private Flight flight;
    private Passenger passenger;
    private LocalDateTime reservationTime;
}
