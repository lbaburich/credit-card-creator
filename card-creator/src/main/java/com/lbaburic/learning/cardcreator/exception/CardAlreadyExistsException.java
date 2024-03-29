package com.lbaburic.learning.cardcreator.exception;

public class CardAlreadyExistsException extends RuntimeException {

    public CardAlreadyExistsException(String message) {
        super(message);
    }
}
