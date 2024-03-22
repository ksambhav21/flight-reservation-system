package org.ideyalabs.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ideyalabs.flights.entity.Flight;
import org.ideyalabs.passenger.entity.Passenger;
import org.ideyalabs.seatassignment.dto.SeatAssignmentResponseDto;
import org.ideyalabs.seatassignment.entity.SeatAssignment;
import org.ideyalabs.seatassignment.repository.SeatAssignmentRepository;
import org.ideyalabs.seats.entity.Seat;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationResponseDto {
    private Long reservationId;

    private Passenger passenger;

    private SeatAssignmentResponseDto seatAssignment;

    private LocalDateTime reservationTime;
}
