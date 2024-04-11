package com.nnamdi.gpi.drones.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record PaginatedResponse<T>(
        List<T> content,
        @JsonProperty("totalPages")
        int totalPages,
        @JsonProperty("totalElements")
        long totalElements,

        boolean last,

        boolean first,
        @JsonProperty("numberOfElements")
        int numberOfElements,
        int size,

        int page


        ) {
}
