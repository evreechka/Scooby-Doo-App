package com.example.scoobydoo.entities;

import com.example.scoobydoo.entities.Character;
import com.example.scoobydoo.entities.Crime;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "CONTENTION")
public class Contention implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contention_id")
    private long id;

    @Column(name = "date_contention")
    private LocalDateTime dateContention;

    @NotNull
    @Column(name = "description")
    private String contentionDescription;

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
