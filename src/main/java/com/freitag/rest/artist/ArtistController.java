package com.freitag.rest.artist;

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
@CrossOrigin
public class ArtistController {

    private final ArtistRepository artistRepository;
    private final ArtistModelAssembler assembler;

    ArtistController(ArtistRepository artistRepository, ArtistModelAssembler assembler) {
        this.artistRepository = artistRepository;
        this.assembler = assembler;
    }

    @GetMapping("/artists")
    /*CollectionModel<EntityModel<Artist>> all() {

        List<EntityModel<Artist>> artists = artistRepository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(artists, //
                linkTo(methodOn(ArtistController.class).all()).withSelfRel());
    }*/
    public ResponseEntity<List<Artist>> all() {
        return ResponseEntity.ok(artistRepository.findAll().stream().toList());
    }

    /*@GetMapping("/artists")
    public List<Artist> getAllArtists() {
        return artistRepository.findAll();
    }*/

    @GetMapping("/artists/{id}")
    EntityModel<Artist> one(@PathVariable Long id) {

        Artist artist = artistRepository.findById(id) //
                .orElseThrow(() -> new NullPointerException("one liefert nix zurück"+id));

        return assembler.toModel(artist);
    }

    @PostMapping("/artists")
    ResponseEntity<EntityModel<Artist>> newArtist(@RequestBody Artist artist) {

        Artist newArtist = artistRepository.save(artist);

        return ResponseEntity //
                .created(linkTo(methodOn(ArtistController.class).one(newArtist.getId())).toUri()) //
                .body(assembler.toModel(newArtist));
    }

    @DeleteMapping("/artists/{id}")
    ResponseEntity<EntityModel<Artist>> deleteArtist(@PathVariable Long id) {

        Artist artist = artistRepository.findById(id).orElseThrow(() -> new NullPointerException("artist not found"));
        artistRepository.delete(artist);

        return ResponseEntity.created(linkTo(methodOn(ArtistController.class).one(artist.getId())).toUri()).body(assembler.toModel(artist));
    }
}
