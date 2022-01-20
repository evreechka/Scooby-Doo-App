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

    public void createAccount(Investigator owner) {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setBalance(100F);
        bankAccount.setOwner(owner);
        bankAccountRepo.save(bankAccount);
    }

    public void deleteBankAccount(long id) {
        bankAccountRepo.delete(id);
    }

    public void addFee(BankAccount bankAccount, float fee) {
        float newBalance = bankAccount.getBalance() + fee;
        bankAccount.setBalance(newBalance);
        bankAccountRepo.save(bankAccount);
    }

    public BankAccount findBankAccountByOwner(Investigator investigator) {
        return bankAccountRepo.findBankAccountByOwner(investigator);
    }

    public void createAccountWithBalance(Investigator owner, Float balance) {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setOwner(owner);
        bankAccount.setBalance(balance);
        bankAccountRepo.save(bankAccount);
    }
}
