package com.nnamdi.gpi.drones.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nnamdi.gpi.drones.util.Direction;

public record DroneDto(
        @JsonProperty("id")
        String id,
        @JsonProperty("x-coordinate")
        int coordinateX,
        @JsonProperty("y-coordinate")
        int coordinateY,
        @JsonProperty("name")
        String name,
        @JsonProperty("direction")
        Direction direction
) { }
