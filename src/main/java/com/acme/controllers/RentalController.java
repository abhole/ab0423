package com.acme.controllers;

import com.acme.dto.RentalDto;
import com.acme.entities.Rental;
import com.acme.mappers.RentalMapper;
import com.acme.services.RentalService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/rentals")
@RequestScoped
public class RentalController {

    private final RentalService service;
    private final RentalMapper mapper;

    @Inject
    public RentalController(RentalService service, RentalMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }


    @GET
    public Response getRentals() {
        List<Rental> rentals = service.findAll();
        List<RentalDto> rentalDtos = mapper.entityToDto(rentals);
        return Response.status(Response.Status.OK)
                .entity(rentalDtos)
                .build();
    }

    @POST
    public Response createRental(@Valid RentalDto rentalDto) {
        Rental rental = mapper.dtoToEntity(rentalDto);
        service.save(rental);
        String id = rental.getId();
        System.out.println("persisted id "+ id);
        RentalDto responseDto = mapper.entityToDto(rental);
        return Response.status(Response.Status.CREATED)
                .entity(responseDto)
                .build();
    }
}
