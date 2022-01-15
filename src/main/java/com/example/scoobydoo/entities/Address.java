package com.example.scoobydoo.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "ADDRESS")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private long id;

    @NotNull
    @Column(name = "city")
    private String city;

    @NotNull
    @Column(name = "avenue")
    private String avenue;

    @Min(1)
    @Column(name = "house_num")
    private long houseNumber;

    @ManyToMany(mappedBy = "livingPlaces")
    private Set<Character> residents;
}
