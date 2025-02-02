package com.migrosone.courier_tracking.repository;

import com.migrosone.courier_tracking.entity.CourierEntranceId;
import com.migrosone.courier_tracking.entity.CourierLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CourierLocationRepository extends JpaRepository<CourierLocation, CourierEntranceId> {

    List<CourierLocation> findAllByOrderByTimeAsc();

    @Query("SELECT cl FROM CourierLocation cl WHERE cl.courierId=:courierId AND cl.time = (SELECT MAX(cMax.time) FROM CourierLocation cMax WHERE cMax.courierId=:courierId AND cMax.time <:time)")
    CourierLocation findLastLocationOfCourier(@Param("courierId") Integer courierId,
                                              @Param("time") LocalDateTime time);
}
