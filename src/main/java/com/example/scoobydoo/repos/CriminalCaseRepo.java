package com.example.scoobydoo.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CriminalCaseRepo extends JpaRepository<CriminalCaseRepo, Long> {
}