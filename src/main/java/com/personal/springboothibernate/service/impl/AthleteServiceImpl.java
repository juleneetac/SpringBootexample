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

    private final AthleteRepository athleteRepository;

    public AthleteServiceImpl(AthleteRepository athleteRepository) {
        super();
        this.athleteRepository = athleteRepository;
    }

    @Override
    public Athlete saveAthlete(Athlete athlete) {
        return athleteRepository.save(athlete);     //save athlete in the database
    }

    @Override
    public  List<Athlete> getAllAthletes() {
        List<Athlete> newlist = athleteRepository.findAll();
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

    @Override
    public Athlete updateAthlete(Athlete athlete, long id) {
        //check if athlete with given id exist in DB
        Athlete existAthlete = athleteRepository.findById(id).orElseThrow(
                () -> new ResourcesNotFoundException("Athlete", "Id", id));

        existAthlete.setName(athlete.getName());
        existAthlete.setGender(athlete.getGender());
        existAthlete.setEvent(athlete.getEvent());

        //save existimg athlete to DB
        athleteRepository.save(existAthlete);

        return existAthlete;
    }

    @Override
    public void deleteAthlete(long id) {
        //check if athlete with given id exist in DB
        athleteRepository.findById(id).orElseThrow(
                () -> new ResourcesNotFoundException("Athlete", "Id", id));

        athleteRepository.deleteById(id);
    }
}
