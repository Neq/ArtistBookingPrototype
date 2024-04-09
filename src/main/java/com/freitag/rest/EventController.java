package com.freitag.rest;

import com.freitag.dtos.ArtistDTO;
import com.freitag.dtos.EventDTO;
import com.freitag.entities.Artist;
import com.freitag.entities.ArtistRequest;
import com.freitag.repositories.ArtistRequestRepository;
import jdk.jfr.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class EventController {

    @Autowired
    ArtistRequestRepository artistRequestRepository;

    @GetMapping("/events")
    public ResponseEntity<List<EventDTO>> all() {
        List<EventDTO> eventDtos = new ArrayList<>();
        for(ArtistRequest artistRequest : artistRequestRepository.findAll().stream().toList()) {
            EventDTO eventDto = new EventDTO();
            eventDto.toDTOFromObject(artistRequest);
            eventDtos.add(eventDto);
        }
        return ResponseEntity.ok(eventDtos);
    }

}
