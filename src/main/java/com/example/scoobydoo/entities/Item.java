package com.example.scoobydoo.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "item_id")
    private long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @Min(0)
    @Column(name = "cost")
    private float cost;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "type")
    private InventoryType type;

    @OneToMany(mappedBy = "item") //TODO
    private Set<ItemCart> itemCarts;

    @OneToMany(mappedBy = "item") //TODO
    private Set<EquipmentCase> equipments;

    @OneToMany(mappedBy = "item") //TODO
    private Set<TrapItem> trapItems;

    @ManyToMany
    @JoinTable(
            name = "Item_Monster",
            joinColumns = @JoinColumn(name = "item_id"),
            inverseJoinColumns = @JoinColumn(name = "monster_type_id"))
    private Set<MonsterType> monsterTypes;
}
