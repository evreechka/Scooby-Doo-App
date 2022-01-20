package com.example.scoobydoo.repos;

import com.example.scoobydoo.entities.BankAccount;
import com.example.scoobydoo.entities.Investigator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface BankAccountRepo extends JpaRepository<BankAccount, Long> {
    BankAccount findBankAccountByOwner(Investigator investigator);
    @Modifying
    @Query("DELETE FROM BankAccount where id=?1")
    void delete(long investigatorId);

}
