package com.freitag.dtos;

import com.freitag.entities.ArtistRequest;

import java.util.Date;

public class ArtistRequestDTO {
    private Long id;
    private Long artistId;

    private Long offerStatusId;
    private Date eventStart;
    private Date eventEnd;
    private String notes;

    private String locationName;

    private String locationWebsite;

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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationWebsite() {
        return locationWebsite;
    }

    public void setLocationWebsite(String locationWebsite) {
        this.locationWebsite = locationWebsite;
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
        setNotes(artistRequest.getNotes());
        setArtistId(artistRequest.getArtist().getId());
        setOfferStatusId(artistRequest.getOfferStatus().getId());
        setLocationName(artistRequest.getLocationName());
        setLocationWebsite(artistRequest.getLocationWebsite());
    }
}
