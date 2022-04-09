package com.personal.springboothibernate.service.impl;

import com.personal.springboothibernate.model.Athlete;
import com.personal.springboothibernate.exception.ResourcesNotFoundException;
import com.personal.springboothibernate.model.Event;
import com.personal.springboothibernate.repository.AthleteRepository;
import com.personal.springboothibernate.repository.EventRepository;
import com.personal.springboothibernate.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

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

    @Override
    public Athlete addEventToAthlete(long athId, Event eventRequest) {   // No se si está bien, seguir haciendo
        Athlete ath = athleteRepository.findById(athId).map(athlete -> {
            long eventId = eventRequest.getId();

            // tag is existed
            if (eventId != 0L) {
                Event _event = eventRepository.findById(eventId).orElseThrow(
                        () -> new ResourcesNotFoundException("Event", "Id", eventId));
                //athlete.setEvent((Set<Event>) _event);   //esto no funciona correcatmente
                athlete.getEvent().add(_event);
                athleteRepository.save(athlete);
                return athlete;
            }

            // add and create new Tag
            athlete.getEvent().add(eventRequest);
            eventRepository.save(eventRequest);
            return athleteRepository.save(athlete);

        }).orElseThrow(
                () -> new ResourcesNotFoundException("Athlete", "Id", athId));

        return ath;
    }

}
