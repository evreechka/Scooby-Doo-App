package com.example.scoobydoo.entities.embedded_keys;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class EquipmentCaseKey implements Serializable {
    @Column(name = "equipment_id")
    private long equipmentId;

    @Column(name = "case_id")
    private long caseId;
}
