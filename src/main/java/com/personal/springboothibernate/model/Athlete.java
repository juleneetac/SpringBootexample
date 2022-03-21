package com.personal.springboothibernate.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "athletes")  //table name in MySQL
public class Athlete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "event")
    private String event;

    @Column(name = "gender")
    private String gender;
}
