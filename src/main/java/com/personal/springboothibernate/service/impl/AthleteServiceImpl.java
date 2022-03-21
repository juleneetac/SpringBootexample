package com.personal.springboothibernate.service.impl;

import com.personal.springboothibernate.exception.ResourcesNotFoundException;
import com.personal.springboothibernate.model.Athlete;
import com.personal.springboothibernate.repository.AthleteRepository;
import com.personal.springboothibernate.service.AthleteService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AthleteServiceImpl implements AthleteService {

    private AthleteRepository athleteRepository;

    public AthleteServiceImpl(AthleteRepository athleteRepository) {
        super();
        this.athleteRepository = athleteRepository;
    }

    @Override
    public Athlete saveAthlete(Athlete athlete) {
        return athleteRepository.save(athlete);     //save athlete in the database
    }

    @Override
    public List<Athlete> getAllAthletes() {
        return athleteRepository.findAll();
    }

    @Override
    public Athlete getAthleteById(long id) {
        Optional <Athlete> athlete = athleteRepository.findById(id);

        if (athlete.isPresent()){
            return athlete.get();
        }
        else{
            throw new ResourcesNotFoundException("Athlete", "Id", id);
        }
    }
}
