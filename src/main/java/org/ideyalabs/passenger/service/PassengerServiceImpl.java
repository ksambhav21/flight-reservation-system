package org.ideyalabs.passenger.service;

import org.ideyalabs.passenger.dto.PassengerRequestDto;
import org.ideyalabs.passenger.dto.PassengerResponseDto;
import org.ideyalabs.passenger.entity.Passenger;
import org.ideyalabs.exception.IdNotFoundException;
import org.ideyalabs.passenger.repository.PassengerRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PassengerServiceImpl implements PassengerService{


        private final PassengerRepo passengerRepository;


        private final ModelMapper modelMapper;

        @Autowired
        public PassengerServiceImpl(PassengerRepo passengerRepository,ModelMapper modelMapper) {
            this.passengerRepository = passengerRepository;
            this.modelMapper=modelMapper;
        }
        @Override
        public List<PassengerResponseDto> getAllPassengers() {

            List<Passenger> passengers = passengerRepository.findAll();
            return passengers.stream().map(p -> this.modelMapper.map(p,PassengerResponseDto.class)).collect(Collectors.toList());
        }


        @Override
public PassengerResponseDto getPassengerById(Long id) {
            Optional<Passenger> optionalPassenger = passengerRepository.findById(id);
            return this.modelMapper.map(optionalPassenger.orElseThrow(() -> new IdNotFoundException("no such passenger exists")),PassengerResponseDto.class);
        }

        @Override
        public PassengerResponseDto createPassenger(PassengerRequestDto passengerRequest) {
            Passenger passenger = this.modelMapper.map(passengerRequest,Passenger.class);
            return this.modelMapper.map(passengerRepository.save(passenger),PassengerResponseDto.class);
        }

        @Override
        public PassengerResponseDto updatePassenger(Long id, PassengerRequestDto passengerRequestDto) {
            Optional<Passenger> optionalPassenger = passengerRepository.findById(id);
        if (optionalPassenger.isEmpty()) {
            throw new IdNotFoundException("no such passenger exists");
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
            passengerRepository.deleteById(id);
            return "deleted successfully";
        }


}


