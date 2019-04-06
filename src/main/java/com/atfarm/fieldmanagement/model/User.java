package com.atfarm.fieldmanagement.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    @EqualsAndHashCode.Include
    private Long id;
    private String name;
    private String specialisation;
    @ManyToMany
    @JoinTable
    private Set<User> fields;
    @OneToMany
    @JsonManagedReference
    private List<FieldCondition> fieldConditions;

    @Version
    @Column(name = "version")
    private int version;
}
