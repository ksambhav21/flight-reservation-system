package org.ideyalabs.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ideyalabs.flights.dto.FlightResponseDto;
import org.ideyalabs.passenger.entity.Passenger;
import org.ideyalabs.seatassignment.dto.SeatAssignmentResponseDto;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationResponseDto {
    private Long reservationId;

    private Passenger passenger;

    private FlightResponseDto flight;

    private SeatAssignmentResponseDto seatAssignment;

    private LocalDateTime reservationTime;
}
