package com.example.scoobydoo.entities;

import com.example.scoobydoo.entities.embedded_keys.HistoryKey;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "HISTORY")
public class History {
    @EmbeddedId
    private HistoryKey id;

    @Column(name = "time_take")
    private LocalDateTime timeTake;

    @Column(name = "time_return")
    private LocalDateTime timeReturn;

    //TODO
    @ManyToOne
    @MapsId("itemSerialNumber")
    @JoinColumn(name = "item_serial_num")
    private Inventory inventory;

    //TODO
    @ManyToOne
    @MapsId("investigatorId")
    @JoinColumn(name = "investigator_id")
    private Investigator investigator;
}
