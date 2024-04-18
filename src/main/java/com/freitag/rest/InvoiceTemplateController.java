package com.freitag.rest;

import com.freitag.entities.InvoiceTemplate;
import com.freitag.repositories.InvoiceTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class InvoiceTemplateController {

    @Autowired
    InvoiceTemplateRepository invoiceTemplateRepository;

    @GetMapping("/invoiceTemplates")
    public ResponseEntity<List<InvoiceTemplate>> all() {
        return ResponseEntity.ok(invoiceTemplateRepository.findAll().stream().toList());
    }

    @PostMapping("/invoiceTemplates")
    ResponseEntity<InvoiceTemplate> newInvoiceTemplate(@RequestBody InvoiceTemplate invoiceTemplate) {
        invoiceTemplateRepository.save(invoiceTemplate);

        return new ResponseEntity<>(invoiceTemplate, HttpStatus.CREATED);
    }

    @PutMapping("/invoiceTemplates/{id}")
    ResponseEntity<InvoiceTemplate> newInvoiceTemplate(@PathVariable Long id, @RequestBody InvoiceTemplate invoiceTemplate) {
        InvoiceTemplate invoiceTemplateToEdit = invoiceTemplateRepository.findById(id).orElseThrow(() -> new NullPointerException("invoiceTemplate not found"));

        invoiceTemplateToEdit.setTemplate(invoiceTemplate.getTemplate());
        invoiceTemplateToEdit.setName(invoiceTemplate.getName());
        invoiceTemplateRepository.save(invoiceTemplateToEdit);

        return ResponseEntity.ok(invoiceTemplateToEdit);
    }

}
