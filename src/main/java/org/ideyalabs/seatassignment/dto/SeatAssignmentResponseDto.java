package org.ideyalabs.seatassignment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ideyalabs.flights.dto.FlightResponseDto;
import org.ideyalabs.seat.dto.SeatResponseDto;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class SeatAssignmentResponseDto {

    private Long seatAssignmentId;

    private FlightResponseDto flight;


    private SeatResponseDto seat;

    private Boolean booked;
}
