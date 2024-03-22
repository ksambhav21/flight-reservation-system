package org.ideyalabs.seat.service;

import org.ideyalabs.seat.entity.Seat;

import java.util.List;
public interface SeatService {
        Seat createSeat(Seat seat,Integer flightId);
        Seat getSeatById(Long id);
        List<Seat> getAllSeats();
//        Seat updateSeat(Long id, Seat seat);
        void deleteSeat(Long id);

}
