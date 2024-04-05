package com.freitag.rest.artistRequest;

import com.freitag.entities.ArtistRequest;
import com.freitag.repositories.ArtistRequestRepository;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin
public class ArtistRequestController {

    private final ArtistRequestRepository artistRequestRepository;
    private final ArtistRequestModelAssembler assembler;

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
    ResponseEntity<List<ArtistRequest>> all() {
        return ResponseEntity.ok(artistRequestRepository.findAll().stream().toList());
    }

    @GetMapping("/artistRequests/{id}")
    EntityModel<ArtistRequest> one(@PathVariable Long id) {

        ArtistRequest artistRequest = artistRequestRepository.findById(id) //
                .orElseThrow(() -> new NullPointerException("one liefert nix zurück"+id));

        return assembler.toModel(artistRequest);
    }

    @PostMapping("/artistRequests")
    ResponseEntity<EntityModel<ArtistRequest>> newArtist(@RequestBody ArtistRequest artistRequest) {

        ArtistRequest newArtistRequest = artistRequestRepository.save(artistRequest);

        return ResponseEntity //
                .created(linkTo(methodOn(ArtistRequestController.class).one(newArtistRequest.getId())).toUri()) //
                .body(assembler.toModel(newArtistRequest));
    }

    @DeleteMapping("/artistRequests/{id}")
    ResponseEntity<EntityModel<ArtistRequest>> deleteArtist(@PathVariable Long id) {

        ArtistRequest artistRequest = artistRequestRepository.findById(id).orElseThrow(() -> new NullPointerException("artistrequest not found"));
        artistRequestRepository.delete(artistRequest);

        return ResponseEntity.created(linkTo(methodOn(ArtistRequestController.class).one(artistRequest.getId())).toUri()).body(assembler.toModel(artistRequest));
    }
}
