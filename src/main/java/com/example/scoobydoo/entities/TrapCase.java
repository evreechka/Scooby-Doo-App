package com.example.scoobydoo.entities;

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
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "TRAP_CASE")
public class TrapCase implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trap_case_id")
    private long id;

    @Column(name = "name")
    private String name;

    @Min(0)
    @Max(10)
    @Column(name = "usefulness")
    private int usefulness;

    @Column(name = "is_selected")
    private boolean isSelected;

    //TODO
    @ManyToOne
    @JoinColumn(name="case_id", referencedColumnName = "case_id")
    private CriminalCase criminalCase;

    @OneToMany(mappedBy = "trapCase") //TODO
    private Set<TrapItem> trapItems;
}
