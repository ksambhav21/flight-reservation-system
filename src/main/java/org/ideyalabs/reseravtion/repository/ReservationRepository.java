package org.ideyalabs.reseravtion.repository;

import org.ideyalabs.reseravtion.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer>{
    List<Reservation> findByFlightId(Integer id);
    List<Reservation> findByPassengerId(Integer id);
}
