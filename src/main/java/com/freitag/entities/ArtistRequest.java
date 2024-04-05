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

    @OneToOne(mappedBy = "artistRequest", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Offer offer;

    private Date eventStart;
    private Date eventEnd;
    private String details;

    public ArtistRequest() {}

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

    /* Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }*/

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

    /*public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }*/

    public Artist getArtist() {
        return this.artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    @Override
    public String toString() {
        return "ArtistRequest{" +
                "id=" + id +
                ", artist=" + artist +
                //", offer=" + offer +
                ", eventStart=" + eventStart +
                ", eventEnd=" + eventEnd +
                ", details='" + details + '\'' +
                '}';
    }
}
