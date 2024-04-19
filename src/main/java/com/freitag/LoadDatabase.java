package com.freitag;

import com.freitag.entities.*;
import com.freitag.repositories.*;
import org.antlr.v4.runtime.misc.LogManager;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.Date;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(ArtistRequestRepository artistRequestRepository, ArtistRepository artistRepository, ContractTemplateRepository contractTemplateRepository, InvoiceTemplateRepository invoiceTemplateRepository, OfferStatusRepository offerStatusRepository) {

        return args -> {
            log.info("Initiating Database");

            // Contract Template
            ContractTemplate contractTemplateAustria = new ContractTemplate();
            contractTemplateAustria.setName("Template Austria");
            contractTemplateAustria.setTemplate("Test123 {firstname} {lastname}");
            log.info("ContractTemplateAustria: "+contractTemplateAustria);
            contractTemplateRepository.save(contractTemplateAustria);


            // InvoiceTemplate
            InvoiceTemplate invoiceTemplateAustria = new InvoiceTemplate();
            invoiceTemplateAustria.setName("Template Austria");
            invoiceTemplateAustria.setTemplate("Test123 {firstname} {lastname}");
            log.info("InvoiceTemplateAustria: "+invoiceTemplateAustria);
            invoiceTemplateRepository.save(invoiceTemplateAustria);

            // OfferStatus
            OfferStatus offerStatus = new OfferStatus();
            offerStatus.setStatus("PENDING");
            offerStatus.setColor("lightblue");
            offerStatusRepository.save(offerStatus);
            log.info("OfferStatus Pending: "+offerStatus);

            OfferStatus offerStatusCancel = new OfferStatus();
            offerStatusCancel.setStatus("CANCEL");
            offerStatusCancel.setColor("red");
            offerStatusRepository.save(offerStatusCancel);
            log.info("OfferStatus Cancel: "+offerStatusCancel);

            OfferStatus offerStatusConfirmed = new OfferStatus();
            offerStatusConfirmed.setStatus("CONFIRMED");
            offerStatusConfirmed.setColor("green");
            offerStatusRepository.save(offerStatusConfirmed);
            log.info("OfferStatus Confirmed: "+offerStatusConfirmed);

            // Artists & ArtistRequests

            Artist djHansWerner = new Artist("DJ Hans Werner", "Super Booking Agency GmbH", "management@email.com", "0123124345345", "djhanswerner@superbookingagency.com", "Austria", "Grundsteingasse 67/13", "1160", "Patrick", "Freitag", "06644517408", contractTemplateAustria, invoiceTemplateAustria);
            log.info("\\tArtist 1: " + artistRepository.save(djHansWerner));
            Artist superDj = new Artist("SuperDJ", "Super Booking Agency GmbH", "management@email.com", "0123124345345","superdj@superbookingagency.com","Austria", "Grundsteingasse 67/13", "1160", "Patrick", "Freitag", "06644517408", contractTemplateAustria, invoiceTemplateAustria);
            log.info("\\tArtist 2: " + artistRepository.save(superDj));
            ArtistRequest djHansWernerRequest = new ArtistRequest(artistRepository.findById(djHansWerner.getId()).get(), new Date(), new Date(), "Test details blabbla");
            djHansWernerRequest.setOfferStatus(offerStatusRepository.findById(Long.valueOf(1)).orElseThrow(() -> new NullPointerException("offerStatus ist null")));
            log.info("\\tArtistRequest 1: " + artistRequestRepository.save(djHansWernerRequest));
            log.info("DJHansWernerRequest: "+artistRequestRepository.findById(djHansWernerRequest.getId()).get());

        };

    }

}
