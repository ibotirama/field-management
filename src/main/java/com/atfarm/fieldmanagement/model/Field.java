package com.atfarm.fieldmanagement.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Field {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    @EqualsAndHashCode.Include
    private Long id;
    private String name;
    private CropType cropType;
    @Embedded
    private Coordinates coordinates; // latitude and longitude
    @OneToMany
    private Set<FieldCondition> fieldConditions;
    @ManyToMany
    @JoinTable
    private Set<User> users; // feel free to implement a proper relation between users and fields. Relation is supposed to be many-to-many
    @Version
    @Column(name = "version")
    private int version;
}
