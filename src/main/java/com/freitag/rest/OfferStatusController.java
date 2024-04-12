package com.freitag.rest;

import com.freitag.dtos.OfferStatusDTO;
import com.freitag.entities.OfferStatus;
import com.freitag.repositories.OfferStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class OfferStatusController {

    @Autowired
    OfferStatusRepository offerStatusRepository;

    @GetMapping("/offerStatus")
    public ResponseEntity<List<OfferStatusDTO>> all() {
        List<OfferStatusDTO> offerStatusDtos = new ArrayList<>();
        for(OfferStatus offerStatus : offerStatusRepository.findAll().stream().toList()) {
            OfferStatusDTO offerStatusDto = new OfferStatusDTO();
            offerStatusDto.toDTOFromObject(offerStatus);
            offerStatusDtos.add(offerStatusDto);
        }
        return ResponseEntity.ok(offerStatusDtos);
    }

    @GetMapping("/offerStatus/{id}")
    ResponseEntity<OfferStatusDTO> one(@PathVariable Long id) {
        OfferStatus offerStatus = offerStatusRepository.findById(id).orElseThrow(() -> new NullPointerException("offerStatus is null "+id));
        OfferStatusDTO offerStatusDto = new OfferStatusDTO();
        offerStatusDto.toDTOFromObject(offerStatus);
        return ResponseEntity.ok(offerStatusDto);
    }

}
