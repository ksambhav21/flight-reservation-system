package org.ideyalabs.flights.repository;

import org.ideyalabs.flights.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface FlightRepository extends JpaRepository<Flight, Integer>{

    List<Flight> findByArrivalTime(LocalDateTime arrivalTime);

    List<Flight> findByDepartureTime(LocalDateTime departureTime);

    List<Flight> findByDestination(String destination);

    List<Flight> findBySource(String source);


}
