package com.example.autoservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(schema = "auto-service", name = "auto")
public class Auto extends BaseEntity {

    @Column(name = "vin")
    private String vin;

    @Column(name = "state_number")
    private String stateNumber;

}
