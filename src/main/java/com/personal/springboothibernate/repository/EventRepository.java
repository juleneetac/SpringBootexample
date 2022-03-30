package com.personal.springboothibernate.repository;

import com.personal.springboothibernate.model.Athlete;
import com.personal.springboothibernate.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
   // List<Event> findEventByAthleteId(long id);  //se tiene que poner el mismo nombre que tiene en la tabla, en este caso "id"
    List<Event> findEventByAthsId(long id);


}
