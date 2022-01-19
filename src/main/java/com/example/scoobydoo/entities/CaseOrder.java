package com.example.scoobydoo.entities;

import com.example.scoobydoo.entities.enums.OrderStatusType;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "CASE_ORDER")
public class CaseOrder implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private long id;

    @NotNull
    @Column(name = "date_order")
    private LocalDateTime timeTake;

    @NotNull
    @Column(name = "date_deliver")
    private LocalDateTime timeReturn;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatusType orderStatus;

    @Column(name = "total_cost", columnDefinition = "DOUBLE PRECISION CHECK (total_cost > 0.0)")
    private float totalCost;
    //TODO
    @ManyToOne
    @JoinColumn(name="orderer_id", referencedColumnName = "investigator_id")
    private Investigator orderer;

    //TODO
    @ManyToOne
    @JoinColumn(name="case_id", referencedColumnName = "case_id")
    private CriminalCase criminalCase;

    @OneToMany(mappedBy = "caseOrder") //TODO
    private Set<ItemCart> itemCarts;

}
