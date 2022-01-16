package com.example.scoobydoo.entities;

import com.example.scoobydoo.entities.embedded_keys.HistoryKey;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "HISTORY")
public class History implements Serializable {
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
