package org.ideyalabs.reservation.service;

import org.ideyalabs.reservation.dto.ReservationRequestDto;
import org.ideyalabs.reservation.dto.ReservationResponseDto;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationService {
    List<ReservationResponseDto> getAllReservationsByPassenger(Integer id);
    List<ReservationResponseDto> getAllReservationsByFlight(Integer id);
    ReservationResponseDto getReservationById(Integer id);
    ReservationResponseDto bookReservationByPassenger(Long passengerId, Integer flightId, Long seatId, LocalDateTime reservationTime);
    ReservationResponseDto updateReservationById(Integer id, ReservationRequestDto reservationRequestDto);
    String deleteReservationById(Integer id);
}
