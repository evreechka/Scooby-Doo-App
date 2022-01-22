package com.example.scoobydoo.repos;

import com.example.scoobydoo.entities.CrimeScene;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CrimeSceneRepo extends JpaRepository<CrimeScene, Long> {
    CrimeScene findCrimeSceneById(long crimeSceneId);
}
