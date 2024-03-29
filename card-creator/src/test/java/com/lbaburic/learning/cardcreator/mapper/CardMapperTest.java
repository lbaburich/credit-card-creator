package com.lbaburic.learning.cardcreator.mapper;

import com.lbaburic.learning.cardcreator.dto.request.CardRequestDTO;
import com.lbaburic.learning.cardcreator.dto.response.CardResponseDTO;
import com.lbaburic.learning.cardcreator.entity.CardEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardMapperTest {


    @Test
    void test_mapToCardResponseDTO() {

        CardEntity cardEntity = CardEntity.builder()
                .id(1)
                .oib("50640346679")
                .firstName("Ivan")
                .lastName("Horvat")
                .status("PROCESSING")
                .build();

        CardResponseDTO cardResponseDTO = CardMapper.mapToCardResponseDTO(cardEntity);

        assertEquals(cardResponseDTO.getFirstName(), cardEntity.getFirstName());
        assertEquals(cardResponseDTO.getLastName(), cardEntity.getLastName());
        assertEquals(cardResponseDTO.getStatus(), cardEntity.getStatus());
    }

    @Test
    void test_mapToCardEntity() {

        CardRequestDTO cardRequestDTO = CardRequestDTO.builder()
                .oib("50640346679")
                .firstName("Ivan")
                .lastName("Horvat")
                .status("PROCESSING")
                .build();

        CardEntity cardEntity = CardMapper.mapToCardEntity(cardRequestDTO);

        assertEquals(cardRequestDTO.getOib(), cardEntity.getOib());
        assertEquals(cardRequestDTO.getFirstName(), cardEntity.getFirstName());
        assertEquals(cardRequestDTO.getLastName(), cardEntity.getLastName());
        assertEquals(cardRequestDTO.getStatus(), cardEntity.getStatus());
    }
}