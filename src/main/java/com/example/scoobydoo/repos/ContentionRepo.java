package com.example.scoobydoo.repos;

import com.example.scoobydoo.entities.Contention;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ContentionRepo extends JpaRepository<Contention, Long> {
    Contention findContentionById(long contentionId);
}
