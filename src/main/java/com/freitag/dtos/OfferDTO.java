package com.freitag.dtos;

import com.freitag.entities.ArtistRequest;
import com.freitag.entities.Offer;

import java.math.BigDecimal;

public class OfferDTO {

    Long id;

    private Long artistRequestId;

    private String details;
    private BigDecimal offerPrice;

    private Offer.OfferStatus offerStatus;

    public OfferDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getArtistRequestId() {
        return artistRequestId;
    }

    public void setArtistRequestId(Long artistRequestId) {
        this.artistRequestId = artistRequestId;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public BigDecimal getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(BigDecimal offerPrice) {
        this.offerPrice = offerPrice;
    }

    public Offer.OfferStatus getOfferStatus() {
        return offerStatus;
    }

    public void setOfferStatus(Offer.OfferStatus offerStatus) {
        this.offerStatus = offerStatus;
    }

    public void toDTOFromObject(Offer offer) {
        setId(offer.getId());
        setArtistRequestId(offer.getArtistRequest().getId());
        setDetails(offer.getDetails());
        setOfferPrice((offer.getOfferPrice()));
        setOfferStatus(offer.getOfferStatus());
    }

}
