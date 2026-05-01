package com.om1cael.simple.wallet.services;

import com.om1cael.simple.wallet.dtos.JwtTokenDto;
import com.om1cael.simple.wallet.dtos.UserRegisterDTO;
import com.om1cael.simple.wallet.models.User;
import com.om1cael.simple.wallet.repositories.UserRepository;
import com.om1cael.simple.wallet.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public JwtTokenDto register(UserRegisterDTO userRegisterInfo) {
        User user = new User(
                userRegisterInfo.fullName(),
                userRegisterInfo.document(),
                userRegisterInfo.email(),
                passwordEncoder.encode(userRegisterInfo.password())
        );

        String token = jwtUtil.create(userRegisterInfo.email());
        repository.save(user);

        return new JwtTokenDto(token);
    }
}
