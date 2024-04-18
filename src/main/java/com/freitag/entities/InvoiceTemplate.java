package com.freitag.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "invoiceTemplate")
public class InvoiceTemplate {
    @Id
    @GeneratedValue
    Long id;

    @Column(unique = true)
    private String name;

    @Column(columnDefinition = "LONGTEXT")
    private String template;

    public void InvoiceTemplate() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

}
