package com.migrosone.courier_tracking.repository;

import com.migrosone.courier_tracking.entity.CourierDistance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourierDistanceRepository extends JpaRepository<CourierDistance, Integer> {

    CourierDistance findByCourierId(Integer courierId);
}
