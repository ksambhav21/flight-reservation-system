package org.ideyalabs.flights.repository;

import org.ideyalabs.flights.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Integer>{

}
