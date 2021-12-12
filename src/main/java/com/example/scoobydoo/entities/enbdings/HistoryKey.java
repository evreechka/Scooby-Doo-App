package com.example.scoobydoo.entities.enbdings;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class HistoryKey implements Serializable {
    @Column(name = "item_serial_num")
    private Long itemSerialNumber;

    @Column(name = "investigator_id")
    private Long investigatorId;
}
