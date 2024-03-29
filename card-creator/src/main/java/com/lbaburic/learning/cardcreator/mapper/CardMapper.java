package com.lbaburic.learning.cardcreator.mapper;

import com.lbaburic.learning.cardcreator.dto.request.CardRequestDTO;
import com.lbaburic.learning.cardcreator.dto.response.CardResponseDTO;
import com.lbaburic.learning.cardcreator.entity.CardEntity;

public final class CardMapper {

    private CardMapper() {}

    public static CardResponseDTO mapToCardResponseDTO(CardEntity card) {
        return CardResponseDTO.builder()
                .firstName(card.getFirstName())
                .lastName(card.getLastName())
                .status(card.getStatus())
                .build();
    }


    public static CardEntity mapToCardEntity(CardRequestDTO cardResponseDTO) {
        return CardEntity.builder()
                .oib(cardResponseDTO.getOib())
                .firstName(cardResponseDTO.getFirstName())
                .lastName(cardResponseDTO.getLastName())
                .status(cardResponseDTO.getStatus())
                .build();
    }
}
