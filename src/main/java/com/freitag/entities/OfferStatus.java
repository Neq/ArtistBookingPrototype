package com.freitag.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "offerStatus")
public class OfferStatus {

  @Id
  @GeneratedValue
  Long id;

  private String status;

  private String color;

  public OfferStatus() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
