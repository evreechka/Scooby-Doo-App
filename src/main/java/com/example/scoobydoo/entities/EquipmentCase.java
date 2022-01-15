package com.example.scoobydoo.entities;

import com.example.scoobydoo.entities.embedded_keys.EquipmentCaseKey;
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

    @Min(1)
    @Column(name = "necessary_count")
    private int necessaryCount;

    @Min(0)
    @Max(10)
    @Column(name = "usefulness")
    private int usefulness;

    //TODO
    @ManyToOne
    @MapsId("equipmentId")
    @JoinColumn(name = "equipment_id", referencedColumnName = "item_id")
    private Item item;

    //TODO
    @ManyToOne
    @MapsId("caseId")
    @JoinColumn(name = "case_id", referencedColumnName = "case_id")
    private CriminalCase criminalCase;
}
