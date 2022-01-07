package com.example.scoobydoo.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Trap_Item")
public class TrapItem {
    @EmbeddedId
    private TrapItemKey id;

    @Min(0)
    @Column(name = "necessary_count")
    private int necessaryCount;

    @ManyToOne
    @MapsId("itemId")
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne
    @MapsId("trapId")
    @JoinColumn(name = "trap_id")
    private TrapCase trapCase;
}
