package com.example.scoobydoo.entities.storage;

import com.example.scoobydoo.entities.characters.Investigator;
import com.example.scoobydoo.entities.enbdings.HistoryKey;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "History")
public class History {
    @EmbeddedId
    private HistoryKey id;

    @Column(name = "time_take")
    private LocalDateTime timeTake;

    @Column(name = "time_return")
    private LocalDateTime timeReturn;

    @ManyToOne
    @MapsId("itemSerialNumber")
    @JoinColumn(name = "item_serial_num")
    private Inventory inventory;

    @ManyToOne
    @MapsId("investigatorId")
    @JoinColumn(name = "investigator_id")
    private Investigator investigator;
}
