package com.example.scoobydoo.entities.order;

import com.example.scoobydoo.entities.enbdings.CartKey;
import com.example.scoobydoo.entities.storage.Item;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Item_Cart")
public class ItemCart {
    @EmbeddedId
    private CartKey id;

    @Min(1)
    @Column(name = "total_count")
    private int totalCount;

    @NotNull
    @Column(name = "date_receipt")
    private LocalDateTime dateReceipt;

    @ManyToOne
    @MapsId("itemId")
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private CaseOrder caseOrder;
}
