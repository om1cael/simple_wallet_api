package com.om1cael.simple.wallet.services;

import com.om1cael.simple.wallet.models.User;
import com.om1cael.simple.wallet.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl {
    @Autowired
    private UserRepository userRepository;

    public User loadUserByEmail(String email) throws UsernameNotFoundException {
        return userRepository
                .findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Email not found"));
    }
}
