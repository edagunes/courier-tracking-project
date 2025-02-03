package com.migrosone.courier_tracking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourierLocationRequest {

    private Integer courierId;
    private LocalDateTime time;
    private double lat;
    private double lng;

}
