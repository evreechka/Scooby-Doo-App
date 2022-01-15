package com.example.scoobydoo.entities;

import com.example.scoobydoo.entities.Character;
import com.example.scoobydoo.entities.Crime;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "CONTENTION")
public class Contetion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contention_id")
    private long id;

    @NotNull
    @Column(name = "date_contention")
    private LocalDateTime dateContention;

    @Column(name = "description")
    private String description;

    @Min(0)
    @Max(10)
    @Column(name = "damage_critically")
    private int damageCritically;

    //TODO
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "crime_id", referencedColumnName = "crime_id")
    private Crime crime;

    //TODO
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "victim_id", referencedColumnName = "character_id")
    private Character character;

}
