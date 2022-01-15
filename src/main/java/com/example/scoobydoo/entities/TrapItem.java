package com.example.scoobydoo.entities;

import com.example.scoobydoo.entities.embedded_keys.TrapItemKey;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Data
@NoArgsConstructor
@Table(name = "TRAP_ITEM")
public class TrapItem {
    @EmbeddedId
    private TrapItemKey id;

    @Min(1)
    @Column(name = "necessary_count")
    private int necessaryCount;

    @ManyToOne
    @MapsId("itemId")
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne
    @MapsId("trapId")
    @JoinColumn(name = "trap_id", referencedColumnName = "trap_case_id")
    private TrapCase trapCase;
}
