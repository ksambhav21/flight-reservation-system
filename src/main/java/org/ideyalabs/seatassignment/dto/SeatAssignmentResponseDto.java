package org.ideyalabs.seatassignment.dto;

import jakarta.persistence.ManyToOne;
import org.ideyalabs.flights.dto.FlightResponseDto;
import org.ideyalabs.flights.entity.Flight;
import org.ideyalabs.seats.dto.SeatResponseDto;
import org.ideyalabs.seats.entity.Seat;

public class SeatAssignmentResponseDto {

    private Long seatAssignmentId;

    private FlightResponseDto flight;


    private SeatResponseDto seat;

    private Boolean booked;
}
