package org.ideyalabs.passenger.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtRequest {

//    @NotEmpty(message="must not be null or empty")
    private String email;
//    @NotEmpty(message="must not be null or empty")
    private String password;

}