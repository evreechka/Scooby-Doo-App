package com.example.scoobydoo.repos;

import com.example.scoobydoo.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface ProfileRepo extends JpaRepository<Profile, Long> {
    Profile findProfileByUsername(String username);
    Profile findProfileById(long profileId);
}
