package com.nnamdi.gpi.drones.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ModelAlreadyExistException extends RuntimeException{
    public ModelAlreadyExistException(String message){
        super(message);
    }
}
