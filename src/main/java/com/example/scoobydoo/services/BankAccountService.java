package com.example.scoobydoo.services;

import com.example.scoobydoo.entities.BankAccount;
import com.example.scoobydoo.entities.Investigator;
import com.example.scoobydoo.repos.BankAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankAccountService {
    @Autowired
    private BankAccountRepo bankAccountRepo;
    public BankAccount createEmptyAccount(Investigator owner) {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setBalance(0F);
        bankAccount.setOwner(owner);
        bankAccountRepo.save(bankAccount);
        return bankAccount;
    }
}
