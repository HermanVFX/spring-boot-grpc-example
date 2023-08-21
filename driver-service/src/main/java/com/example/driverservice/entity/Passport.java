package com.example.driverservice.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(schema = "driver_service", name = "passport")
public class Passport extends BaseEntity{

    @Column(name = "serial")
    private Integer serial;

    @Column(name = "number")
    private Integer number;

    @OneToOne(mappedBy = "passport")
    private Driver driver;
}
