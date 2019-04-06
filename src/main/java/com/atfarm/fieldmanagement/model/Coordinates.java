package com.atfarm.fieldmanagement.model;

import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class Coordinates {
    private Double latitude;
    private Double longitude;

}
