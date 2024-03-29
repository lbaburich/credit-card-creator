package com.lbaburic.learning.cardconsumer.dto;

import lombok.Data;

@Data
public class CardRequestDTO {

    private String firstName;
    private String lastName;
    private String status;
    private String oib;

}
