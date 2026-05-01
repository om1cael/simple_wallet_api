package com.om1cael.simple.wallet.services;

import com.om1cael.simple.wallet.dtos.TransactionDTO;
import com.om1cael.simple.wallet.dtos.UserResponseDTO;
import com.om1cael.simple.wallet.exceptions.MoneyTransferException;
import com.om1cael.simple.wallet.models.Transaction;
import com.om1cael.simple.wallet.models.User;
import com.om1cael.simple.wallet.repositories.TransactionRepository;
import com.om1cael.simple.wallet.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public UserResponseDTO transfer(TransactionDTO transactionDTO) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(currentUser == null) throw new EntityNotFoundException("User is not logged in");

        User receiver = userRepository.findById(transactionDTO.receiverId())
                .orElseThrow(() -> new EntityNotFoundException("Receiver not found"));

        if(currentUser.getId().equals(receiver.getId())) {
            throw new MoneyTransferException("User cannot send money to itself");
        }

        if(currentUser.getBalance().compareTo(transactionDTO.value()) < 0) {
            throw new MoneyTransferException("No balance");
        }

        currentUser.setBalance(currentUser.getBalance().subtract(transactionDTO.value()));
        receiver.setBalance(receiver.getBalance().add(transactionDTO.value()));
        Transaction transaction = new Transaction(currentUser, receiver, transactionDTO.value());

        userRepository.save(currentUser);
        userRepository.save(receiver);
        repository.save(transaction);

        return new UserResponseDTO(currentUser.getId(), currentUser.getBalance());
    }
}
