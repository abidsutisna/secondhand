package com.secondhand.secondhand.models.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.secondhand.secondhand.models.entities.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long>{
    Optional<Users> findByEmail (String email);

    public Users findByEmailAndPassword (String email, String password);
}


