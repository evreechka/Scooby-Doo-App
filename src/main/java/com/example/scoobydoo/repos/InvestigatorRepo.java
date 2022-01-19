package com.example.scoobydoo.repos;

import com.example.scoobydoo.entities.Investigator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface InvestigatorRepo extends JpaRepository<Investigator, Long> {
    Investigator findInvestigatorByInvestigatorId(long investigatorId);
    List<Investigator> findAll();
}
