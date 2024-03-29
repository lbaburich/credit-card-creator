package com.lbaburic.learning.cardconsumer.consumer;

import com.lbaburic.learning.cardconsumer.service.ConsumerService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaMessageListener {

    private final ConsumerService consumerService;

    @KafkaListener(topics = "create-card-demo02",groupId = "local-group-01")
    public void consumeEvents(String event) {
        consumerService.callPostNewCard(event);
    }
}
