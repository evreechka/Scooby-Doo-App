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
@Table(name = "Contetion")
public class Contetion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "contetion_id")
    private long id;

    @NotNull
    @Column(name = "date")
    private LocalDateTime date;

    @NotNull
    @Column(name = "description")
    private LocalDateTime description;

    @Min(0)
    @Max(10)
    @Column(name = "damage_critically")
    private int damageCritically;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "crime_id", referencedColumnName = "crime_id")
    private Crime crime;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "victim_id", referencedColumnName = "character_id")
    private Character character;

}
