package com.etec.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.etec.backend.entity.Pressure;
import java.util.List;

public interface PressureRepository extends JpaRepository<Pressure, Long> {
    
    @Query(value = "SELECT * FROM tb02_pressure WHERE tb02_user_id = :userId", nativeQuery = true)
    List<Pressure> findByUserId(@Param("userId") Long userId);
}
