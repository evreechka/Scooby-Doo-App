package com.example.scoobydoo.entities.embedded_keys;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
@Data
@Embeddable
public class TrapItemKey implements Serializable {
    @Column(name = "item_id")
    private long itemId;

    @Column(name = "trap_id")
    private long trapId;
}
