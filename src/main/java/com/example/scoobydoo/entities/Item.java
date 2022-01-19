package com.example.scoobydoo.entities;

import com.example.scoobydoo.entities.enums.ItemType;
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
@Table(name = "ITEM")
public class Item implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private ItemType type;

    @Column(name = "cost", columnDefinition = "DOUBLE PRECISION CHECK (cost > 0.0)")
    private float cost;

    @OneToMany(mappedBy = "item") //TODO
    private Set<ItemCart> itemCarts;

    @OneToMany(mappedBy = "item") //TODO
    private Set<EquipmentCase> equipments;

    @OneToMany(mappedBy = "item") //TODO
    private Set<TrapItem> trapItems;

    //TODO
    @ManyToMany
    @JoinTable(
            name = "Item_Monster",
            joinColumns = @JoinColumn(name = "item_id", referencedColumnName = "item_id"),
            inverseJoinColumns = @JoinColumn(name = "monster_type_id", referencedColumnName = "monster_type_id"))
    private Set<MonsterType> monsterTypes;
}
