package com.freitag;

import com.freitag.entities.Artist;
import com.freitag.entities.ArtistRequest;
import com.freitag.entities.ContractTemplate;
import com.freitag.entities.Offer;
import com.freitag.repositories.ArtistRepository;
import com.freitag.repositories.ArtistRequestRepository;
import com.freitag.repositories.ContractTemplateRepository;
import com.freitag.repositories.OfferRepository;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.Date;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(ArtistRequestRepository artistRequestRepository, ArtistRepository artistRepository, OfferRepository offerRepository, ContractTemplateRepository contractTemplateRepository) {

        return args -> {
            log.info("Initiating Database");
            ContractTemplate contractTemplateAustria = new ContractTemplate();
            contractTemplateAustria.setTemplate("Test123 {firstname} {lastname}");
            log.info("ContractTemplateAustria: "+contractTemplateAustria);
            contractTemplateRepository.save(contractTemplateAustria);

            Artist djHansWerner = new Artist("DJ Hans Werner", "Super Booking Agency GmbH", "djhanswerner@superbookingagency.com", "Austria", "Grundsteingasse 67/13", "1160", "Patrick", "Freitag", contractTemplateAustria);
            log.info("\\tArtist 1: " + artistRepository.save(djHansWerner));
            Artist superDj = new Artist("SuperDJ", "Super Booking Agency GmbH", "superdj@superbookingagency.com","Austria", "Grundsteingasse 67/13", "1160", "Patrick", "Freitag", contractTemplateAustria);
            log.info("\\tArtist 2: " + artistRepository.save(superDj));
            ArtistRequest djHansWernerRequest = new ArtistRequest(artistRepository.findById(djHansWerner.getId()).get(), new Date(), new Date(), "Test details blabbla");
            log.info("\\tArtistRequest 1: " + artistRequestRepository.save(djHansWernerRequest));
            log.info("DJHansWernerRequest: "+artistRequestRepository.findById(djHansWernerRequest.getId()).get());

            Offer djHansWernerOffer = new Offer(artistRequestRepository.findById(djHansWernerRequest.getId()).get(),"test", new BigDecimal(99.99), Offer.OfferStatus.PENDING);
            log.info("DJHansWernerOffer: "+offerRepository.save(djHansWernerOffer));

            //contractTemplateRepository.save(contractTemplateAustria);
            //log.info("CotnractTempltaeAustria size: "+contractTemplateAustria.getArtists().size());

        };

    }

}
