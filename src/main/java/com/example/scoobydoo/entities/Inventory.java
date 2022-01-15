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
@Table(name = "INVENTORY")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_serial_num")
    private long id;

    @NotNull
    @Column(name = "is_available")
    private boolean isAvailable;

    @Column(name = "deposit_date")
    private LocalDateTime depositDate;

    //TODO
    @ManyToOne
    @JoinColumn(name="item_id", referencedColumnName = "item_id")
    private Item item;

    @OneToMany(mappedBy = "inventory") //TODO
    private Set<History> histories;
}
