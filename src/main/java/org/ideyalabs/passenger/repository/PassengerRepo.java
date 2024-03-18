package org.ideyalabs.passenger.repository;

import org.ideyalabs.passenger.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepo extends JpaRepository<Passenger,Long> {

}
