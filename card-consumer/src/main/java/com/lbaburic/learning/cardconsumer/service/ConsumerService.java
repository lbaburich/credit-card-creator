package com.lbaburic.learning.cardconsumer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lbaburic.learning.cardconsumer.dto.CardRequestDTO;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConsumerService {

    private final ObjectMapper objectMapper;
    private final RestClient restClient;


    public void callPostNewCard(String event) {

        try {
            CardRequestDTO cardRequestDTO = mapStringToCardRequestDTO(event);
            restClient.post()
                    .uri("/cards")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(cardRequestDTO)
                    .retrieve();

            log.info("Sent a card request with OIB: {}", cardRequestDTO.getOib());

        }catch (JsonProcessingException e) {
            log.error(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    private CardRequestDTO mapStringToCardRequestDTO(String event) throws JsonProcessingException {
        return objectMapper.readValue(event, CardRequestDTO.class);
    }

}
