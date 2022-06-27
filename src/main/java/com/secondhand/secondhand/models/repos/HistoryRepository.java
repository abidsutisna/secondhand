package com.secondhand.secondhand.models.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.secondhand.secondhand.models.entities.History;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
    
}
