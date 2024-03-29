package com.lbaburic.learning.cardproducer.service;

import com.lbaburic.learning.cardproducer.dto.request.CardRequestDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
@AllArgsConstructor
public class KafkaMessagePublisher {

    private final KafkaTemplate<String,Object> template;

    public void sendEventsToTopic(CardRequestDTO cardRequestDTO) {
        try {
            CompletableFuture<SendResult<String, Object>> future = template.send("create-card-demo02", cardRequestDTO);
            future.whenComplete((result, ex) -> {
                if (ex == null) {
                    log.info("Sent message={}", cardRequestDTO.toString());
                } else {
                    log.error(ex.getMessage());
                }
            });

        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }

}
