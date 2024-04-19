package com.freitag.dtos;

import com.freitag.entities.OfferStatus;

public class OfferStatusDTO {

    Long id;

    String status;

    String color;

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

    public void toDTOFromObject(OfferStatus offerStatus) {
        setId(offerStatus.getId());
        setStatus(offerStatus.getStatus());
        setColor(offerStatus.getColor());
    }
}
