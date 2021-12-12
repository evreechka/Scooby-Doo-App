package com.example.scoobydoo.entities.characters;

import com.example.scoobydoo.entities.characters.Character;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "address_id")
    private long id;

    @NotBlank
    @Column(name = "city")
    private String city;

    @NotBlank
    @Column(name = "avenue")
    private String avenue;

    @Min(1)
    @Column(name = "house_num")
    private int houseNumber;

    @ManyToMany(mappedBy = "livingPlaces")
    private Set<Character> residents;
}
