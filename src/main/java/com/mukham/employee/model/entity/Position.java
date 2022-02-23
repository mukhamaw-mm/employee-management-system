package com.mukham.employee.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "position")
@Data
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String positionName;
}
