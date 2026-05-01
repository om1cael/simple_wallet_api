package com.om1cael.simple.wallet.services;

import com.om1cael.simple.wallet.dtos.UserResponseDTO;
import com.om1cael.simple.wallet.models.User;
import com.om1cael.simple.wallet.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;

@Service
public class TransactionService {
    @Autowired
    private TransactionService service;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public UserResponseDTO transfer(Long receiverId, BigDecimal value) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(currentUser == null) throw new IllegalStateException("User is not logged in");

        User receiver = userRepository.findById(receiverId)
                .orElseThrow(() -> new EntityNotFoundException("Receiver not found"));

        currentUser.setBalance(currentUser.getBalance().subtract(value));
        receiver.setBalance(receiver.getBalance().add(value));

        userRepository.save(currentUser);
        userRepository.save(receiver);

        return new UserResponseDTO(currentUser.getId(), currentUser.getBalance());
    }
}
