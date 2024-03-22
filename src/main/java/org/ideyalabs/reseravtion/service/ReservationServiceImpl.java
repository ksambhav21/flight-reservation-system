package org.ideyalabs.reseravtion.service;

import org.ideyalabs.exception.IdNotFoundException;
import org.ideyalabs.flights.dto.FlightRequestDto;
import org.ideyalabs.flights.entity.Flight;
import org.ideyalabs.flights.service.FlightService;
import org.ideyalabs.passenger.dto.PassengerResponseDto;
import org.ideyalabs.passenger.entity.Passenger;
import org.ideyalabs.passenger.service.PassengerService;
import org.ideyalabs.reseravtion.dto.ReservationRequestDto;
import org.ideyalabs.reseravtion.dto.ReservationResponseDto;
import org.ideyalabs.reseravtion.entity.Reservation;
import org.ideyalabs.reseravtion.repository.ReservationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService{
    @Autowired
    private PassengerService passengerService;
    @Autowired
    private FlightService flightService;

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ReservationResponseDto> getAllReservationsByPassenger(Integer id) {
        return reservationRepository.findByPassenger_PassengerId(id).stream()
                .map(reservation -> modelMapper.map(reservation, ReservationResponseDto.class))
                .toList();
    }

    @Override
    public List<ReservationResponseDto> getAllReservationsByFlight(Integer id) {
        List<Reservation> reservations = reservationRepository.findByFlight_FlightId(    id);
        // Map reservations to ReservationResponseDto
        return reservations.stream()
                .map(reservation -> modelMapper.map(reservation, ReservationResponseDto.class))
                .toList();
    }

    @Override
    public ReservationResponseDto getReservationById(Integer id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Reservation with ID " + id + " not found"));
        return modelMapper.map(reservation, ReservationResponseDto.class);
    }

    @Override
    public ReservationResponseDto bookReservationByPassenger(Integer passengerId, Integer flightId, LocalDateTime reservationTime){
        Passenger passenger = modelMapper.map(passengerService.getPassengerById(Long.valueOf(passengerId)), Passenger.class);
        Flight flight = modelMapper.map(flightService.getFlightById(flightId), Flight.class);

        if(passenger == null) {
            throw new IdNotFoundException("Passenger not found");
        }
        else if(flight == null) {
            throw new IdNotFoundException("Flight not found");
        }

        if(flight.getAvailableSeats() <= 0) {
            throw new IdNotFoundException("No available seats for flight with ID " + flightId);
        }

        flight.setAvailableSeats(flight.getAvailableSeats() - 1);
        flightService.updateFlight(flightId, modelMapper.map(flight, FlightRequestDto.class));

        Reservation reservation = Reservation.builder()
                .passenger(passenger)
                .flight(flight)
                .reservationTime(reservationTime)
                .build();
        reservationRepository.save(reservation);
        return modelMapper.map(reservation, ReservationResponseDto.class);
    }

    @Override
    public ReservationResponseDto updateReservationById(Integer id, ReservationRequestDto reservationRequestDto) {
        Reservation existingReservation = reservationRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Reservation with ID " + id + " not found"));
        modelMapper.map(reservationRequestDto, existingReservation);
        reservationRepository.save(existingReservation);
        return modelMapper.map(existingReservation, ReservationResponseDto.class);
    }

    @Override
    public String deleteReservationById(Integer id) {
        Optional<Reservation> reservation = reservationRepository.findById(id);
        if (reservation.isEmpty()) {
            throw new IdNotFoundException("Reservation with ID " + id + " not found");
        }
        reservationRepository.delete(reservation.get());
        return "Reservation with ID " + id + " deleted successfully";
    }
}
