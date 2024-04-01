package com.freitag.rest.artistRequest;

import com.freitag.entities.ArtistRequest;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import org.springframework.hateoas.EntityModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
class ArtistRequestModelAssembler implements RepresentationModelAssembler<ArtistRequest, EntityModel<ArtistRequest>> {

    @Override
    public EntityModel<ArtistRequest> toModel(ArtistRequest artistRequest) {

        // Unconditional links to single-item resource and aggregate root

        EntityModel<ArtistRequest> orderModel = EntityModel.of(artistRequest,
                linkTo(methodOn(ArtistRequestController.class).one(artistRequest.getId())).withSelfRel(),
                linkTo(methodOn(ArtistRequestController.class).all()).withRel("artistRequests"));

        return orderModel;
    }
}
