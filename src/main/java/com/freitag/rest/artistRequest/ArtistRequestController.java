package com.freitag.rest.artistRequest;

import com.freitag.dtos.ArtistRequestDTO;
import com.freitag.entities.Artist;
import com.freitag.entities.ArtistRequest;
import com.freitag.repositories.ArtistRepository;
import com.freitag.repositories.ArtistRequestRepository;
import org.apache.coyote.Response;
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

    private final ArtistRequestRepository artistRequestRepository;
    private final ArtistRequestModelAssembler assembler;

    @Autowired
    private ArtistRepository artistRepository;

    ArtistRequestController(ArtistRequestRepository artistRequestRepository, ArtistRequestModelAssembler assembler) {
        this.artistRequestRepository = artistRequestRepository;
        this.assembler = assembler;
    }

    @GetMapping("/artistRequests")
    /*CollectionModel<EntityModel<ArtistRequest>> all() {

        List<EntityModel<ArtistRequest>> artistRequests = artistRequestRepository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(artistRequests, //
                linkTo(methodOn(ArtistRequestController.class).all()).withSelfRel());
    }*/
    ResponseEntity<List<ArtistRequestDTO>> all() {
        List<ArtistRequestDTO> artistRequestDtos = new ArrayList<>();
        for(ArtistRequest artistRequest : artistRequestRepository.findAll().stream().toList()) {
            ArtistRequestDTO artistRequestDto = new ArtistRequestDTO();
            artistRequestDto.setEventEnd(artistRequest.getEventEnd());
            artistRequestDto.setEventStart(artistRequest.getEventStart());
            artistRequestDto.setDetails(artistRequest.getDetails());
            artistRequestDto.setArtistId(artistRequest.getArtist().getId());

            artistRequestDtos.add(artistRequestDto);
        }
        return ResponseEntity.ok(artistRequestDtos);
    }

    @GetMapping("/artistRequests/{id}")
    /*EntityModel<ArtistRequest> one(@PathVariable Long id) {

        ArtistRequest artistRequest = artistRequestRepository.findById(id) //
                .orElseThrow(() -> new NullPointerException("one liefert nix zurück"+id));

        return assembler.toModel(artistRequest);
    }*/
    ResponseEntity<ArtistRequest> one(@PathVariable Long id) {
        ArtistRequest artistRequest = artistRequestRepository.findById(id).orElseThrow(() -> new NullPointerException("one liefert nix zurück"+id));
        return ResponseEntity.ok(artistRequest);
    }

    @PostMapping("/artistRequests")
    /*ResponseEntity<EntityModel<ArtistRequest>> newArtist(@RequestBody ArtistRequest artistRequest) {

        ArtistRequest newArtistRequest = artistRequestRepository.save(artistRequest);

        return ResponseEntity //
                .created(linkTo(methodOn(ArtistRequestController.class).one(newArtistRequest.getId())).toUri()) //
                .body(assembler.toModel(newArtistRequest));
    }*/
    ResponseEntity<ArtistRequest> newArtist(@RequestBody ArtistRequestDTO artistRequestDto) {
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
        return new ResponseEntity<>(createdRequest, HttpStatus.CREATED);


        /*Artist newArtistToAdd = artistRepository.findById(artistRequestDto.getArtistId()).orElseThrow(() -> new NullPointerException(("artist not found")));
        artistRequestDto.setArtist(newArtistToAdd);
        ArtistRequest savedArtistRequest = artistRequestRepository.save(artistRequestDto);

        return ResponseEntity.ok(savedArtistRequest);*/
    }



    @DeleteMapping("/artistRequests/{id}")
    ResponseEntity<EntityModel<ArtistRequest>> deleteArtist(@PathVariable Long id) {

        ArtistRequest artistRequest = artistRequestRepository.findById(id).orElseThrow(() -> new NullPointerException("artistrequest not found"));
        artistRequestRepository.delete(artistRequest);

        return ResponseEntity.created(linkTo(methodOn(ArtistRequestController.class).one(artistRequest.getId())).toUri()).body(assembler.toModel(artistRequest));
    }

    @PutMapping("/artistRequests/{id}")
    ResponseEntity<ArtistRequest> updateArtist(@PathVariable Long id, @RequestBody ArtistRequestDTO artistRequestDto) {
        ArtistRequest artistRequestToEdit = artistRequestRepository.findById(id).orElseThrow(() -> new NullPointerException("artistrequest not found"));

        artistRequestToEdit.setEventEnd(artistRequestDto.getEventEnd());
        artistRequestToEdit.setEventStart(artistRequestDto.getEventStart());
        artistRequestToEdit.setDetails((artistRequestDto.getDetails()));

        Artist newArtistToSet = artistRepository.findById(artistRequestDto.getArtistId()).orElseThrow(() -> new NullPointerException(("artist not found")));
        artistRequestToEdit.setArtist(newArtistToSet);

        //Artist artist = artistRe
        //artistRequestToEdit.setArtist();
        artistRequestRepository.save(artistRequestToEdit);

        return ResponseEntity.ok(artistRequestToEdit);
    }
}
