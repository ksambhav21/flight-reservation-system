package org.ideyalabs.passenger.service;

import org.ideyalabs.passenger.dto.JwtRequest;
import org.ideyalabs.passenger.dto.JwtResponse;
import org.ideyalabs.passenger.dto.PassengerRequestDto;
import org.ideyalabs.passenger.dto.PassengerResponseDto;
import org.ideyalabs.passenger.entity.Passenger;
import org.ideyalabs.jwt.JwtHelper;
import org.ideyalabs.passenger.repository.PassengerRepo;
import org.ideyalabs.security.PassengerDetailsServiceImpl;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {


    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);
    private ModelMapper modelMapper;
    private PassengerRepo passengerRepo;
    private PassengerService passengerService;
    private BCryptPasswordEncoder passwordEncoder;
    private PassengerDetailsServiceImpl passengerDetailsService;

    private AuthenticationManager manager;
    private JwtHelper helper;
    @Autowired
    public AuthServiceImpl(ModelMapper modelMapper, PassengerRepo passengerRepo, PassengerService passengerService, BCryptPasswordEncoder passwordEncoder, PassengerDetailsServiceImpl passengerDetailsService, AuthenticationManager manager, JwtHelper helper) {
        this.modelMapper = modelMapper;
        this.passengerRepo = passengerRepo;
        this.passengerService = passengerService;
        this.passwordEncoder = passwordEncoder;
        this.passengerDetailsService = passengerDetailsService;
        this.manager = manager;
        this.helper = helper;
    }

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
        logger.info("sign in service called");
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
        logger.info("create passenger service called");
        Passenger passenger = modelMapper.map(passengerRequestDto,Passenger.class);
        passenger.setRole("ROLE_USER");
        passenger.setPassword(passwordEncoder.encode(passenger.getPassword()));
        return this.modelMapper.map(passengerRepo.save(passenger),PassengerResponseDto.class);
    }


}
