package com.freitag.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "artistRequest")
public class ArtistRequest {

    private @Id
    @GeneratedValue Long id;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @ManyToOne
    @JoinColumn(name = "offerStatus_id")
    private OfferStatus offerStatus;

    private Date eventStart;
    private Date eventEnd;
    private String notes;

    private String locationName;

    private String locationWebsite;

    public ArtistRequest() {}

    public ArtistRequest(Artist artist, Date eventStart, Date eventEnd, String notes) {
        this.artist = artist;
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Artist getArtist() {
        return this.artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public void setOfferStatus(OfferStatus offerStatus) {
        this.offerStatus = offerStatus;
    }

    public OfferStatus getOfferStatus() {
        return offerStatus;
    }

    @Override
    public String toString() {
        return "ArtistRequest{" +
                "id=" + id +
                ", artist=" + artist +
                //", offer=" + offer +
                ", eventStart=" + eventStart +
                ", eventEnd=" + eventEnd +
                ", details='" + notes + '\'' +
                '}';
    }
}
