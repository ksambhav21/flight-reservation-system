package org.ideyalabs.flights.service;

import org.ideyalabs.exception.IdNotFoundException;
import org.ideyalabs.flights.dto.FlightRequestDto;
import org.ideyalabs.flights.dto.FlightResponseDto;
import org.ideyalabs.flights.entity.Flight;
import org.ideyalabs.flights.repository.FlightRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FlightServiceImpl implements FlightService{
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
        return flightRepository.findAll().stream()
                .map(flight -> modelMapper.map(flight, FlightResponseDto.class))
                .toList();
    }

    @Override
    public FlightResponseDto getFlightById(Integer id) {
        return modelMapper.map(flightRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Flight with ID " + id + " not found")), FlightResponseDto.class);
    }

    @Override
    public FlightResponseDto addFlight(FlightRequestDto flightRequestDto) {
        Flight flight = modelMapper.map(flightRequestDto, Flight.class);

        flightRepository.save(flight);
        return modelMapper.map(flight, FlightResponseDto.class);
    }

    @Override
    public FlightResponseDto updateFlight(Integer id, FlightRequestDto flightRequestDto) {
        Flight existingFlight = flightRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Flight with ID " + id + " not found"));

        modelMapper.map(flightRequestDto, existingFlight);
        flightRepository.save(existingFlight);
        return modelMapper.map(existingFlight, FlightResponseDto.class);
    }

    @Override
    public String deleteFlight(Integer id) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Flight with ID " + id + " not found"));

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