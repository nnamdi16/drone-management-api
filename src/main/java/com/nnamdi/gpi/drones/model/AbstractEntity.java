package com.nnamdi.gpi.drones.model;

import com.nnamdi.gpi.drones.util.AppUtil;
import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.ZonedDateTime;


@Slf4j
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@MappedSuperclass
public class AbstractEntity extends PanacheEntityBase {
    @Id
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "updated_date")
    @UpdateTimestamp
    private ZonedDateTime lastModifiedDate;

    @Column(name = "created_date")
    @CreationTimestamp
    private ZonedDateTime createdDate;

    @PrePersist
    public void abstractPrePersist() {
        log.debug("about to run abstractPrePersist method");
        createdDate = ZonedDateTime.now();
        lastModifiedDate = ZonedDateTime.now();
        if (AppUtil.stringIsNullOrEmpty(id)) {
            id = AppUtil.generateUUIDString();
        }
        log.debug("finished running abstractPrePersist method ");
    }

    @PreUpdate
    public void abstractPreUpdate() {
        lastModifiedDate = ZonedDateTime.now();
    }
}
