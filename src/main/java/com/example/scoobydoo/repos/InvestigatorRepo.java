package com.example.scoobydoo.repos;

import com.example.scoobydoo.entities.Investigator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface InvestigatorRepo extends JpaRepository<Investigator, Long> {
    Investigator findInvestigatorByInvestigatorId(long investigatorId);
    List<Investigator> findAll();
    @Modifying
    @Query("DELETE FROM Investigator where investigatorId=?1")
    void delete(long id);
}
