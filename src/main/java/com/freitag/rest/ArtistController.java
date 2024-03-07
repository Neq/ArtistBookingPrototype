package com.freitag.rest;

import com.freitag.entities.Artist;
import com.freitag.repositories.ArtistRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class ArtistController {

    private final ArtistRepository artistRepository;
    private final ArtistModelAssembler assembler;

    ArtistController(ArtistRepository artistRepository, ArtistModelAssembler assembler) {

        this.artistRepository = artistRepository;
        this.assembler = assembler;
    }

    @GetMapping("/artists")
    CollectionModel<EntityModel<Artist>> all() {

        List<EntityModel<Artist>> orders = artistRepository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(orders, //
                linkTo(methodOn(ArtistController.class).all()).withSelfRel());
    }

    @GetMapping("/artists/{id}")
    EntityModel<Artist> one(@PathVariable Long id) {

        Artist artist = artistRepository.findById(id) //
                .orElseThrow(() -> new NullPointerException("one liefert nix zurück"+id));

        return assembler.toModel(artist);
    }

    @PostMapping("/artists")
    ResponseEntity<EntityModel<Artist>> newOrder(@RequestBody Artist artist) {

        Artist newOrder = artistRepository.save(artist);

        return ResponseEntity //
                .created(linkTo(methodOn(ArtistController.class).one(newOrder.getId())).toUri()) //
                .body(assembler.toModel(newOrder));
    }
}
