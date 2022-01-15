package com.example.scoobydoo.entities;

import com.example.scoobydoo.entities.embedded_keys.CartKey;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "ITEM_CART")
public class ItemCart {
    @EmbeddedId
    private CartKey id;

    @Min(1)
    @Column(name = "total_count")
    private int totalCount;

    @NotNull
    @Column(name = "date_receipt")
    private LocalDateTime dateReceipt;

    //TODO
    @ManyToOne
    @MapsId("itemId")
    @JoinColumn(name = "item_id")
    private Item item;

    //TODO
    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private CaseOrder caseOrder;
}
