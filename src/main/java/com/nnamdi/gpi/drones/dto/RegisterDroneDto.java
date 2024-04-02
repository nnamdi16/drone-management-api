package com.nnamdi.gpi.drones.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nnamdi.gpi.drones.util.Direction;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public record RegisterDroneDto(
        @JsonProperty("x-coordinate")
        @NotNull(message ="x-coordinate must be provided")
        @Max(value = 9, message = "x co-ordinate field boundary must not exceed 10")
        @Min(value = 0, message = "x co-ordinate field boundary must not be below 0")
        int coordinateX,
        @NotNull(message = "y-coordinate must be provided")
        @Max(value = 9, message = "y co-ordinate field boundary must not exceed 10")
        @Min(value = 0, message = "y co-ordinate field boundary must not be below 0")
        @JsonProperty("y-coordinate")
        int coordinateY,
        @JsonProperty("name")
        @NotBlank(message = "name of drone must be provided")
        String name,
        @JsonProperty("direction")
        @NotNull(message = "direction must be provided")
        @Enumerated(EnumType.STRING)
        Direction direction
) implements Serializable { }


