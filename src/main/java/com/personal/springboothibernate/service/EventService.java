package com.personal.springboothibernate.service;


import com.personal.springboothibernate.model.Athlete;
import com.personal.springboothibernate.model.Event;

import java.util.List;

public interface EventService {

    Event saveEvent (Event event);
    List<Event> getAllEvents();
    List<Event> getAllEventsByAthleteId(long id);
    Athlete addEventToAthlete (long id, Event event);
}
