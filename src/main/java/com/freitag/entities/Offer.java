package com.freitag.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "offer")
public class Offer {
    private @Id @GeneratedValue Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artistRequest_id")
    private ArtistRequest artistRequest;

    private String details;
    private BigDecimal offerPrice;

    private OfferStatus offerStatus;

    public Offer() {}

    public Offer(ArtistRequest artistRequest, String details, BigDecimal offerPrice, OfferStatus offerStatus) {
        this.artistRequest = artistRequest;
        this.details = details;
        this.offerPrice = offerPrice;
        this.offerStatus = offerStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ArtistRequest getArtistRequest() {
        return artistRequest;
    }

    public void setArtistRequest(ArtistRequest artistRequest) {
        this.artistRequest = artistRequest;
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

    public OfferStatus getOfferStatus() {
        return offerStatus;
    }

    public void setOfferStatus(OfferStatus offerStatus) {
        this.offerStatus = offerStatus;
    }

    public enum OfferStatus {
        PENDING,
        COMPLETED,
        CANCELLED
    }

    @Override
    public String toString() {
        return "Offer{" +
                "id=" + id +
                ", artistRequest=" + artistRequest +
                ", details='" + details + '\'' +
                ", offerPrice=" + offerPrice +
                ", offerStatus=" + offerStatus +
                '}';
    }
}
