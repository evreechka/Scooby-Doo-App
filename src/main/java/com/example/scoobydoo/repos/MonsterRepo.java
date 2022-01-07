package com.example.scoobydoo.repos;

import com.example.scoobydoo.entities.Monster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface MonsterRepo extends JpaRepository<Monster, Long> {
}
