package com.example.scoobydoo.repos;

import com.example.scoobydoo.entities.CriminalCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CriminalCaseRepo extends JpaRepository<CriminalCase, Long> {
    @Modifying
    @Query(value = "SELECT is_enough_evidence(?1)", nativeQuery = true)
    boolean isEnoughEvidence(long criminalCaseId);
    CriminalCase findCriminalCasesById(long criminalCaseId);
}
