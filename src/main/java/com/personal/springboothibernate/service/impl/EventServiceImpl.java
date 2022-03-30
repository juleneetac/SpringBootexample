package com.personal.springboothibernate.service.impl;

import com.personal.springboothibernate.model.Athlete;
import com.personal.springboothibernate.exception.ResourcesNotFoundException;
import com.personal.springboothibernate.model.Event;
import com.personal.springboothibernate.repository.AthleteRepository;
import com.personal.springboothibernate.repository.EventRepository;
import com.personal.springboothibernate.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final AthleteRepository athleteRepository;

    public EventServiceImpl(EventRepository eventRepository, AthleteRepository athleteRepository) {
        super();
        this.eventRepository = eventRepository;
        this.athleteRepository = athleteRepository;
    }

    @Override
    public Event saveEvent(Event event) {
        return eventRepository.save(event);     //save event in the database
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> getAllEventsByAthleteId(long id) {
        Athlete existAthlete = athleteRepository.findById(id).orElseThrow(
                () -> new ResourcesNotFoundException("Athlete", "Id", id));

        List<Event> events = eventRepository.findEventByAthsId(id);
        return events;

    }
}
