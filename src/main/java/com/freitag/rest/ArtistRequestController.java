package com.freitag.rest;

import com.freitag.dtos.ArtistRequestDTO;
import com.freitag.entities.Artist;
import com.freitag.entities.ArtistRequest;
import com.freitag.repositories.ArtistRepository;
import com.freitag.repositories.ArtistRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin
public class ArtistRequestController {

    @Autowired
    ArtistRequestRepository artistRequestRepository;

    @Autowired
    ArtistRepository artistRepository;

    @GetMapping("/artistRequests")
    ResponseEntity<List<ArtistRequestDTO>> all() {
        List<ArtistRequestDTO> artistRequestDtos = new ArrayList<>();
        for(ArtistRequest artistRequest : artistRequestRepository.findAll().stream().toList()) {
            ArtistRequestDTO artistRequestDto = new ArtistRequestDTO();
            artistRequestDto.toDTOFromObject(artistRequest);
            artistRequestDtos.add(artistRequestDto);
        }
        return ResponseEntity.ok(artistRequestDtos);
    }

    @GetMapping("/artistRequests/{id}")
    ResponseEntity<ArtistRequestDTO> one(@PathVariable Long id) {
        ArtistRequest artistRequest = artistRequestRepository.findById(id).orElseThrow(() -> new NullPointerException("one liefert nix zurück"+id));

        ArtistRequestDTO artistRequestDto = new ArtistRequestDTO();
        artistRequestDto.toDTOFromObject(artistRequest);

        return ResponseEntity.ok(artistRequestDto);
    }

    @PostMapping("/artistRequests")
    ResponseEntity<ArtistRequestDTO> newArtist(@RequestBody ArtistRequestDTO artistRequestDto) {
        Artist artist = artistRepository.findById(artistRequestDto.getArtistId()).orElseThrow(() -> new NullPointerException(("artist not found")));
        if (artist == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ArtistRequest artistRequest = new ArtistRequest();
        artistRequest.setArtist(artist);
        artistRequest.setEventStart(artistRequestDto.getEventStart());
        artistRequest.setEventEnd(artistRequestDto.getEventEnd());
        artistRequest.setDetails(artistRequestDto.getDetails());

        ArtistRequest createdRequest = artistRequestRepository.save(artistRequest);

        return new ResponseEntity<>(artistRequestDto, HttpStatus.CREATED);
    }



    @DeleteMapping("/artistRequests/{id}")
    ResponseEntity<ArtistRequestDTO> deleteArtist(@PathVariable Long id) {

        ArtistRequest artistRequest = artistRequestRepository.findById(id).orElseThrow(() -> new NullPointerException("artistrequest not found"));
        artistRequestRepository.delete(artistRequest);

        ArtistRequestDTO artistRequestDTO = new ArtistRequestDTO();
        artistRequestDTO.toDTOFromObject(artistRequest);
        return ResponseEntity.ok(artistRequestDTO);
    }

    @PutMapping("/artistRequests/{id}")
    ResponseEntity<ArtistRequestDTO> updateArtist(@PathVariable Long id, @RequestBody ArtistRequestDTO artistRequestDto) {
        ArtistRequest artistRequestToEdit = artistRequestRepository.findById(id).orElseThrow(() -> new NullPointerException("artistrequest not found"));

        artistRequestToEdit.setEventEnd(artistRequestDto.getEventEnd());
        artistRequestToEdit.setEventStart(artistRequestDto.getEventStart());
        artistRequestToEdit.setDetails((artistRequestDto.getDetails()));

        Artist newArtistToSet = artistRepository.findById(artistRequestDto.getArtistId()).orElseThrow(() -> new NullPointerException(("artist not found")));
        artistRequestToEdit.setArtist(newArtistToSet);

        //Artist artist = artistRe
        //artistRequestToEdit.setArtist();
        artistRequestRepository.save(artistRequestToEdit);

        return ResponseEntity.ok(artistRequestDto);
    }
}
