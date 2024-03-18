package org.ideyalabs.passenger.controller;

import org.ideyalabs.passenger.dto.JwtRequest;
import org.ideyalabs.passenger.dto.JwtResponse;
import org.ideyalabs.passenger.dto.PassengerRequestDto;
import org.ideyalabs.passenger.dto.PassengerResponseDto;
import org.ideyalabs.passenger.jwt.JwtHelper;
import org.ideyalabs.passenger.security.PassengerDetailsServiceImpl;
import org.ideyalabs.passenger.service.AuthService;
import org.ideyalabs.passenger.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {


    @Autowired
    private JwtHelper helper;

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> addUser(@RequestBody PassengerRequestDto passengerDto)
    {

        return new ResponseEntity<PassengerResponseDto>(authService.createUser(passengerDto), HttpStatus.OK);
    }


    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> login( @RequestBody JwtRequest request) {



        return new ResponseEntity<>(authService.signin(request), HttpStatus.OK);
    }

}

