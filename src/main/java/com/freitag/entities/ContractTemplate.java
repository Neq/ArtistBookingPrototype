package com.freitag.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "contractTemplate")
public class ContractTemplate {

    @Id
    @GeneratedValue
    Long id;

    private String template;

    public void ContractTemplate() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

}
