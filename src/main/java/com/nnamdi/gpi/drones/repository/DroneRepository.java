package com.nnamdi.gpi.drones.repository;

import com.nnamdi.gpi.drones.model.Drone;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DroneRepository implements PanacheRepository<Drone> {

    public Uni<Drone> findByCoordinatesOrName(int coordinateX, int coordinateY, String name) {
        return find("(coordinateX = ?1 AND coordinateY = ?2) OR lower(name) = lower(?3)",
                coordinateX, coordinateY, name).firstResult();
    }

}
