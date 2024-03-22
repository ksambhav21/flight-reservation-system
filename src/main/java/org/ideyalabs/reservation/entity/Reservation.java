package org.ideyalabs.reservation.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ideyalabs.passenger.entity.Passenger;
import org.ideyalabs.seatassignment.entity.SeatAssignment;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reservationId;

    @ManyToOne
    @JoinColumn(name = "passenger_id", nullable = false)
    private Passenger passenger;

    @OneToOne
    @JoinColumn(nullable = false)
    private SeatAssignment seatAssignment;

    private LocalDateTime reservationTime;
}