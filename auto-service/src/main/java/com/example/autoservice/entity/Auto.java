package com.example.autoservice.entity;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(schema = "auto_service", name = "auto")
public class Auto extends BaseEntity {

    @Column(name = "vin")
    private String vin;

    @Column(name = "state_number")
    private String stateNumber;

    @Column(name = "driver_id")
    private Long driverId;

    @Column(name = "make")
    private String make;

    @Column(name = "age")
    private Integer age;

    @OneToMany(mappedBy = "auto", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Detail> details = new ArrayList<>();
}
