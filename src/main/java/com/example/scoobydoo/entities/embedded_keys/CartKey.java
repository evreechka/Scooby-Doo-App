package com.example.scoobydoo.entities.embedded_keys;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class CartKey implements Serializable {
    @Column(name = "item_id")
    private long itemId;

    @Column(name = "order_id")
    private long orderId;
}
