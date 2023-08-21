package com.example.autoservice.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(schema = "auto_service", name = "detail")
public class Detail extends BaseEntity {

    private String detailCode;

    @ManyToOne
    @JoinColumn(name = "auto_id")
    @ToString.Exclude
    private Auto auto;
}
