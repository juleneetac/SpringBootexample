package com.personal.springboothibernate.controller;

import com.personal.springboothibernate.model.Athlete;
import com.personal.springboothibernate.model.Event;
import com.personal.springboothibernate.service.AthleteService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/athletes")
public class AthleteController {

    private AthleteService athleteService;

    public AthleteController(AthleteService athleteService) {
        super();
        this.athleteService = athleteService;
    }

    //build create athlete REST API
    @PostMapping("/save")
    public ResponseEntity<Athlete> saveAthlete(@RequestBody Athlete athlete){
            return new ResponseEntity<Athlete>(athleteService.saveAthlete(athlete), HttpStatus.CREATED);
    }

    //build get all athletes REST API
    @GetMapping("/allath")
    public  List<Athlete> getAllAthletes(){
        return athleteService.getAllAthletes();
    }

    //build get athletes by id REST API
    @GetMapping("{id}")
    public ResponseEntity<Athlete> getAthleteById(@PathVariable("id") long athleteId){
        return new ResponseEntity<Athlete>(athleteService.getAthleteById(athleteId), HttpStatus.OK);
    }

    //build update athlete by id REST API
    @PutMapping("/update/{id}")
    public ResponseEntity<Athlete> updateAthlete(@PathVariable("id") long athleteId, @RequestBody Athlete athlete){
        return  new ResponseEntity<Athlete>(athleteService.updateAthlete(athlete, athleteId), HttpStatus.OK);
    }

    //build delete athlete by id REST API
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAthlete(@PathVariable("id") long athleteId){
        Athlete deletedath = athleteService.getAthleteById(athleteId);
        //delete athlete from DB
        athleteService.deleteAthlete(athleteId);
        return new ResponseEntity<String>("Athlete " + deletedath.getName()+ " deleted OK!", HttpStatus.OK);
    }

    @GetMapping("{id}/athletes")
    public List<Athlete> getAllAthletesByEventId(@PathVariable("id") long eventId){
        return athleteService.getAllAthletesByEventId(eventId);
    }

}
