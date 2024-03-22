package org.ideyalabs.passenger.controller;

import jakarta.validation.Valid;
import org.ideyalabs.passenger.dto.JwtRequest;
import org.ideyalabs.passenger.dto.JwtResponse;
import org.ideyalabs.passenger.dto.PassengerRequestDto;
import org.ideyalabs.passenger.dto.PassengerResponseDto;
import org.ideyalabs.jwt.JwtHelper;
import org.ideyalabs.passenger.service.AuthService;
import org.ideyalabs.passenger.service.AuthServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth-api/v1")
public class AuthController {

    private final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private JwtHelper helper;

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> addUser(@Valid @RequestBody PassengerRequestDto passengerDto)
    {
        logger.info("sign up endpoint called");

        return new ResponseEntity<PassengerResponseDto>(authService.createUser(passengerDto), HttpStatus.OK);
    }


    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> login( @RequestBody JwtRequest request) {
        logger.info("login endpoint hit");
        return new ResponseEntity<>(authService.signin(request), HttpStatus.OK);
    }

}

