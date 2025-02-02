package com.migrosone.courier_tracking.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "COURIER_DISTANCE")
@NoArgsConstructor
@AllArgsConstructor
public class CourierDistance {

    @Id
    private Integer courierId;
    private double totalDistance;
}
