package com.personal.springboothibernate.controller;


import com.personal.springboothibernate.model.Athlete;
import com.personal.springboothibernate.model.Event;
import com.personal.springboothibernate.service.AthleteService;
import com.personal.springboothibernate.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private AthleteService athleteService;
    private EventService eventService;

    public EventController(EventService eventService, AthleteService athleteService) {
        super();
        this.eventService = eventService;
        this.athleteService= athleteService;
    }

    //build create athlete REST API
    @PostMapping("/save")
    public ResponseEntity<Event> saveEvent(@RequestBody Event event){
        return new ResponseEntity<Event>(eventService.saveEvent(event), HttpStatus.CREATED);
    }

    //build get all athletes REST API
    @GetMapping("/allevents")
    public List<Event> getAllEvents(){
        return eventService.getAllEvents();
    }

    @GetMapping("/{id}/events")
    public List<Event> getAllEventsByAthleteId(@PathVariable("id") long athleteId){
        return eventService.getAllEventsByAthleteId(athleteId);
    }

    @PostMapping("/add/{athleteId}/event")
    public ResponseEntity<Athlete> addEventToAthlete(@PathVariable(value = "athleteId") Long athleteId, @RequestBody Event eventRequest){
        return new ResponseEntity<Athlete>(eventService.addEventToAthlete(athleteId, eventRequest), HttpStatus.CREATED);

    }
}
