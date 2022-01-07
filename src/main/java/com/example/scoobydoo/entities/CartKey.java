package com.example.scoobydoo.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class CartKey implements Serializable {
    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "order_id")
    private Long orderId;
}
