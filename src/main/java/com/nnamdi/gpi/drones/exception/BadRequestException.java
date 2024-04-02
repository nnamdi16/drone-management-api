package com.nnamdi.gpi.drones.exception;


import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class BadRequestException extends CustomException{
    public BadRequestException(String message){super(message);}
}
