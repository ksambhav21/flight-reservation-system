package org.ideyalabs.reseravtion.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.antlr.v4.runtime.misc.NotNull;
import org.ideyalabs.flights.entity.Flight;
import org.ideyalabs.passenger.entity.Passenger;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationRequestDto {
    @NotNull
    private Flight flight;
    @NonNull
    private Passenger passenger;
    @DateTimeFormat(fallbackPatterns = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime reservationTime;
}
