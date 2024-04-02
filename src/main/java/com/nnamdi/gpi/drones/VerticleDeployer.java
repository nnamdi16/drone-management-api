package com.nnamdi.gpi.drones;


import io.smallrye.mutiny.vertx.core.AbstractVerticle;
import io.vertx.mutiny.core.Vertx;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.event.Startup;
import jakarta.enterprise.inject.Instance;

@ApplicationScoped
public class VerticleDeployer {
    public void init(@Observes Startup startupEvent, Vertx vertx, Instance<AbstractVerticle> verticles) {
        verticles.forEach(v -> vertx.deployVerticle(v).await().indefinitely());

    }
}
