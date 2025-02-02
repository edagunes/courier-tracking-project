package com.migrosone.courier_tracking.entity;

import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@NoArgsConstructor
public class CourierLocationId implements Serializable {

    private Integer courierId;

    private LocalDateTime time;

    public CourierLocationId(Integer courierId, LocalDateTime time) {
        this.courierId = courierId;
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CourierLocationId that = (CourierLocationId) o;
        return Objects.equals(courierId, that.courierId) && Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courierId, time);
    }
}
