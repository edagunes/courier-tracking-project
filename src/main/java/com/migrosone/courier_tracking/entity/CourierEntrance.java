package com.migrosone.courier_tracking.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "COURIER_ENTRANCE")
@NoArgsConstructor
@AllArgsConstructor
@IdClass(CourierEntranceId.class)
public class CourierEntrance {

    @Id
    private Integer storeId;
    @Id
    private Integer courierId;
    @Id
    private LocalDateTime entryTime;
}
