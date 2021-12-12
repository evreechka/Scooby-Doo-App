package com.example.scoobydoo.entities.investigation.equipment;

import com.example.scoobydoo.entities.investigation.docs.CriminalCase;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Trap_Case")
public class TrapCase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "trap_id")
    private long id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @Min(0)
    @Max(10)
    @Column(name = "usefulness")
    private int usefulness;

    @NotNull
    @Column(name = "is_selected")
    private boolean isSelected;

    @ManyToOne
    @JoinColumn(name="case_id")
    private CriminalCase criminalCase;

    @OneToMany(mappedBy = "trapCase") //TODO
    private Set<TrapItem> trapItems;
}
