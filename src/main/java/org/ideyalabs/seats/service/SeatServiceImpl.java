package org.ideyalabs.seats.service;

import org.ideyalabs.exception.IdNotFoundException;
import org.ideyalabs.flights.entity.Flight;
import org.ideyalabs.flights.service.FlightService;
import org.ideyalabs.seatassignment.entity.SeatAssignment;
import org.ideyalabs.seatassignment.repository.SeatAssignmentRepository;
import org.ideyalabs.seats.entity.Seat;
import org.ideyalabs.seats.repository.SeatRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class SeatServiceImpl implements SeatService {

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private SeatAssignmentRepository seatAssignmentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FlightService flightService;

    @Override
    public Seat createSeat(Seat seat,Integer flightId) {
        Flight flight = modelMapper.map(flightService.getFlightById(flightId), Flight.class);

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

//    @Override
//    public Seat updateSeat(Long id, Seat seat) {
//        if (seatRepository.existsById(id)) {
//            seat.setSeatId(id);
//            return seatRepository.save(seat);
//        }
//        return null;
//    }

    @Override
    public void deleteSeat(Long id) {
        if (seatRepository.existsById(id)) {
            seatRepository.deleteById(id);
        }
    }
}

