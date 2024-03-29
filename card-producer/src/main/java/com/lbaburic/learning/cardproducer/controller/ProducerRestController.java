package com.lbaburic.learning.cardproducer.controller;

import com.lbaburic.learning.cardproducer.dto.request.CardRequestDTO;
import com.lbaburic.learning.cardproducer.service.KafkaMessagePublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/producer")
@RequiredArgsConstructor
public class ProducerRestController {

    private final KafkaMessagePublisher publisher;

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/cards")
    public ResponseEntity initiateCardCreation(@RequestBody CardRequestDTO cardRequestDTO) {

        publisher.sendEventsToTopic(cardRequestDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

}
