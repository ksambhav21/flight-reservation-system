package org.ideyalabs.passenger.repository;

import org.ideyalabs.passenger.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PassengerRepo extends JpaRepository<Passenger,Long> {

    Optional<Passenger> findByPassengerId(Long passengerId);
    Optional<Passenger> findByEmail(String email);

}
