package com.migrosone.courier_tracking.rabbitmq;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@SuperBuilder
public class CourierTrackingConsumerPayload {

    private Integer courierId;
    private LocalDateTime time;
    private double lat;
    private double lng;
}
