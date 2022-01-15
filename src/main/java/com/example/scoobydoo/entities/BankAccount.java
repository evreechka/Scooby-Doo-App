package com.example.scoobydoo.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Data
@NoArgsConstructor
@Table(name = "BANK_ACCOUNT")
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bank_account_id")
    private long id;

    @Min(1)
    @Column(name = "balance")
    private float balance;

    //TODO
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id", referencedColumnName = "investigator_id")
    private Investigator owner;
}
