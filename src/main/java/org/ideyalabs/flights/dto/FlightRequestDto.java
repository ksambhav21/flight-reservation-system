package org.ideyalabs.flights.dto;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightRequestDto {

    @NotEmpty(message = "cannot leave it empty")
    private String flightName;

    private LocalDateTime departureTime;

    private LocalDateTime arrivalTime;

    @NotEmpty
    private String source;
    @NotEmpty
    private String destination;
}
