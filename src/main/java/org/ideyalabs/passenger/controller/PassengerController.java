package org.ideyalabs.passenger.controller;

import org.ideyalabs.passenger.dto.PassengerRequestDto;
import org.ideyalabs.passenger.dto.PassengerResponseDto;
import org.ideyalabs.passenger.service.PassengerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/passengers-api/v1")
public class PassengerController {

    private final PassengerService passengerService;
    private static final Logger logger = LoggerFactory.getLogger(PassengerController.class);

    @Autowired
    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @GetMapping
    public ResponseEntity<?> getAllPassengers() {
        logger.info("get all passengers endpoint hit");
        List<PassengerResponseDto> passengers = passengerService.getAllPassengers();
        return new ResponseEntity<>(passengers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPassengerById(@PathVariable Long id) {
        logger.info("get passenger by id endpoint hit");
        PassengerResponseDto passenger = passengerService.getPassengerById(id);
        return new ResponseEntity<>(passenger, HttpStatus.OK);
    }

//    @PostMapping
//    public ResponseEntity<?> createPassenger(@RequestBody PassengerRequestDto passenger) {
//        PassengerResponseDto createdPassenger = passengerService.createPassenger(passenger);
//        return new ResponseEntity<>(createdPassenger, HttpStatus.CREATED);
//    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePassenger(@PathVariable Long id, @RequestBody PassengerRequestDto passenger) {
        logger.info("update passengers endpoint hit");
        PassengerResponseDto updatedPassenger = passengerService.updatePassenger(id, passenger);
        return new ResponseEntity<>(updatedPassenger, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePassenger(@PathVariable Long id) {
        logger.info("delete passenger endpoint hit");
        return new ResponseEntity<>(passengerService.deletePassenger(id),HttpStatus.NO_CONTENT);
    }
}

