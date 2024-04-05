package com.freitag.dtos;

import java.util.Date;

public class ArtistRequestDTO {
    private Long artistId;
    private Date eventStart;
    private Date eventEnd;
    private String details;

    public Long getArtistId() {
        return artistId;
    }

    public ArtistRequestDTO() {}

    public ArtistRequestDTO(Long artistId, Date eventStart, Date eventEnd, String details) {
        this.artistId = artistId;
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
        this.details = details;
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
}
