package org.ideyalabs.passenger.service;

import org.ideyalabs.passenger.dto.PassengerRequestDto;
import org.ideyalabs.passenger.dto.PassengerResponseDto;
import org.ideyalabs.passenger.entity.Passenger;
import org.ideyalabs.exception.IdNotFoundException;
import org.ideyalabs.passenger.repository.PassengerRepo;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PassengerServiceImpl implements PassengerService{


        private final PassengerRepo passengerRepository;

        private static final Logger logger = LoggerFactory.getLogger(PassengerServiceImpl.class);
        private final ModelMapper modelMapper;

        @Autowired
        public PassengerServiceImpl(PassengerRepo passengerRepository,ModelMapper modelMapper) {
            this.passengerRepository = passengerRepository;
            this.modelMapper=modelMapper;
        }
        @Override
        public List<PassengerResponseDto> getAllPassengers() {
            logger.info("get all passengers service called");
            List<Passenger> passengers = passengerRepository.findAll();
            return passengers.stream().map(p -> this.modelMapper.map(p,PassengerResponseDto.class)).collect(Collectors.toList());
        }


        @Override
        public PassengerResponseDto getPassengerById(Long id) {
            logger.info("get passenger by id service called");
            Optional<Passenger> optionalPassenger = passengerRepository.findByPassengerId(id);
            return this.modelMapper.map(optionalPassenger.orElseThrow(() -> new IdNotFoundException("no such passenger exists with given id: " + id)),PassengerResponseDto.class);
        }



        @Override
        public PassengerResponseDto updatePassenger(Long id, PassengerRequestDto passengerRequestDto) {
            logger.info("update passenger service called");
            Optional<Passenger> optionalPassenger = passengerRepository.findByPassengerId(id);
        if (optionalPassenger.isEmpty()) {
            throw new IdNotFoundException("no such passenger exists with given id: " + id);
        }

            Passenger existingPassenger = optionalPassenger.get();

            if (passengerRequestDto.getName() != null) {
                existingPassenger.setName(passengerRequestDto.getName());
            }
            if (passengerRequestDto.getMobileNumber() != null) {
                existingPassenger.setMobileNumber(passengerRequestDto.getMobileNumber());
            }
            if (passengerRequestDto.getEmail() != null) {
                existingPassenger.setEmail(passengerRequestDto.getEmail());
            }
            return this.modelMapper.map(passengerRepository.save(existingPassenger),PassengerResponseDto.class);
        }

        @Override
        public String deletePassenger(Long id) {
            logger.info("delete passenger service called");
            Optional<Passenger> optionalPassenger = passengerRepository.findByPassengerId(id);
            if (optionalPassenger.isEmpty()) {
                throw new IdNotFoundException("no such passenger exists with given id: " + id);
            }
            passengerRepository.delete(optionalPassenger.get());
            return "deleted successfully";
        }


}


