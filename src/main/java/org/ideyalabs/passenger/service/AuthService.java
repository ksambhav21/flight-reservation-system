package org.ideyalabs.passenger.service;

import org.ideyalabs.passenger.dto.JwtRequest;
import org.ideyalabs.passenger.dto.JwtResponse;
import org.ideyalabs.passenger.dto.PassengerRequestDto;
import org.ideyalabs.passenger.dto.PassengerResponseDto;

public interface AuthService {

    public PassengerResponseDto createUser(PassengerRequestDto passengerRequestDto);
    public JwtResponse signin(JwtRequest request);
}
