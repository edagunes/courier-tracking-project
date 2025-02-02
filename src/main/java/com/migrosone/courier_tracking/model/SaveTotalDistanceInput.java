package com.migrosone.courier_tracking.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaveTotalDistanceInput {

    private Integer courierId;
    private LocalDateTime time;
    private double latitude;
    private double longitude;

}
