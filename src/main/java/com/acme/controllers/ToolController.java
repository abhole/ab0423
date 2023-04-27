package com.acme.controllers;

import com.acme.dto.ToolDto;
import com.acme.entities.Tool;
import com.acme.mappers.ToolMapper;
import com.acme.services.ToolService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/tools")
@RequestScoped
public class ToolController {
    final private ToolService toolService;
    final private ToolMapper mapper;
    @Inject
    public ToolController(ToolService toolService, ToolMapper mapper) {
        this.toolService = toolService;
        this.mapper = mapper;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTools() {
        List<Tool> tools =  toolService.findAll();
        List<ToolDto> toolDtos = mapper.entityToDto(tools);

        return Response.status(Response.Status.OK)
                .entity(toolDtos)
                .build();
    }

}
