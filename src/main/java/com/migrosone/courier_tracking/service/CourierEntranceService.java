package com.migrosone.courier_tracking.service;

import com.migrosone.courier_tracking.dto.CourierLocationRequest;
import com.migrosone.courier_tracking.entity.CourierEntrance;
import com.migrosone.courier_tracking.entity.CourierLocation;
import com.migrosone.courier_tracking.model.SaveTotalDistanceInput;
import com.migrosone.courier_tracking.repository.CourierEntranceRepository;
import com.migrosone.courier_tracking.repository.CourierLocationRepository;
import com.migrosone.courier_tracking.repository.StoreRepository;
import org.springframework.stereotype.Service;

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

    public void processLocationAndDistance(CourierLocationRequest courierLocationRequest) {

        saveCourierLocation(courierLocationRequest);

        List<Integer> storeIds = storeRepository.findAllStoreIdIfIsInRadius(courierLocationRequest.getCourierId(), courierLocationRequest.getLat(), courierLocationRequest.getLng(), RADIUS, EARTH_RADIUS, courierLocationRequest.getTime(), courierLocationRequest.getTime().minusMinutes(1));
        storeIds.forEach(storeId -> entranceRepository.save(new CourierEntrance(storeId, courierLocationRequest.getCourierId(), courierLocationRequest.getTime())));

        distanceService.saveTotalTravelDistance(SaveTotalDistanceInput.builder()
                .courierId(courierLocationRequest.getCourierId())
                .lat(courierLocationRequest.getLat())
                .lng(courierLocationRequest.getLng())
                .time(courierLocationRequest.getTime())
                .build());

    }

    private void saveCourierLocation(CourierLocationRequest courierLocationRequest) {
        CourierLocation courierLocation = new CourierLocation();
        courierLocation.setCourierId(courierLocationRequest.getCourierId());
        courierLocation.setLat(courierLocationRequest.getLat());
        courierLocation.setLng(courierLocationRequest.getLng());
        courierLocation.setTime(courierLocationRequest.getTime());

        locationRepository.save(courierLocation);
    }
}
