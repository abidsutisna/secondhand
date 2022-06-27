package com.secondhand.secondhand.models.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.secondhand.secondhand.models.entities.NotifikasiBid;

@Repository
public interface NotifikasiBidRepository extends JpaRepository<NotifikasiBid, Long>{
    
}
