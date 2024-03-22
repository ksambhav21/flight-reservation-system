package org.ideyalabs.seat.repository;

import org.ideyalabs.seat.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat,Long> {
}
