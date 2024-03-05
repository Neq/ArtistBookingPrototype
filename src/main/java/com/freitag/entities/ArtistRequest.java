package com.freitag.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class ArtistRequest {

    private @Id
    @GeneratedValue Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "artist_id", referencedColumnName = "id")
    private Artist artist;
    private Date eventStart;
    private Date eventEnd;
    private String details;

    public ArtistRequest(Artist artist, Date eventStart, Date eventEnd, String details) {
        this.artist = artist;
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
        this.details = details;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
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
}
