package com.example.scoobydoo.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Equipment_Case")
public class EquipmentCase {
    @EmbeddedId
    private EquipmentCaseKey id;

    @ManyToOne
    @MapsId("itemId")
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne
    @MapsId("caseId")
    @JoinColumn(name = "case_id")
    private CriminalCase criminalCase;

    @Min(0)
    @Max(10)
    @Column(name = "usefulness")
    private int usefulness;

    @Min(0)
    @Column(name = "necessary_count")
    private int necessaryCount;
}
