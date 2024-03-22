package org.ideyalabs.flights.service;

import org.ideyalabs.exception.IdNotFoundException;
import org.ideyalabs.flights.dto.FlightRequestDto;
import org.ideyalabs.flights.dto.FlightResponseDto;
import org.ideyalabs.flights.entity.Flight;
import org.ideyalabs.flights.repository.FlightRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FlightServiceImpl implements FlightService{

    private final static Logger logger = LoggerFactory.getLogger(FlightServiceImpl.class);

    @Autowired
    private final FlightRepository flightRepository;

    @Autowired
    private final ModelMapper modelMapper;

    public FlightServiceImpl(FlightRepository flightRepository, ModelMapper modelMapper) {
        this.flightRepository = flightRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<FlightResponseDto> getAllFlights() {
        logger.info("Getting all flights service is called");
        return flightRepository.findAll().stream()
                .map(flight -> modelMapper.map(flight, FlightResponseDto.class))
                .toList();
    }

    @Override
    public FlightResponseDto getFlightById(Integer id) {
        logger.info("Getting flight by id service is called");
        return modelMapper.map(flightRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Flight with ID " + id + " not found")), FlightResponseDto.class);
    }

    @Override
    public FlightResponseDto addFlight(FlightRequestDto flightRequestDto) {
        logger.info("Adding flight service is called");
        Flight flight = modelMapper.map(flightRequestDto, Flight.class);
        flightRepository.save(flight);
        return modelMapper.map(flight, FlightResponseDto.class);
    }

    @Override
    public FlightResponseDto updateFlight(Integer id, FlightRequestDto flightRequestDto) {
        logger.info("Updating flight by id service is called");
        Flight existingFlight = flightRepository.findById(id)
                .orElseThrow(() ->{
                    logger.error("Flight with ID " + id + " not found");
                    return new IdNotFoundException("Flight with ID " + id + " not found");
                });


        modelMapper.map(flightRequestDto, existingFlight);
        flightRepository.save(existingFlight);
        return modelMapper.map(existingFlight, FlightResponseDto.class);
    }

    @Override
    public String deleteFlight(Integer id) {
        logger.info("Deleting flight by id service is called");
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Flight with ID " + id + " not found");
                    return new IdNotFoundException("Flight with ID " + id + " not found");
                });

        flightRepository.delete(flight);
        return "Flight with ID " + id + " has been deleted successfully";
    }

    @Override
    public List<FlightResponseDto> getFlightByArrivalTime(LocalDateTime arrivalTime) {
        return flightRepository.findByArrivalTime(arrivalTime).stream()
                .map(flight -> modelMapper.map(flight, FlightResponseDto.class))
                .toList();
    }

    @Override
    public List<FlightResponseDto> getFlightByDepartureTime(LocalDateTime departureTime) {
        return flightRepository.findByDepartureTime(departureTime).stream()
                .map(flight -> modelMapper.map(flight, FlightResponseDto.class))
                .toList();
    }

    @Override
    public List<FlightResponseDto> getFlightByDestination(String destination) {
        return flightRepository.findByDestination(destination).stream()
                .map(flight -> modelMapper.map(flight, FlightResponseDto.class))
                .toList();
    }

    @Override
    public List<FlightResponseDto> getFlightBySource(String source) {
        return flightRepository.findBySource(source).stream()
                .map(flight -> modelMapper.map(flight, FlightResponseDto.class))
                .toList();
    }
}