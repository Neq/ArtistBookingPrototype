package com.freitag.rest.artist;

import com.freitag.entities.Artist;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import org.springframework.hateoas.EntityModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
class ArtistModelAssembler implements RepresentationModelAssembler<Artist, EntityModel<Artist>> {

    @Override
    public EntityModel<Artist> toModel(Artist artist) {

        // Unconditional links to single-item resource and aggregate root

        EntityModel<Artist> orderModel = EntityModel.of(artist,
                linkTo(methodOn(ArtistController.class).one(artist.getId())).withSelfRel(),
                linkTo(methodOn(ArtistController.class).all()).withRel("artists"));

        return orderModel;
    }
}
