package com.personal.springboothibernate;

import com.personal.springboothibernate.model.Athlete;
import com.personal.springboothibernate.model.Event;
import com.personal.springboothibernate.repository.AthleteRepository;
import com.personal.springboothibernate.repository.EventRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootHibernateApplicationTests  {

    @Autowired
    private AthleteRepository athRepository;

    @Autowired
    private EventRepository eventRepository;


    @Test
    public void testcreateAthleteEvent() {
        // Cleanup the tables
        athRepository.deleteAllInBatch();
        eventRepository.deleteAllInBatch();

        // =======================================

        // Create a Post
        Athlete ath1 = new Athlete("Kel","M");
        Athlete ath2 = new Athlete("Mel","W");

        // Create two tags
        Event longjump = new Event("Long jump");
        Event hundred = new Event("100");
        Event six = new Event("60");


        // Add tag references in the post
        ath1.getEvent().add(longjump);
        ath1.getEvent().add(hundred);
        ath2.getEvent().add(six);
        ath2.getEvent().add(longjump);

        // Add post reference in the tags
        longjump.getAths().add(ath1);
        hundred.getAths().add(ath1);
        longjump.getAths().add(ath2);
        six.getAths().add(ath2);

        // Save in database
        athRepository.save(ath1);
        athRepository.save(ath2);

        // =======================================

    }

}
