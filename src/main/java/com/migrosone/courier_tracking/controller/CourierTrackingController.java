package com.migrosone.courier_tracking.controller;

import com.migrosone.courier_tracking.service.CourierDistanceService;
import com.migrosone.courier_tracking.service.CourierEntranceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courier-tracking")
public class CourierTrackingController {

    private CourierEntranceService courierEntranceService;
    private CourierDistanceService courierDistanceService;

    public CourierTrackingController(CourierEntranceService courierEntranceService, CourierDistanceService courierDistanceService) {
        this.courierEntranceService = courierEntranceService;
        this.courierDistanceService = courierDistanceService;
    }

    @PostMapping("/courier/location")
    public ResponseEntity<String> saveNearbyCourierAndTotalDistance() {
        courierEntranceService.processLocationAndDistance();
        return ResponseEntity.ok("Nearby couriers saved");
    }

    @GetMapping("/courier/{courierId}/distance") //TODO: null olursa response entity'de no content yolla
    public ResponseEntity<Double> getCourierDistance(@PathVariable("courierId") Integer courierId) {
        double distance = courierDistanceService.getTotalTravelDistance(courierId);
        return ResponseEntity.ok(distance);
    }

}
