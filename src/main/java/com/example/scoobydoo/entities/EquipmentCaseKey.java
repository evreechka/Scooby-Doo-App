package com.example.scoobydoo.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class EquipmentCaseKey implements Serializable {
    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "case_id")
    private Long caseId;
}
