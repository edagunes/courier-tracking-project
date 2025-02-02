package com.migrosone.courier_tracking.repository;

import com.migrosone.courier_tracking.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store,Integer> {

    @Query("SELECT s.storeId "
            + " FROM Store s "
            + " WHERE (:earthRadius * ACOS(COS(RADIANS(:lat)) * COS(RADIANS(s.lat))"
            + " * COS(RADIANS(s.lng) - RADIANS(:lng)) + SIN(RADIANS(:lat)) * SIN(RADIANS(s.lat)))) < :radius"
            + " AND NOT EXISTS (SELECT 1 "
                            + " FROM CourierEntrance ce "
                            + " WHERE ce.storeId = s.storeId"
                            + " AND ce.courierId = :courierId"
                            + " AND ce.entryTime BETWEEN :oneMinuteAgo AND :courierTime)")
    List<Integer> findAllStoreIdIfIsInRadius(@Param("courierId") Integer courierId ,
                                             @Param("lat") double lat,
                                             @Param("lng") double lng,
                                             @Param("radius") double radius,
                                             @Param("earthRadius") double earthRadius,
                                             @Param("courierTime") LocalDateTime courierTime,
                                             @Param("oneMinuteAgo") LocalDateTime oneMinuteAgo);
}
