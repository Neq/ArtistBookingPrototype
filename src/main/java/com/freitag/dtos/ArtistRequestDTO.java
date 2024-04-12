package com.freitag.dtos;

import com.freitag.entities.ArtistRequest;

import java.util.Date;

public class ArtistRequestDTO {
    private Long id;
    private Long artistId;

    private Long offerStatusId;
    private Date eventStart;
    private Date eventEnd;
    private String details;

    public Long getArtistId() {
        return artistId;
    }

    public ArtistRequestDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setArtistId(Long artistId) {
        this.artistId = artistId;
    }

    public Date getEventStart() {
        return eventStart;
    }

    public void setEventStart(Date eventStart) {
        this.eventStart = eventStart;
    }

    public Date getEventEnd() {
        return eventEnd;
    }

    public void setEventEnd(Date eventEnd) {
        this.eventEnd = eventEnd;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Long getOfferStatusId() {
        return offerStatusId;
    }

    public void setOfferStatusId(Long offerStatusId) {
        this.offerStatusId = offerStatusId;
    }

    public void toDTOFromObject(ArtistRequest artistRequest) {
        setId(artistRequest.getId());
        setEventEnd(artistRequest.getEventEnd());
        setEventStart(artistRequest.getEventStart());
        setDetails(artistRequest.getDetails());
        setArtistId(artistRequest.getArtist().getId());
        setOfferStatusId(artistRequest.getOfferStatus().getId());
    }
}
