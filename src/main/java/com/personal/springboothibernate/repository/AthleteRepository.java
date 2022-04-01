package com.personal.springboothibernate.repository;

import com.personal.springboothibernate.model.Athlete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AthleteRepository extends JpaRepository<Athlete, Long> {

    @Query("SELECT DISTINCT a FROM Athlete a LEFT JOIN FETCH a.event e")
    List<Athlete> findAll();

    List<Athlete> findByEventContaining(String event);

}
