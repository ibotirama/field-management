package com.atfarm.fieldmanagement.model;

import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class Temperature {
    private Double min;
    private Double max;
    private Double avg;
}
