package org.ideyalabs.seat.service;

import org.ideyalabs.exception.IdNotFoundException;
import org.ideyalabs.flights.entity.Flight;
import org.ideyalabs.flights.repository.FlightRepository;
import org.ideyalabs.flights.service.FlightService;
import org.ideyalabs.seatassignment.entity.SeatAssignment;
import org.ideyalabs.seatassignment.repository.SeatAssignmentRepository;
import org.ideyalabs.seat.entity.Seat;
import org.ideyalabs.seat.repository.SeatRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SeatServiceImpl implements SeatService {


    private SeatRepository seatRepository;
    private SeatAssignmentRepository seatAssignmentRepository;
    private ModelMapper modelMapper;
    private FlightRepository flightRepository;

    private static final Logger logger = LoggerFactory.getLogger(SeatServiceImpl.class);

    @Autowired
    public SeatServiceImpl(SeatRepository seatRepository, SeatAssignmentRepository seatAssignmentRepository, ModelMapper modelMapper, FlightRepository flightRepository) {
        this.seatRepository = seatRepository;
        this.seatAssignmentRepository = seatAssignmentRepository;
        this.modelMapper = modelMapper;
        this.flightRepository = flightRepository;
    }

    @Override
    public Seat createSeat(Seat seat,Integer flightId) {
        logger.info("create seat service called");
        Flight flight = flightRepository.findById(flightId).orElseThrow(() -> new IdNotFoundException("no such flight exists in the database"));

        Seat savedSeat=seatRepository.save(seat);
        SeatAssignment seatAssignment = SeatAssignment.builder()
                .flight(flight)
                .seat(savedSeat)
                .booked(false)
                .build();
        seatAssignmentRepository.save(seatAssignment);

        return savedSeat;
    }

    @Override
    public Seat getSeatById(Long id) {
        Optional<Seat> seat = seatRepository.findById(id);
        return seat.orElse(null);
    }

    @Override
    public List<Seat> getAllSeats() {
        return seatRepository.findAll();
    }

    @Override
    public void deleteSeat(Long id) {
        if (seatRepository.existsById(id)) {
            seatRepository.deleteById(id);
        }
    }
}

