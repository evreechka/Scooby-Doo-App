package com.example.scoobydoo.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;

@Embeddable
@Data
@NoArgsConstructor
@Table(name = "Victim")
public class Victim {
    @Column(name = "visit_id")
    private long visitId;
    @Column(name = "visit_id")
    private long visitId;

}
