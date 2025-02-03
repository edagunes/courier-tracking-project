package com.migrosone.courier_tracking.rabbitmq;

import com.migrosone.courier_tracking.dto.CourierLocationRequest;
import com.migrosone.courier_tracking.service.CourierEntranceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourierTrackingConsumerService {

    private final CourierEntranceService courierEntranceService;

    public void processPayload(CourierTrackingConsumerPayload payload) {
        CourierLocationRequest courierLocation = new CourierLocationRequest();
        courierLocation.setCourierId(payload.getCourierId());
        courierLocation.setLat(payload.getLat());
        courierLocation.setLng(payload.getLng());
        courierLocation.setTime(payload.getTime());

        courierEntranceService.processLocationAndDistance(courierLocation);
    }
}
