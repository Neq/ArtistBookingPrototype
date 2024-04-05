package com.freitag.rest;

import com.freitag.dtos.ArtistDTO;
import com.freitag.entities.Artist;
import com.freitag.entities.ContractTemplate;
import com.freitag.repositories.ContractTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class ContractTemplateController {

    @Autowired
    ContractTemplateRepository contractTemplateRepository;

    @GetMapping("/contractTemplates")
    public ResponseEntity<List<ContractTemplate>> all() {
        /*List<ArtistDTO> artistDtos = new ArrayList<>();
        for(ContractTemplate contractTemplate : contractTemplateRepository.findAll().stream().toList()) {

        }
        return ResponseEntity.ok(artistDtos);*/
        return ResponseEntity.ok(contractTemplateRepository.findAll().stream().toList());
    }

}
