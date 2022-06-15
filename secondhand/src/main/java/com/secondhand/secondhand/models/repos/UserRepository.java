package com.secondhand.secondhand.models.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.secondhand.secondhand.models.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    User findByUsername(String username);
}


