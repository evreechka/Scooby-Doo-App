package com.example.scoobydoo.repos;

import com.example.scoobydoo.entities.Crime;
import com.example.scoobydoo.entities.CrimeVisit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface CrimeVisitRepo extends JpaRepository<CrimeVisit, Long> {
    CrimeVisit findCrimeVisitById(long crimeVisitId);

    List<CrimeVisit> findAllByCrime(Crime crime);
}
