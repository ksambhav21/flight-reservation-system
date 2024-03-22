package org.ideyalabs.passenger.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassengerRequestDto {

    @NotEmpty(message="must not be null or empty")
    private String name;

    @NotEmpty(message="must not be null or empty")
    private String mobileNumber;

    @NotEmpty(message="must not be null or empty")
    @Email(message = "please enter valid email")
    private String email;

    @NotEmpty(message="must not be null or empty")
    private String password;
}
