package org.ideyalabs.reservation.repository;

import org.ideyalabs.flights.entity.Flight;
import org.ideyalabs.reservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer>{
    List<Reservation> findByFlight(Flight flight);
    List<Reservation> findByPassenger_PassengerId(Integer id);
}
