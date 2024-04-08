package com.freitag.dtos;

import com.freitag.entities.ArtistRequest;

import java.util.Date;

public class EventDTO {

    private Long id;

    private Date start;

    private Date end;

    private String title;

    public EventDTO(Long id, Date start, Date end) {
        this.id = id;
        this.start = start;
        this.end = end;
    }

    public EventDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void toDTOFromObject(ArtistRequest artistRequest) {
        setStart(artistRequest.getEventStart());
        setEnd(artistRequest.getEventEnd());
        setTitle(artistRequest.getArtist().getName());
        setId(artistRequest.getId());
    }
}
