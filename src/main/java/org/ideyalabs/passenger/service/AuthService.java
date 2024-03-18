package org.ideyalabs.passenger.service;

import org.ideyalabs.passenger.dto.JwtRequest;
import org.ideyalabs.passenger.dto.JwtResponse;
import org.ideyalabs.passenger.dto.PassengerRequestDto;
import org.ideyalabs.passenger.dto.PassengerResponseDto;
import org.ideyalabs.passenger.entity.Passenger;
import org.ideyalabs.passenger.jwt.JwtHelper;
import org.ideyalabs.passenger.repository.PassengerRepo;
import org.ideyalabs.passenger.security.PassengerDetailsServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService {


    @Autowired
    private ModelMapper modelMapper;


    @Autowired
    private PassengerRepo passengerRepo;

    @Autowired
    private PassengerService passengerService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private PassengerDetailsServiceImpl passengerDetailsService;

    @Autowired
    private AuthenticationManager manager;


    @Autowired
    private JwtHelper helper;

    /*
     * validating the login input parameters and creating token by setting authentication
     */

    private void doAuthenticate(String email, String password) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);
        }
        catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid Username or Password Provided!!");
        }
    }

    public JwtResponse signin(JwtRequest request) {
        doAuthenticate(request.getEmail(), request.getPassword());

        UserDetails passengerDetails = passengerDetailsService.loadUserByUsername(request.getEmail());
        String token = this.helper.generateToken(passengerDetails);
        String username = passengerDetails.getUsername();
        return  JwtResponse.builder()
                .jwtToken(token)
                .email(username).build();

    }

    public PassengerResponseDto createUser(PassengerRequestDto passengerRequestDto)
    {
        Passenger passenger = modelMapper.map(passengerRequestDto,Passenger.class);
        passenger.setRole("USER");
        passenger.setPassword(passwordEncoder.encode(passenger.getPassword()));
        return this.modelMapper.map(passengerRepo.save(passenger),PassengerResponseDto.class);
    }


}
