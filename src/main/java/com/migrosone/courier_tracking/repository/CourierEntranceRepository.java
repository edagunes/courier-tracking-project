package com.migrosone.courier_tracking.repository;

import com.migrosone.courier_tracking.entity.CourierEntrance;
import com.migrosone.courier_tracking.entity.CourierEntranceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourierEntranceRepository extends JpaRepository<CourierEntrance, CourierEntranceId> {

    @Query("SELECT c FROM CourierEntrance c WHERE c.storeId=:storeId AND c.courierId=:courierId ORDER BY c.entryTime DESC LIMIT 1")
    Optional<CourierEntrance> findLastEntryForCourier(@Param("storeId") Integer storeId,
                                                      @Param("courierId") Integer courierId);
}
