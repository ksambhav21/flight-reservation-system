package org.ideyalabs.seatassignment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ideyalabs.flights.entity.Flight;
import org.ideyalabs.seat.entity.Seat;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class SeatAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seatAssignmentId;

    @ManyToOne(cascade=CascadeType.ALL)
    private Flight flight;

    @ManyToOne(cascade = CascadeType.ALL)
    private Seat seat;

    private Boolean booked;
}
