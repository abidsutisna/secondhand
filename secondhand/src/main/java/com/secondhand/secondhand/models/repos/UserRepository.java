package com.secondhand.secondhand.models.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.secondhand.secondhand.models.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByEmail (String email);

    public User findByEmailAndPassword (String email, String password);
}


