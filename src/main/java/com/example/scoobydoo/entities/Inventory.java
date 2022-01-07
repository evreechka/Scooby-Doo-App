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
@Table(name = "Item")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "item_serial_num")
    private long id;

    @NotNull
    @Column(name = "is_available")
    private boolean isAvailable;

    @NotNull
    @Column(name = "deposit_date")
    private LocalDateTime depositDate;

    @ManyToOne
    @JoinColumn(name="item_id")
    private Item item;

    @OneToMany(mappedBy = "inventory") //TODO
    private Set<History> histories;
}
