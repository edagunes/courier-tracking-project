package com.migrosone.courier_tracking.rabbitmq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CourierTrackingConsumer {

    private final CourierTrackingConsumerService courierTrackingConsumerService;

    @RabbitListener(queues = "courier_tracking_queue")
    public void listenCourierTrackingQueue(CourierTrackingConsumerPayload payload){
        try{
            courierTrackingConsumerService.processPayload(payload);
        }catch (Exception e){
            log.error("Consume Edilirken Hata: {}", e.getMessage());
        }

    }
}
