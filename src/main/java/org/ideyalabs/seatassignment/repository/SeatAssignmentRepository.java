package org.ideyalabs.seatassignment.repository;

import org.ideyalabs.flights.entity.Flight;
import org.ideyalabs.seatassignment.entity.SeatAssignment;
import org.ideyalabs.seats.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SeatAssignmentRepository extends JpaRepository<SeatAssignment,Long> {
    Optional<SeatAssignment> findBySeatAndFlight(Seat seat, Flight flight);
}
