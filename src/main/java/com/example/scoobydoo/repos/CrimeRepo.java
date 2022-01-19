package com.example.scoobydoo.repos;

import com.example.scoobydoo.entities.Crime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface CrimeRepo extends JpaRepository<Crime, Long> {
    List<Crime> findAll();
    Crime findCrimeById(long crimeId);
}
