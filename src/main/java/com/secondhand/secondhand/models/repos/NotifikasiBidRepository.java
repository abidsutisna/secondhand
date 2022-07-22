package com.secondhand.secondhand.models.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.secondhand.secondhand.models.entities.NotifikasiBid;

@Repository
public interface NotifikasiBidRepository extends JpaRepository<NotifikasiBid, Long>{
    
    @Query("SELECT n FROM NotifikasiBid n WHERE n.userId = :userId")
    public List<NotifikasiBid> getNotifikasiByUserId(@PathVariable Long userId);
}
