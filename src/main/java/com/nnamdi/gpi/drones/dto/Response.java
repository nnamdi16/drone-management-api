package com.nnamdi.gpi.drones.dto;

public record Response(Integer code, String message, Object data, String descriptiveMessage) {
}
