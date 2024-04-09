package com.freitag.rest;

import com.freitag.dtos.EventDTO;
import com.freitag.dtos.OfferDTO;
import com.freitag.entities.ArtistRequest;
import com.freitag.entities.Offer;
import com.freitag.repositories.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class OfferController {

    @Autowired
    OfferRepository offerRepository;

    @GetMapping("/offers")
    public ResponseEntity<List<OfferDTO>> all() {
        List<OfferDTO> offerDtos = new ArrayList<>();
        for(Offer offer : offerRepository.findAll().stream().toList()) {
            OfferDTO offerDto = new OfferDTO();
            offerDto.toDTOFromObject(offer);
            offerDtos.add(offerDto);
        }
        return ResponseEntity.ok(offerDtos);
    }

}
