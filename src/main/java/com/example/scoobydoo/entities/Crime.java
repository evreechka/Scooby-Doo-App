package com.example.scoobydoo.entities;

import com.example.scoobydoo.entities.enums.CrimeStatusType;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "CRIME")
public class Crime implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "crime_id")
    private long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "crime_status")
    private CrimeStatusType crimeStatus;

    @Column(name = "description")
    private String description;

    @Min(1)
    @Column(name = "fee")
    private float fee;
//TODO
    @ManyToOne
    @JoinColumn(name="sheriff_id")
    private Character sheriff;
//TODO
    @ManyToMany
    @JoinTable(
            name = "INVESTIGATOR_CRIME",
            joinColumns = @JoinColumn(name = "crime_id", referencedColumnName = "crime_id"),
            inverseJoinColumns = @JoinColumn(name = "investigator_id", referencedColumnName = "investigator_id"))
    private Set<Investigator> investigators;
}
