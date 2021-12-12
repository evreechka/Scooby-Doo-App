package com.example.scoobydoo.entities.enbdings;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
@Data
@Embeddable
public class TrapItemKey implements Serializable {
    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "trap_id")
    private Long trapId;
}
