package com.example.scoobydoo.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Crime")
public class Crime {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "crime_id")
    private long id;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "crima_status")
    private CrimeStatusType crimeStatus;

    @NotBlank
    @Column(name = "description")
    private String description;

    @Min(0)
    @Column(name = "fee")
    private float fee;

    @ManyToOne
    @JoinColumn(name="sheriff_id")
    private Character sheriff;

    @ManyToMany
    @JoinTable(
            name = "Investigator_Case",
            joinColumns = @JoinColumn(name = "crime_id"),
            inverseJoinColumns = @JoinColumn(name = "investigator_id"))
    private Set<Investigator> investigators;
}
