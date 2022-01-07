package com.example.scoobydoo.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Case_Order")
public class CaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private long id;

    @NotNull
    @Column(name = "date_order")
    private LocalDateTime timeTake;

    @NotNull
    @Column(name = "date_deliver")
    private LocalDateTime timeReturn;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "order_status")
    private OrderStatusType orderStatus;

    @ManyToOne
    @JoinColumn(name="orderer_id")
    private Investigator orderer;

    @ManyToOne
    @JoinColumn(name="case_id")
    private CriminalCase criminalCase;

    @OneToMany(mappedBy = "caseOrder") //TODO
    private Set<ItemCart> itemCarts;

}
