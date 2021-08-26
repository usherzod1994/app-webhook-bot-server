package com.example.appwebhookbotserver.service;

import com.example.appwebhookbotserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.UUID;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        return userRepository
                .findByPhoneNumber(phoneNumber)
                .orElseThrow(()-> new UsernameNotFoundException("User with this phone number is not exist!"));
    }

    public UserDetails loadUserById(UUID userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("Animal id not found: " + userId));
    }
}
