package org.ideyalabs.security;

import org.ideyalabs.passenger.entity.Passenger;
import org.ideyalabs.exception.IdNotFoundException;
import org.ideyalabs.passenger.repository.PassengerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class PassengerDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PassengerRepo passengerRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Passenger> passenger = passengerRepository.findByEmail(email);
        if(passenger.isEmpty())
        {
            throw new IdNotFoundException("no such user exists with given email : " + email);
        }
        return new PassengerDetailsImpl(passenger.get());
    }
}
