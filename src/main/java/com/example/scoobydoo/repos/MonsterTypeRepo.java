package com.example.scoobydoo.repos;

import com.example.scoobydoo.entities.Monster;
import com.example.scoobydoo.entities.MonsterType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface MonsterTypeRepo extends JpaRepository<MonsterType, Long> {
}
