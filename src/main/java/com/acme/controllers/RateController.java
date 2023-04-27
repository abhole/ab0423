package com.acme.controllers;

import com.acme.dto.RateDto;
import com.acme.entities.Rate;
import com.acme.mappers.RateMapper;
import com.acme.services.RateService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/rates")
@RequestScoped
public class RateController {
    final private RateService service;
    final private RateMapper mapper;
    @Inject
    public RateController(RateService service, RateMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTools() {
        List<Rate> rates =  service.findAll();
        List<RateDto> rateDtos = mapper.entityToDto(rates);
        return Response.status(Response.Status.OK)
                .entity(rateDtos)
                .build();
    }

}
