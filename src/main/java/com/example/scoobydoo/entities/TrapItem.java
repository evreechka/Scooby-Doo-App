package com.example.scoobydoo.entities;

import com.example.scoobydoo.entities.embedded_keys.TrapItemKey;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "TRAP_ITEM")
public class TrapItem implements Serializable {
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
