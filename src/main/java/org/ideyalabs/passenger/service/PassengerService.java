package org.ideyalabs.passenger.service;

import org.ideyalabs.passenger.dto.PassengerRequestDto;
import org.ideyalabs.passenger.dto.PassengerResponseDto;

import java.util.List;

public interface PassengerService {
    String deletePassenger(Long id);

    PassengerResponseDto updatePassenger(Long id, PassengerRequestDto passenger);

    PassengerResponseDto createPassenger(PassengerRequestDto passenger);

    PassengerResponseDto getPassengerById(Long id);

    List<PassengerResponseDto> getAllPassengers();
}
