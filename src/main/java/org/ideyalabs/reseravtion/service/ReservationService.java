package org.ideyalabs.reseravtion.service;

import org.ideyalabs.reseravtion.dto.ReservationRequestDto;
import org.ideyalabs.reseravtion.dto.ReservationResponseDto;
import org.ideyalabs.reseravtion.entity.Reservation;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationService {
    List<ReservationResponseDto> getAllReservationsByPassenger(Integer id);
    List<ReservationResponseDto> getAllReservationsByFlight(Integer id);
    ReservationResponseDto getReservationById(Integer id);
    ReservationResponseDto bookReservationByPassenger(Integer passengerId, Integer flightId, LocalDateTime reservationTime);
    ReservationResponseDto updateReservationById(Integer id, ReservationRequestDto reservationRequestDto);
    String deleteReservationById(Integer id);
}
