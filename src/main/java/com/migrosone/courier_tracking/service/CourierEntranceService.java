package com.migrosone.courier_tracking.service;

import com.migrosone.courier_tracking.entity.CourierEntrance;
import com.migrosone.courier_tracking.model.SaveTotalDistanceInput;
import com.migrosone.courier_tracking.repository.CourierEntranceRepository;
import com.migrosone.courier_tracking.repository.CourierLocationRepository;
import com.migrosone.courier_tracking.repository.StoreRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.migrosone.courier_tracking.constant.CourierTrackingConstant.EARTH_RADIUS;
import static com.migrosone.courier_tracking.constant.CourierTrackingConstant.RADIUS;

@Service
public class CourierEntranceService {

    private final CourierLocationRepository locationRepository;

    private final CourierEntranceRepository entranceRepository;

    private final StoreRepository storeRepository;

    private final CourierDistanceService distanceService;

    public CourierEntranceService(CourierLocationRepository locationRepository, CourierEntranceRepository entranceRepository, StoreRepository storeRepository, CourierDistanceService distanceService) {
        this.locationRepository = locationRepository;
        this.entranceRepository = entranceRepository;
        this.storeRepository = storeRepository;
        this.distanceService = distanceService;
    }

    public void processLocationAndDistance() {

        locationRepository.findAllByOrderByTimeAsc().forEach(courierLocation -> {
            Integer courierId = courierLocation.getCourierId();
            LocalDateTime time = courierLocation.getTime();

            List<Integer> storeIds = storeRepository.findAllStoreIdIfIsInRadius(courierId, courierLocation.getLatitude(), courierLocation.getLongitude(), RADIUS, EARTH_RADIUS, time, time.minusMinutes(1));
            storeIds.forEach(storeId -> entranceRepository.save(new CourierEntrance(storeId, courierId, time)));

            distanceService.saveTotalTravelDistance(SaveTotalDistanceInput.builder()
                    .courierId(courierId)
                    .latitude(courierLocation.getLatitude())
                    .longitude(courierLocation.getLongitude())
                    .time(time)
                    .build());
        });

    }
}
