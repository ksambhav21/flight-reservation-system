package org.ideyalabs.config;



import org.ideyalabs.passenger.entity.Passenger;
import org.ideyalabs.passenger.repository.PassengerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
    @Autowired
    PassengerRepo passengerRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String...args) throws Exception {
        Passenger admin = Passenger.builder().name("FlightAppAdmin")
                .email("admin@xyz.com")
                .password(passwordEncoder.encode("pass"))
                .role("ADMIN")
                .build();
        passengerRepository.save(admin);
    }
}
