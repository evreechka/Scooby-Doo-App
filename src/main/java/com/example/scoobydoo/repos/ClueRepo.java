package com.example.scoobydoo.repos;

import com.example.scoobydoo.entities.Clue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ClueRepo extends JpaRepository<Clue, Long> {
    Clue findClueById(long clueId);
}
