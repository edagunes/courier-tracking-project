package com.migrosone.courier_tracking.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "COURIER_LOCATION")
@NoArgsConstructor
@AllArgsConstructor
@IdClass(CourierLocationId.class)
public class CourierLocation {

    @Id
    private Integer courierId;
    @Id
    private LocalDateTime time;
    private double lat;
    private double lng;

}
