package com.etec.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.etec.entities.Pressure;

import java.util.List;

public interface PressureRepository extends JpaRepository<Pressure, Integer> {
    
    @Query(value = "SELECT * FROM tb05_pressure WHERE tb05_user_id = :userId", nativeQuery = true)
    List<Pressure> findByUserId(@Param("userId") Integer userId);
}
