package com.nnamdi.gpi.drones.controller;

import com.nnamdi.gpi.drones.dto.DroneDto;
import com.nnamdi.gpi.drones.dto.RegisterDroneDto;
import com.nnamdi.gpi.drones.service.DroneService;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/v1/drone")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DroneController {

    @Inject
    DroneService droneService;



    @POST
    public Uni<DroneDto> registerDrone (@Valid RegisterDroneDto droneDto) {
        return  droneService.registerDrone(droneDto);
    }

    @GET
    public Uni<List<DroneDto>> getDrones (@QueryParam("page") @DefaultValue("1") int page,
                                          @QueryParam("size") @DefaultValue("50") int size) {
        return  droneService.getDrones(page, size);
    }
}
