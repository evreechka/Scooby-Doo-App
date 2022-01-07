package com.example.scoobydoo.repos;

import com.example.scoobydoo.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface AddressRepo extends JpaRepository<Address, Long> {
}
