package com.freitag.rest;

import com.freitag.dtos.ArtistDTO;
import com.freitag.dtos.ArtistRequestDTO;
import com.freitag.entities.*;
import com.freitag.repositories.ContractTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/contractTemplates")
    ResponseEntity<ContractTemplate> newContractTemplate(@RequestBody ContractTemplate contractTemplate) {
        contractTemplateRepository.save(contractTemplate);

        return new ResponseEntity<>(contractTemplate, HttpStatus.CREATED);
    }

    @PutMapping("/contractTemplates/{id}")
    ResponseEntity<ContractTemplate> updateContractTemplate(@PathVariable Long id, @RequestBody ContractTemplate contractTemplate) {
        ContractTemplate contractTemplateToEdit = contractTemplateRepository.findById(id).orElseThrow(() -> new NullPointerException("contractTemplate not found"));

        contractTemplateToEdit.setTemplate(contractTemplate.getTemplate());
        contractTemplateToEdit.setName(contractTemplate.getName());
        contractTemplateRepository.save(contractTemplateToEdit);

        return ResponseEntity.ok(contractTemplateToEdit);
    }

}
