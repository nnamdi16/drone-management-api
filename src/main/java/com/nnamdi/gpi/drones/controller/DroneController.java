package com.nnamdi.gpi.drones.controller;

import com.nnamdi.gpi.drones.dto.DroneDto;
import com.nnamdi.gpi.drones.dto.Response;
import com.nnamdi.gpi.drones.request.RegisterDroneDto;
import com.nnamdi.gpi.drones.service.DroneService;
import com.nnamdi.gpi.drones.util.ResponseUtil;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import static com.nnamdi.gpi.drones.controller.BaseApiController.BASE_API_PATH;
import static com.nnamdi.gpi.drones.controller.BaseApiController.DRONE;

@Path(BASE_API_PATH + DRONE)
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DroneController {

    @Inject
    DroneService droneService;

    @Inject
    ResponseUtil responseUtil;



    @POST
    public Uni<DroneDto> registerDrone (@Valid RegisterDroneDto droneDto) {
        return  droneService.registerDrone(droneDto);
    }

    @GET
    public Uni<Response> getDrones (@QueryParam("page") @DefaultValue("1") int page,
                                    @QueryParam("size") @DefaultValue("50") int size) {
        return  droneService.getDrones(page, size).map(responseUtil::getSuccessResponse);
    }
}
