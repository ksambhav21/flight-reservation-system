package org.ideyalabs.passenger.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassengerRequestDto {

    private String name;

    private String mobileNumber;

    private String email;
}
