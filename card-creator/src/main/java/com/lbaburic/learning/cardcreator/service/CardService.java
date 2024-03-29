package com.lbaburic.learning.cardcreator.service;

import com.lbaburic.learning.cardcreator.dto.request.CardRequestDTO;
import com.lbaburic.learning.cardcreator.dto.response.CardResponseDTO;
import com.lbaburic.learning.cardcreator.entity.CardEntity;
import com.lbaburic.learning.cardcreator.exception.CardAlreadyExistsException;
import com.lbaburic.learning.cardcreator.exception.CardNotFoundException;
import com.lbaburic.learning.cardcreator.exception.InvalidOibException;
import com.lbaburic.learning.cardcreator.mapper.CardMapper;
import com.lbaburic.learning.cardcreator.repository.CardRepository;
import com.lbaburic.learning.cardcreator.utils.OibUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;

    public CardResponseDTO getCardByOib(String oib) {

        validateOib(oib);

        CardEntity cardEntity = cardRepository.findByOib(oib)
                .orElseThrow(
                        () -> {
                            log.error("Card with oib:{} not found", oib);
                            throw new CardNotFoundException("Card with oib: " + oib + " not found");
                        }
                );

        checkStatus(cardEntity);

        log.info("Card with oib:{} found", oib);
        return CardMapper.mapToCardResponseDTO(cardEntity);
    }

    public void checkStatus(CardEntity cardEntity) {
        if("PROCESSING".equals(cardEntity.getStatus())) {
            Date twoMinutesAgo = new Date(System.currentTimeMillis() - 120 * 1000);

            if(cardEntity.getCreatedAt().before(twoMinutesAgo)) {
                updateEntityStatus(cardEntity);
            }
        }
    }

    private void updateEntityStatus(CardEntity cardEntity) {
        cardEntity.setStatus("DONE");
        cardRepository.save(cardEntity);
        log.info("Card with oib:{} created", cardEntity.getOib());
    }

    public CardResponseDTO createNewCard(CardRequestDTO cardRequestDTO) {

        validateOib(cardRequestDTO.getOib());

        Optional<CardEntity> cardEntityOptional = cardRepository.findByOib(cardRequestDTO.getOib());

        if(cardEntityOptional.isPresent()) {
            log.error("Card with oib:{} already exists", cardRequestDTO.getOib());
            throw new CardAlreadyExistsException("Card with oib: " + cardRequestDTO.getOib() + " already exists");
        }

        CardEntity cardEntity = cardRepository.save(CardMapper.mapToCardEntity(cardRequestDTO));
        log.info("Card with oib:{} is being processed", cardRequestDTO.getOib());
        return CardMapper.mapToCardResponseDTO(cardEntity);
    }

    public boolean deleteCardByOib(String oib) {

        validateOib(oib);

        Optional<CardEntity> cardEntityOptional = cardRepository.findByOib(oib);

        if(!cardEntityOptional.isPresent()) {
            log.error("Card with oib:{} not found", oib);
            throw new CardNotFoundException("Card with oib: " + oib + " not found");
        }

        cardRepository.deleteById(cardEntityOptional.get().getId());
        log.info("Card with oib:{} deleted", oib);
        return true;
    }

    private void validateOib(String oib) {
        if(!OibUtils.isValidOib(oib)) {
            log.error("Invalid OIB format for {}", oib);
            throw new InvalidOibException("OIB: " + oib + " is invalid");
        }
    }
}
