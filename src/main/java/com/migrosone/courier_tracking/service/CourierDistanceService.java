package com.migrosone.courier_tracking.service;

import com.migrosone.courier_tracking.entity.CourierDistance;
import com.migrosone.courier_tracking.entity.CourierLocation;
import com.migrosone.courier_tracking.model.SaveTotalDistanceInput;
import com.migrosone.courier_tracking.repository.CourierDistanceRepository;
import com.migrosone.courier_tracking.repository.CourierLocationRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class CourierDistanceService {

    private final CourierLocationRepository locationRepository;
    private final CourierDistanceRepository distanceRepository;

    public CourierDistanceService(CourierLocationRepository locationRepository, CourierDistanceRepository distanceRepository) {
        this.locationRepository = locationRepository;
        this.distanceRepository = distanceRepository;
    }

    public void saveTotalTravelDistance(SaveTotalDistanceInput saveTotalDistanceInput) {

        CourierLocation getLastLocationOfCourier = locationRepository.findLastLocationOfCourier(saveTotalDistanceInput.getCourierId(), saveTotalDistanceInput.getTime());

        if (Objects.isNull(getLastLocationOfCourier)) {
            distanceRepository.save(new CourierDistance(saveTotalDistanceInput.getCourierId(),0.0));
            return;
        }

        double distance = calculateDistance(saveTotalDistanceInput, getLastLocationOfCourier);
        CourierDistance courierDistance = distanceRepository.findByCourierId(saveTotalDistanceInput.getCourierId());
        if (Objects.isNull(courierDistance)) {
            return;
        }
        courierDistance.setTotalDistance(courierDistance.getTotalDistance()+distance);
        distanceRepository.save(courierDistance);
    }

    private double calculateDistance(SaveTotalDistanceInput saveTotalDistanceInput, CourierLocation getLastLocationOfCourier) {

        return Haversine.calculateDistance(saveTotalDistanceInput.getLat(), saveTotalDistanceInput.getLng(),
                getLastLocationOfCourier.getLat(), getLastLocationOfCourier.getLng());
    }

    public double getTotalTravelDistance(Integer courierId) {

        CourierDistance courierDistance = distanceRepository.findByCourierId(courierId);
        if (Objects.isNull(courierDistance)) {
            throw new NoSuchElementException("Courier not found");
        }
        return courierDistance.getTotalDistance();
    }
}
