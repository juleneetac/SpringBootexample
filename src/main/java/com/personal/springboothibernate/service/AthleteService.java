package com.personal.springboothibernate.service;

import com.personal.springboothibernate.model.Athlete;

import java.util.List;

public interface AthleteService {
    Athlete saveAthlete (Athlete athlete);
    List<Athlete> getAllAthletes();
    Athlete getAthleteById(long id);
    Athlete updateAthlete(Athlete athlete, long id);
    void deleteAthlete(long id);

}
