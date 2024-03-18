package org.ideyalabs.passenger.security;

import org.ideyalabs.passenger.entity.Passenger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;

public class PassengerDetailsImpl implements UserDetails {
    private Passenger passenger;
    public PassengerDetailsImpl(Passenger passenger)
    {
        this.passenger=passenger;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        HashSet<SimpleGrantedAuthority> set = new HashSet();
        set.add( new SimpleGrantedAuthority("" + this.passenger.getRole()));
        return set;
    }

    @Override
    public String getPassword() {
        return this.passenger.getPassword();
    }

    @Override
    public String getUsername() {
        return this.passenger.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
