package org.ideyalabs.reservation.service;

import org.ideyalabs.exception.IdNotFoundException;
import org.ideyalabs.flights.dto.FlightRequestDto;
import org.ideyalabs.flights.entity.Flight;
import org.ideyalabs.flights.service.FlightService;
import org.ideyalabs.passenger.entity.Passenger;
import org.ideyalabs.passenger.service.PassengerService;
import org.ideyalabs.reservation.dto.ReservationRequestDto;
import org.ideyalabs.reservation.dto.ReservationResponseDto;
import org.ideyalabs.reservation.entity.Reservation;
import org.ideyalabs.reservation.repository.ReservationRepository;
import org.ideyalabs.seatassignment.entity.SeatAssignment;
import org.ideyalabs.seatassignment.repository.SeatAssignmentRepository;
import org.ideyalabs.seats.entity.Seat;
import org.ideyalabs.seats.service.SeatService;
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
    private SeatAssignmentRepository seatAssignmentRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private SeatService seatService;
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
        List<Reservation> reservations = reservationRepository.findByFlight_FlightId(id);
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
    public ReservationResponseDto bookReservationByPassenger(Long passengerId, Integer flightId, Long seatId, LocalDateTime reservationTime){
        Passenger passenger = modelMapper.map(passengerService.getPassengerById(Long.valueOf(passengerId)), Passenger.class);
        Flight flight = modelMapper.map(flightService.getFlightById(flightId), Flight.class);
        Seat seat = modelMapper.map(seatService.getSeatById(seatId), Seat.class);
        Optional<SeatAssignment> seatAssignment = seatAssignmentRepository.findBySeatAndFlight(seat,flight);
        if(passenger == null) {
            throw new IdNotFoundException("Passenger not found");
        }
        else if(flight == null) {
            throw new IdNotFoundException("Flight not found");
        }
        else if(seatAssignment.isEmpty()){
            throw new IdNotFoundException("No seat matches for the given flight");
        }

        else if(flight.getAvailableSeats() <= 0) {
            throw new IdNotFoundException("No available seats for flight with ID " + flightId);
        }

        else if (seatAssignment.get().getBooked()==true){
            throw new IllegalArgumentException("Seat Already Booked");
        }


        flight.setAvailableSeats(flight.getAvailableSeats() - 1);
        flightService.updateFlight(flightId, modelMapper.map(flight, FlightRequestDto.class));

        seatAssignment.get().setBooked(true);
        Reservation reservation = Reservation.builder()
                .passenger(passenger)
                .seatAssignment(seatAssignment.get())
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
        Reservation existingReservation = reservationRepository.findById(id)

                .orElseThrow(() -> new IdNotFoundException("Reservation with ID " + id + " not found"));
        SeatAssignment bookesSeatAssignment =existingReservation.getSeatAssignment();
                bookesSeatAssignment.setBooked(false);
                Flight flight = bookesSeatAssignment.getFlight();
        flight.setAvailableSeats(flight.getAvailableSeats() + 1);

        reservationRepository.delete(existingReservation);
        return "Reservation with ID " + id + " deleted successfully";
    }


}
