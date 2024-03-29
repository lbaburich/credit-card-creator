package com.lbaburic.learning.cardcreator.exception;

public class CardNotFoundException  extends RuntimeException{

    public CardNotFoundException(String message) {
        super(message);
    }
}
