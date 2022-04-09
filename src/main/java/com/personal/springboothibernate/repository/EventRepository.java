package com.personal.springboothibernate.repository;

import com.personal.springboothibernate.model.Athlete;
import com.personal.springboothibernate.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
   // List<Event> findEventByAthleteId(long id);  //se tiene que poner el mismo nombre que tiene en la tabla, en este caso "id"

    //@Query("SELECT DISTINCT e FROM Event e LEFT JOIN FETCH e.aths a where a.id = :id")
    List<Event> findEventByAthsId(long id);


}
