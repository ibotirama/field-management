package com.atfarm.fieldmanagement.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.sql.Date;
@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class FieldCondition {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    @EqualsAndHashCode.Include
    private Long id;
    private Date date; // day
    @ManyToOne
    private Field field;  // feel free to implement a proper relation between fields and field conditions
    @Embedded
    private Temperature temperature; // min, max, the average for that day
    private Double cloudiness;
    private Double vegetation;
    @Version
    @Column(name = "version")
    private int version;
}
