package com.om1cael.simple.wallet.repositories;

import com.om1cael.simple.wallet.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
