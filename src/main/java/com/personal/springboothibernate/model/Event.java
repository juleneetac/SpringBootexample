package com.personal.springboothibernate.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;



@Data
@NoArgsConstructor  // lombok, ahorra poner el constructor vacio
@Entity
@Table(name = "events")  //table name in MySQL
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "event_name", nullable = false)
    private String event_name;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "event")
    //Are you using Lombok's @Data annotation? That annocation creates equals and hashcode implementations that
    //include checking the content of the OneToMany collections. This interferes with Hibernate's loading strategy,
    // or something like that. Adding those annotations will prevent that the OneToMany collections get accessed too early.
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonBackReference
    private Set<Athlete> aths = new HashSet<>();

    public Event(String event_name) {
        this.event_name = event_name;
    }


    //    @Column(name = "total_ath")
//    private String event;
//
//    @Column(name = "gender")
//    private String gender;
}
