package com.freitag;

import com.freitag.entities.Artist;
import com.freitag.entities.ArtistRequest;
import com.freitag.repositories.ArtistRepository;
import com.freitag.repositories.ArtistRequestRepository;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(ArtistRequestRepository artistRequestRepository, ArtistRepository artistRepository, Session session) {

        return args -> {
            log.info("Initiating Database");
            Artist djHansWerner = new Artist("DJ Hans Werner", "Super Booking Agency GmbH", "djhanswerner@superbookingagency.com");
            log.info("\\tArtist 1: " + artistRepository.save(djHansWerner));
            session.persist(djHansWerner);
            log.info("\\tArtist 2: " + artistRepository.save(new Artist("SuperDJ", "Super Booking Agency GmbH", "superdj@superbookingagency.com")));

            log.info("\\tArtistRequest 1: " + artistRequestRepository.save(new ArtistRequest(artistRepository.findById(djHansWerner.getId()).get(), new Date(), new Date(), "Alles Gut")));
        };

    }

}
