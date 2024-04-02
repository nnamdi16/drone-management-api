package com.nnamdi.gpi.drones.model;


import com.nnamdi.gpi.drones.util.Direction;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.io.Serializable;
import java.util.Objects;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "drones")
public class Drone extends AbstractEntity implements Serializable {
    @Column(name = "coordinateX")
    private int coordinateX;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "coordinateY")
    private int coordinateY;

    @Column(name = "direction")
    @Enumerated(EnumType.STRING)
    private Direction direction;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Drone drone = (Drone) o;
        return getId() != null && Objects.equals(getId(), drone.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
