package org.ideyalabs.seatassignment.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ideyalabs.flights.entity.Flight;
import org.ideyalabs.seats.entity.Seat;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SeatAssignment {

    private Long seatAssignmentId;

    @ManyToOne(cascade=CascadeType.ALL)
    private Flight flight;

    @ManyToOne(cascade = CascadeType.ALL)
    private Seat seat;

    private Boolean booked;
}
