package com.personal.springboothibernate.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data  // lombok, ahorra poner todos los getters y setter
@NoArgsConstructor  // lombok, ahorra poner el constructor vacio
@Entity
@Table(name = "athletes")  //table name in MySQL
public class Athlete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "gender")
    private String gender;

//    @Column(name = "event")
//    private String event;


    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})     //many to many relation 1 athlete multiple events, 1 event multiple athletes
    @JoinTable(
            name = "athlete_event",
            joinColumns = @JoinColumn(name = "athlete_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    //Are you using Lombok's @Data annotation? That annocation creates equals and hashcode implementations that
    //include checking the content of the OneToMany collections. This interferes with Hibernate's loading strategy,
    // or something like that. Adding those annotations will prevent that the OneToMany collections get accessed too early.
    @EqualsAndHashCode.Exclude  //
    @ToString.Exclude  //
    //@JsonManagedReference
    Set<Event> event = new HashSet<>();


    public Athlete(String name, String gender) {
        this.name = name;
        this.gender = gender;
    }
}
