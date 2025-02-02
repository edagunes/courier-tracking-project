package com.migrosone.courier_tracking.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;


@NoArgsConstructor
public class CourierEntranceId implements Serializable {

    private Integer storeId;
    private Integer courierId;
    private LocalDateTime entryTime;

    public CourierEntranceId(Integer storeId, Integer courierId, LocalDateTime entryTime) {
        this.storeId = storeId;
        this.courierId = courierId;
        this.entryTime = entryTime;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CourierEntranceId that = (CourierEntranceId) o;
        return Objects.equals(storeId, that.storeId) && Objects.equals(courierId, that.courierId) && Objects.equals(entryTime, that.entryTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storeId, courierId, entryTime);
    }
}
