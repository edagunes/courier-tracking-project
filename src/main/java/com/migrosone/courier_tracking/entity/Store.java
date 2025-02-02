package com.migrosone.courier_tracking.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "STORE")
@NoArgsConstructor
@AllArgsConstructor
public class Store {

    @Id
    private Integer storeId;
    private String name;
    private double latitude;
    private double longitude;

}
