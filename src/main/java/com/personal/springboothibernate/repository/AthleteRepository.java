package com.personal.springboothibernate.repository;

import com.personal.springboothibernate.model.Athlete;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AthleteRepository extends JpaRepository<Athlete, Long> {

    List<Athlete> findByEventContaining(String event);

}
