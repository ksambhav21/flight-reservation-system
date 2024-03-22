package org.ideyalabs.seats.repository;

import org.ideyalabs.seats.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat,Long> {
}
