package org.ideyalabs.seatassignment.dto;

import org.ideyalabs.flights.dto.FlightResponseDto;
import org.ideyalabs.seat.dto.SeatResponseDto;

public class SeatAssignmentResponseDto {

    private Long seatAssignmentId;

    private FlightResponseDto flight;


    private SeatResponseDto seat;

    private Boolean booked;
}
